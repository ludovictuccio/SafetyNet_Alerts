package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.PersonStationCounterDTO;
import com.safetynet.alerts.dto.PersonStationDTO;
import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;

/**
 * FireStation service class.
 *
 * @author Ludovic Tuccio
 */
@Service
public class FireStationService implements IFireStationService {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(FireStationService.class);
   /**
    * EntitiesInfosStorage variable used to retrieve informations from model
    * classes.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * This method service is used to create a new firestation number/address
    * under responsibility mapping.
    *
    * @param firestationMappingToCreate
    * @return isAdded boolean
    */
   public boolean addAddressForFirestation(
               final Map<String, String> firestationMappingToCreate) {
      boolean isAdded = false;
      try {
         Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                     .getFirestations();

         String newAddress = firestationMappingToCreate.get("address")
                     .toString();
         FireStation firestationRecovered = allFirestationsMapping
                     .get(firestationMappingToCreate.get("station").toString());

         for (Entry<String, FireStation> entry : allFirestationsMapping
                     .entrySet()) {
            FireStation firestationsNumber = entry.getValue();

            // For not add the same address already added
            if (firestationsNumber.getAddresses().toString()
                        .contains(newAddress.toString())) {
               LOGGER.error(
                           "The address entered for: {} already exists for a mapping. Please delete or update a mapping.",
                           firestationMappingToCreate.values());
               return isAdded;
            }
         }
         firestationRecovered.addAddress(newAddress);
         return isAdded = true;

      } catch (NullPointerException np) {
         throw new NullPointerException(
                     "NullPointerException. Please verify the station number entered."
                                 + np);
      }
   }

   /**
    * This method service is used to update an existing address for other
    * existing firestation.
    *
    * @param firestationMappingToCreate
    * @return isUpdated boolean
    */
   public boolean updateAddressForFireStation(
               final Map<String, String> firestationMappingToCreate) {
      boolean isUpdated = false;
      try {
         Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                     .getFirestations();

         String address = firestationMappingToCreate.get("address").toString();
         FireStation firestationNumberRecovered = allFirestationsMapping
                     .get(firestationMappingToCreate.get("station").toString());

         for (Entry<String, FireStation> entry : allFirestationsMapping
                     .entrySet()) {
            FireStation firestationsNumber = entry.getValue();

            // 1.Verify existing address
            // 2.Remove the address that was assigned to another station
            // 3.Update address with the other station
            if (firestationsNumber.getAddresses().toString()
                        .contains(address)) {
               firestationsNumber.getAddresses().remove(address);
               firestationNumberRecovered.addAddress(address);
               return isUpdated = true;
            }
         }
         LOGGER.error("The address entered for: {} does not exist.",
                     firestationMappingToCreate.values());
         return isUpdated;
      } catch (NullPointerException np) {
         throw new NullPointerException(
                     "NullPointerException. Please verify the station number entered."
                                 + np);
      }
   }

   /**
    * This method service is used to delete an existing address, and update the
    * address/station mapping.
    *
    * @param address
    * @return isDeleted boolean
    */
   public boolean deleteAddressForFireStation(final String address) {
      boolean isDeleted = false;
      Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                  .getFirestations();

      for (Entry<String, FireStation> entry : allFirestationsMapping
                  .entrySet()) {
         FireStation firestationsNumber = entry.getValue();

         if (firestationsNumber.getAddresses().toString().contains(address)) {
            firestationsNumber.getAddresses().remove(address);
            return isDeleted = true;
         }
      }
      LOGGER.error("The address entered for: {} does not exist.", address);
      return isDeleted;
   }

   /**
    * This method service is used to retrieve persons covered by the station,
    * and calculate the number of adults & children.
    *
    * @param stationNumber
    * @return firestationDto
    */
   public PersonStationCounterDTO firestationNumber(
               final String stationNumber) {
      int totalAdultsNumber = 0;
      int totalChildrenNumber = 0;
      Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                  .getFirestations();
      Set<String> addressesRecovered = new HashSet<>();
      for (Entry<String, FireStation> entry : allFirestationsMapping
                  .entrySet()) {
         FireStation firestation = entry.getValue();

         if (firestation.getStation().toString()
                     .equals(stationNumber.toString())) {
            addressesRecovered = firestation.getAddresses();
         }
      }
      // Verify addresses recover success
      if (addressesRecovered.isEmpty()) {
         PersonStationCounterDTO emptyList = new PersonStationCounterDTO(null,
                     0, 0);
         return emptyList;
      }
      List<Person> allPersonsList = entitiesInfosStorage.getPersonsList();
      List<PersonStationDTO> personsUnderResponsibility = new ArrayList<>();

      for (Person person : allPersonsList) {
         if (addressesRecovered.contains(person.getAddress())) {
            // Add person
            PersonStationDTO personStationDto = new PersonStationDTO(
                        person.getFirstName(), person.getLastName(),
                        person.getAddress(), person.getPhone());
            personsUnderResponsibility.add(personStationDto);
            // Count adults & childen
            if (!AgeCalculator.isChild(person)) {
               totalAdultsNumber++;
            } else {
               totalChildrenNumber++;
            }
         }
      }
      PersonStationCounterDTO firestationDto = new PersonStationCounterDTO(
                  personsUnderResponsibility, totalAdultsNumber,
                  totalChildrenNumber);
      return firestationDto;
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
   public List<Person> flood(final String stationsNumber) {
      return null;

   }

   /**
    * @param fireStationNumber
    * @return
    */
   public List<String> phoneAlert(final String fireStationNumber) {
      return null;

   }

}
