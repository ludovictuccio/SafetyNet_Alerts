package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * Data loader class.
 *
 * @author Ludovic Tuccio
 *
 */
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Service
public class DataLoader implements IDataLoader {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);

   /**
    * Method used to retrieve data from json file when the application starts,
    * and convert it to an object.
    *
    * @param file
    * @return entitiesInfosStorage
    */
   public EntitiesInfosStorage readJsonFile(final String file)
               throws IOException, NullPointerException {

      byte[] byteArray = Files.readAllBytes(new File(file).toPath());
      JsonIterator jsonIterator = JsonIterator.parse(byteArray);
      Any any = jsonIterator.readAny(); // Jsoniter container

      // Persons
      Any anyPerson = any.get("persons");
      List<Person> persons = new ArrayList<>();
      Map<String, List<Person>> households = new HashMap<>();
      anyPerson.forEach(personJson -> {
         String firstName = personJson.get("firstName").toString();
         String lastName = personJson.get("lastName").toString();
         String address = personJson.get("address").toString();
         String city = personJson.get("city").toString();
         String zip = personJson.get("zip").toString();
         String phone = personJson.get("phone").toString();
         String email = personJson.get("email").toString();

         Person person = new Person(firstName, lastName, address, city, zip,
                     phone, email);
         persons.add(person);

         List<Person> household = households.computeIfAbsent(address,
                     temp -> new ArrayList<>());
         household.add(person);
         LOGGER.debug("Persons loaded from a Json file");
      });

      // FireStations
      Any anyFireStation = any.get("firestations");
      Map<Integer, FireStation> firestations = new HashMap<>();
      anyFireStation.forEach(firestationJson -> {
         int id = firestationJson.get("station").toInt();
         String address = firestationJson.get("address").toString();

         FireStation firestation = firestations.computeIfAbsent(id,
                     currentId -> new FireStation(currentId));
         firestation.addAddress(address);
         LOGGER.debug("FireStations loaded from a Json file");
      });

      // MedicalRecord
      Any anyMedicalRecord = any.get("medicalrecords");
      anyMedicalRecord.forEach(medicalRecordJson -> {
         String firstName = medicalRecordJson.get("firstName").toString();
         String lastName = medicalRecordJson.get("lastName").toString();
         String birthdate = medicalRecordJson.get("birthdate").toString();

         List<String> medications = new ArrayList<>();
         Any medicationsAny = medicalRecordJson.get("medications");
         medicationsAny.forEach(medicationJson -> medications
                     .add(medicationJson.toString()));

         List<String> allergies = new ArrayList<>();
         Any allergiesAny = medicalRecordJson.get("allergies");
         allergiesAny.forEach(
                     allergyJson -> allergies.add(allergyJson.toString()));
         LOGGER.debug("MedicalRecords loaded from a Json file");

         // Allocation of medical records to corresponding persons
         searchPerson(firstName, lastName, persons).setMedicalRecord(
                     new MedicalRecord(birthdate, medications, allergies));
      });

      EntitiesInfosStorage entitiesInfosStorage = new EntitiesInfosStorage(
                  persons, firestations, households);
      return entitiesInfosStorage;
   }

   /**
    * Method used to search a person with name in person's list.
    *
    * @param firstName
    * @param lastName
    * @param personsList
    * @return person
    */
   public Person searchPerson(final String firstName, final String lastName,
               final List<Person> personsList) {
      return personsList.stream()
                  .filter(person -> firstName.equals(person.getFirstName())
                              && lastName.equals(person.getLastName()))
                  .findFirst()
                  .orElseThrow(() -> new IllegalArgumentException(
                              "The person named: "
                                          + firstName
                                          + " "
                                          + lastName
                                          + " is not found."));
   }

}
