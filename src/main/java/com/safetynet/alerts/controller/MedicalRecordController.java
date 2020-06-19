package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;

/**
 * 
 * Medical Record Controller class.
 *
 * @author Ludovic Tuccio
 *
 */
public class MedicalRecordController {

   /**
    * Used to retrieve persons informations.
    */
   private EntitiesInfosStorage entitiesInfosStorage;
   /**
    * Used to retrieve persons medical record.
    */
   private IMedicalRecordService medicalRecordService;

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
    * @return
    */
   public void updateMedicalRecord(final MedicalRecord medicalRecord) {

   }

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   public void deleteMedicalRecord(final String firstName,
               final String lastName) {

   }
}
