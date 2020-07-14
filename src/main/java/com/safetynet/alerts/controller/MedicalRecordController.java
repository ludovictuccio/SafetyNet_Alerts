package com.safetynet.alerts.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

/**
 * MedicalRecord Controller class.
 *
 * @author Ludovic Tuccio
 */
@RestController
public class MedicalRecordController {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(MedicalRecordController.class);
   /**
    * PersonService interface variable used to acces to service & model classes.
    */
   @Autowired
   private IMedicalRecordService medicalRecordService;

   /**
    * This method controller is used to create a new medicalrecord with the
    * service method.
    *
    * @param personToCreate
    */
   @PostMapping("/medicalRecord")
   public void createMedicalRecord(
               @RequestBody final MedicalRecord newMedicalRecord,
               final HttpServletResponse response) {

      MedicalRecord medicalRecordCreated = medicalRecordService
                  .createMedicalRecord(newMedicalRecord);

      if (medicalRecordCreated != null) {
         LOGGER.info("SUCCESS - CreateMedicalRecord POST request");
         response.setStatus(Constants.STATUS_CREATED_201);
      } else {
         response.setStatus(Constants.ERROR_CONFLICT_409);
      }
   }

   /**
    * This method controller is used to update an existing medicalrecord.
    *
    * @param medicalRecord
    */
   @PutMapping("/medicalRecord")
   public void updateMedicalRecord(
               @NotNull @RequestBody final MedicalRecord medicalRecord,
               final HttpServletResponse response) {

      boolean isUpdated = medicalRecordService
                  .updateMedicalRecord(medicalRecord);

      if (isUpdated) {
         LOGGER.info("SUCCESS - Update MedicalRecord PUT request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error(
                     "FAILED to update medicalrecord for person: {} {}. Unknow person.",
                     medicalRecord.getFirstName(), medicalRecord.getLastName());
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
   }

   /**
    * @param firstName
    * @param lastName
    */
   @DeleteMapping("/medicalRecord")
   public void deleteMedicalRecord(final String firstName,
               final String lastName) {

   }
}
