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
    * EntitiesInfosStorage variable used to retrieve persons informations from
    * model classes.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * This method service is used to create a new medicalrecord for existing
    * person without medicalrecord.
    *
    * @param personToCreate
    * @return Person newPerson
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

//   /**
//    * @param medicalRecord
//    */
//   public void updateMedicalRecord(final MedicalRecord medicalRecord) {
//
//      String birthdate = medicalRecord.set("birthdate").toString();
//      List<String> medications = (List<String>) medicalRecord
//                  .get("medications");
//      List<String> allergies = (List<String>) medicalRecord.get("allergies");
//
//      MedicalRecord medicalrecord = new MedicalRecord(birthdate, medications,
//                  allergies);
//
//      List<Person> personsList = entitiesInfosStorage.getPersonsList();
//
//      // Verification for not create a medicalrecord for nonexistent person
//      for (Person existingPerson : personsList) {
//         if (existingPerson.getFirstName()
//                     .equals(medicalRecord.get("firstName").toString())
//                     && existingPerson.getLastName().equals(
//                                 medicalRecord.get("lastName").toString())) {
//            existingPerson.setMedicalRecord(medicalrecord);
//         } else {
//            LOGGER.error(
//                        "FAILED to create the medicalrecord for: {} {}, nonexistent person.",
//                        medicalRecord.get("firstName").toString(),
//                        medicalRecord.get("lastName").toString());
//
//         }
//      }
//   }
//
//   }

   /**
    * @param firstName
    * @param lastName
    */
   public void deleteMedicalRecord(final String firstName,
               final String lastName) {

   }
}
