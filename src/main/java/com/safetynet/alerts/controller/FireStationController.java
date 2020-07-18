package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.PersonStationCounterDTO;
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
    * This method controller is used to retrieve persons covered by the station,
    * and calculate the number of adults & children.
    *
    * @param stationNumber
    * @return firestationDto
    */
   @GetMapping("/firestation")
   public PersonStationCounterDTO firestationNumber(
               @NotNull @RequestParam(value = "stationNumber") final String stationNumber,
               final HttpServletResponse response) {

      PersonStationCounterDTO firestationDto = fireStationService
                  .firestationNumber(stationNumber);

      if (firestationDto.getPersonsStationList() != null) {
         LOGGER.info("SUCCESS - FirestationNumber GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error(
                     "FAILED - No firestation founded for number: {}. Please verify the station number entered.",
                     stationNumber);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return firestationDto;
   }

   /**
    * This method controller is used to retrieve phone number of persons covered
    * by the station.
    *
    * @param firestation the station number
    * @return phoneNumberList
    */
   @GetMapping("/phoneAlert")
   public List<String> phoneAlert(
               @NotNull @RequestParam(value = "firestation") final String firestation,
               final HttpServletResponse response) {
      List<String> phoneNumberList = fireStationService.phoneAlert(firestation);

      if (!phoneNumberList.isEmpty()) {
         LOGGER.info("SUCCESS - PhoneAlert GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error(
                     "FAILED - No firestation founded for number: {}. Please verify the station number entered.",
                     firestation);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return phoneNumberList;
   }

   /**
    * This method controller is used to retrieve informations of persons leaving
    * at the address entered, with the firestation number indication.
    *
    * @param address
    * @return fireDtoPersonsList
    */
   @GetMapping("/fire")
   public List<FireDTO> fire(
               @NotNull @RequestParam(value = "address") final String address,
               final HttpServletResponse response) {

      List<FireDTO> fireDtoPersonsList = fireStationService.fire(address);

      if (!fireDtoPersonsList.isEmpty()) {
         LOGGER.info("SUCCESS - Fire GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("FAILED - No address founded for: {}.", address);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return fireDtoPersonsList;
   }

   /**
    * This method controller is used to recover the households served by the
    * station. The list must group persons by address. The information are:
    * name, age, telephone and medical record.
    * 
    * @param stations, List String
    * @return flood a FloodDTO List
    */
   @GetMapping("/flood/stations")
   public List<FloodDTO> flood(
               @NotNull @RequestParam(value = "stations") final List<String> stations,
               final HttpServletResponse response) {

      List<FloodDTO> flood = fireStationService.flood(stations);

      if (flood != null) {
         LOGGER.info("SUCCESS - Flood GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("FAILED - Flood GET request");
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return flood;
   }

}
