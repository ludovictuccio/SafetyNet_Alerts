package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IFireStationService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

/**
 * FireStation controller class.
 *
 * @author Ludovic Tuccio
 */
@RestController
public class FireStationController {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(FireStationController.class);
   /**
    * Used to retrieve firestations informations.
    */
   @Autowired
   private IFireStationService fireStationService;

   /**
    * This method controller is used to create a new firestation number/address
    * under responsibility mapping.
    *
    * @param firestationToCreate
    */
   @PostMapping("/firestation")
   public void addAddressForFirestation(
               @NotNull @RequestBody final Map<String, String> firestationToCreate,
               final HttpServletResponse response) {

      boolean isAdded = fireStationService
                  .addAddressForFirestation(firestationToCreate);

      if (isAdded) {
         LOGGER.info("SUCCESS - AddAddressForFirestation POST request -  Firestation number: {}, Address: {}",
                     firestationToCreate.get("station"),
                     firestationToCreate.get("address"));
         response.setStatus(Constants.STATUS_CREATED_201);
      } else {
         response.setStatus(Constants.ERROR_CONFLICT_409);
      }
   }

   /**
    * This method controller is used to update an existing address for other
    * existing firestation.
    *
    * @param firestationMappingToCreate
    */
   @PutMapping("/firestation")
   public void updateAddressForFireStation(
               @NotNull @RequestBody final Map<String, String> firestationMappingToCreate,
               final HttpServletResponse response) {

      boolean isUpdated = fireStationService
                  .updateAddressForFireStation(firestationMappingToCreate);

      if (isUpdated) {
         LOGGER.info("SUCCESS - UpdateFireStation PUT request -  Firestation number: {}, Address: {}",
                     firestationMappingToCreate.get("station"),
                     firestationMappingToCreate.get("address"));
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
   }

   /**
    * This method controller is used to delete an existing address, and update
    * the address/station mapping.
    *
    * @param address
    */
   @DeleteMapping("/firestation")
   public void deleteAddressForFireStation(
               @NotNull @RequestParam final String address,
               final HttpServletResponse response) {

      boolean isDeleted = fireStationService
                  .deleteAddressForFireStation(address);

      if (isDeleted) {
         LOGGER.info("SUCCESS - DeleteFireStation DELETE request -  Address: {}",
                     address);
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
   }

   /**
    * @param stationNumber
    * @return
    */
   public List<Person> stationNumber(final int stationNumber) {
      return null;

   }

   /**
    * @param householdAdress
    * @return
    */
   public List<Person> fire(final String householdAdress) {
      return null;

   }

   /**
    * @param stationsNumber
    * @return
    */
   public List<Person> flood(final int stationsNumber) {
      return null;

   }

   /**
    * @param fireStationNumber
    * @return
    */
   public List<String> phoneAlert(final int fireStationNumber) {
      return null;

   }

   /**
    * @param phoneNumber
    * @param responsibleFireStation
    * @param personsUnderFirestationResponsibility
    * @return
    */
   public List<String> getPhoneAlert(final String phoneNumber,
               final Map<Integer, FireStation> responsibleFireStation,
               final List<Person> personsUnderFirestationResponsibility,
               final HttpServletResponse response) {
      return null;

   }
}
