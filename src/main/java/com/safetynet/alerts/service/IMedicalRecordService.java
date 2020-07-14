package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * Medical record service interface.
 *
 * @author Ludovic Tuccio
 */
public interface IMedicalRecordService {

   /**
    * @param newMedicalRecord
    */
   MedicalRecord createMedicalRecord(MedicalRecord newMedicalRecord);

   /**
    * @param medicalRecord
    */
   boolean updateMedicalRecord(MedicalRecord medicalRecord);

   /**
    * @param firstName
    * @param lastName
    */
   void deleteMedicalRecord(String firstName, String lastName);

}
