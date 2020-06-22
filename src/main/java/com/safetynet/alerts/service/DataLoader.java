package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.TypeLiteral;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * Data loader class.
 *
 * @author Ludovic Tuccio
 *
 */
@Service
public class DataLoader implements IDataLoader {

   /**
    * Json file path.
    */
   private static String jsonFile = "src/main/resources/data.json";
   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);

   @Autowired
   private PersonService personService;

   @Autowired
   private MedicalRecordService medicalRecordService;

   @Autowired
   private FireStationService fireStationService;

   /**
    * Method used to retrieve data from json file when the application starts,
    * and convert it to an object.
    */
   @EventListener(ApplicationReadyEvent.class)
   public void startAppWithJsonFile() {
      try {
         byte[] byteArray;
         byteArray = Files.readAllBytes(new File(jsonFile).toPath());

         JsonIterator jsonIterator = JsonIterator.parse(byteArray);
         Any any = jsonIterator.readAny(); // Jsoniter container

         Any anyPerson = any.get("persons");
         anyPerson.forEach(person -> personService.createPerson(
                     new Person(person.get("firstName").toString(),
                                 (person.get("lastName").toString()),
                                 (person.get("address").toString()),
                                 (person.get("city").toString()),
                                 (person.get("zip").toString()),
                                 (person.get("phone").toString()),
                                 (person.get("email").toString()))));
         LOGGER.debug("Persons loaded from a Json file.");

         Any anyFireStation = any.get("firestations");
         anyFireStation.forEach(firestation -> fireStationService
                     .createFireStation(new FireStation(
                                 firestation.get("address").toString(),
                                 (firestation.get("station").toString()))));
         LOGGER.debug("Fire Stations loaded from a Json file.");

         Any anyMedical = any.get("medicalrecords");
         anyMedical.forEach(medicalRecord -> medicalRecordService
                     .createMedicalRecord(new MedicalRecord(
                                 medicalRecord.get("firstName").toString(),
                                 medicalRecord.get("lastName").toString(),
                                 medicalRecord.get("birthdate").toString(),
                                 medicalRecord.get("medications").as(
                                             new TypeLiteral<List<String>>() {
                                             }),
                                 medicalRecord.get("allergies").as(
                                             new TypeLiteral<List<String>>() {
                                             }))));
         LOGGER.debug("Medical Records loaded from a Json file.");
      } catch (IOException e) {
         LOGGER.error("ERROR while loading Json file process.");
      }
   }

   /**
    * Method used to report an EventListener launch error.
    */
   @EventListener(ApplicationFailedEvent.class)
   public void EventListenerExecuteFailed() {
      LOGGER.error("Application Event Listener is Failed.");
   }

}
