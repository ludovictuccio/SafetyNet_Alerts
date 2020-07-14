package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * MedicalRecord service class.
 *
 * @author Ludovic Tuccio
 */
@Service
public class MedicalRecordService implements IMedicalRecordService {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(MedicalRecordService.class);
   /**
    * EntitiesInfosStorage variable used to retrieve informations from model
    * classes.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * This method service is used to create a new medicalrecord for existing
    * person without medicalrecord.
    *
    * @param newMedicalRecord
    * @return MedicalRecord newMedicalRecord or null
    */
   public MedicalRecord createMedicalRecord(
               final MedicalRecord newMedicalRecord) {

      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      // For create a medicalrecord for a person without one
      for (Person existingPerson : personsList) {
         if (existingPerson.getFirstName().toUpperCase()
                     .equals(newMedicalRecord.getFirstName().toUpperCase())
                     && existingPerson.getLastName().toUpperCase().equals(
                                 newMedicalRecord.getLastName().toUpperCase())
                     && existingPerson.getMedicalRecord() == null) {
            existingPerson.setMedicalRecord(newMedicalRecord);
            return newMedicalRecord;
         }
      }
      LOGGER.error(
                  "FAILED to create the medicalrecord for: {} {}, existant medicalrecord. You must to update it.",
                  newMedicalRecord.getFirstName(),
                  newMedicalRecord.getLastName());
      return null;
   }

   /**
    * This method service is used to update an existing medicalrecord.
    *
    * @param medicalRecord
    * @return boolean isUpdated
    */
   public boolean updateMedicalRecord(final MedicalRecord medicalRecord) {
      boolean isUpdated = false;
      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      // For update an existing person's medicalrecord.
      for (Person existingPerson : personsList) {
         if (existingPerson.getMedicalRecord() != null
                     && existingPerson.getFirstName().toUpperCase().equals(
                                 medicalRecord.getFirstName().toUpperCase())
                     && existingPerson.getLastName().toUpperCase().equals(
                                 medicalRecord.getLastName().toUpperCase())) {

            existingPerson.setMedicalRecord(medicalRecord);
            isUpdated = true;
         }
      }
      return isUpdated;
   }

   /**
    * @param firstName
    * @param lastName
    */
   public void deleteMedicalRecord(final String firstName,
               final String lastName) {

   }
}
