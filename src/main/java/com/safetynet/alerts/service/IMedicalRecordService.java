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
    * @return
    */
   MedicalRecord createMedicalRecord(MedicalRecord newMedicalRecord);

   /**
    * @param medicalRecord
    * @return
    */
   boolean updateMedicalRecord(MedicalRecord medicalRecord);

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   boolean deleteMedicalRecord(String firstName, String lastName);

}
