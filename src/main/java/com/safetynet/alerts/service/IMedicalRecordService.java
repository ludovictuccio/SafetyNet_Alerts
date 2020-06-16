package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * Medical record service interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IMedicalRecordService {

   /**
    * @param firstName
    * @param lastName
    */
   MedicalRecord getMedicalRecordByName(String firstName, String lastName);

   /**
    * @param medicalRecord
    */
   MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

   /**
    * @param medicalRecord
    */
   void updateMedicalRecord(MedicalRecord medicalRecord);

   /**
    * @param firstName
    * @param lastName
    */
   void deleteMedicalRecord(String firstName, String lastName);

}
