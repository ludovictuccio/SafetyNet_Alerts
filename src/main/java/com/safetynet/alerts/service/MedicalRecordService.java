package com.safetynet.alerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.MedicalRecord;

/**
 * Medical record service class.
 *
 * @author Ludovic Tuccio
 *
 */
@Service
public class MedicalRecordService implements IMedicalRecordService {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(MedicalRecordService.class);
   /**
    * Used to retrieve persons informations.
    */
   @Autowired
   private EntitiesInfosStorage infosRetrieval;

   /**
    * Public class constructor.
    */
   public MedicalRecordService() {

   }

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   public MedicalRecord getMedicalRecordByName(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @param medicalRecord
    * @return
    */
   public MedicalRecord createMedicalRecord(final MedicalRecord medicalRecord) {
      return null;

   }

   /**
    * @param medicalRecord
    */
   public void updateMedicalRecord(final MedicalRecord medicalRecord) {

   }

   /**
    * @param firstName
    * @param lastName
    */
   public void deleteMedicalRecord(final String firstName,
               final String lastName) {

   }
}
