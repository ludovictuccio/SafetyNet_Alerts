package com.safetynet.alerts.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

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
 */
@Component
public class DataLoader {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);
   /**
    * Persons counter.
    */
   private static int personCounter = 0;
   /**
    * Firestations counter.
    */
   private static int firestationCounter = 0;
   /**
    * MedicalRecords counter.
    */
   private static int medicalrecordCounter = 0;

   /**
    * Method used to retrieve data from json file when the application starts,
    * and convert it to an object.
    *
    * @param file
    * @return entitiesInfosStorage
    */
   public static EntitiesInfosStorage readJsonFile(final String file)
               throws IOException, NullPointerException {
      LOGGER.debug("ReadJsonFile initialization");
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
         personCounter++;

         List<Person> household = households.computeIfAbsent(address,
                     temp -> new ArrayList<>());
         household.add(person);
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
         firestationCounter++;
      });
      // MedicalRecord
      Any anyMedicalRecord = any.get("medicalrecords");
      anyMedicalRecord.forEach(medicalRecordJson -> {
         String firstName = medicalRecordJson.get("firstName").toString();
         String lastName = medicalRecordJson.get("lastName").toString();
         String birthdate = medicalRecordJson.get("birthdate").toString();

         List<String> medications = new ArrayList<>();
         Any anyMedications = medicalRecordJson.get("medications");
         anyMedications.forEach(medicationJson -> medications
                     .add(medicationJson.toString()));

         List<String> allergies = new ArrayList<>();
         Any anyAllergies = medicalRecordJson.get("allergies");
         anyAllergies.forEach(
                     allergyJson -> allergies.add(allergyJson.toString()));

         // Allocation of medical records to corresponding persons
         searchPerson(firstName, lastName, persons).setMedicalRecord(
                     new MedicalRecord(birthdate, medications, allergies));
         medicalrecordCounter++;
      });
      LOGGER.debug(String.valueOf(personCounter)
                  + " persons loaded from a Json file");
      LOGGER.debug(String.valueOf(firestationCounter)
                  + " firestations loaded from a Json file");
      LOGGER.debug(String.valueOf(medicalrecordCounter)
                  + " medicalrecords loaded from a Json file");

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
   public static Person searchPerson(final String firstName,
               final String lastName, final List<Person> personsList) {
      return personsList.stream()
                  .filter(person -> firstName.equals(person.getFirstName())
                              && lastName.equals(person.getLastName()))
                  .findFirst()
                  .orElseThrow(() -> new IllegalArgumentException(
                              "The person named: "
                                          + firstName.toUpperCase()
                                          + " "
                                          + lastName.toUpperCase()
                                          + " is not found."));
   }

}
