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
    * @return a MedicalRecord object
    */
   MedicalRecord createMedicalRecord(MedicalRecord newMedicalRecord);

   /**
    * @param medicalRecord
    * @return a boolean
    */
   boolean updateMedicalRecord(MedicalRecord medicalRecord);

   /**
    * @param firstName
    * @param lastName
    * @return a boolean
    */
   boolean deleteMedicalRecord(String firstName, String lastName);

}
