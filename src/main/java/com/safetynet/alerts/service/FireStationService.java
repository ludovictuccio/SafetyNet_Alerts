package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.AddressDTO;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.HouseholdsFloodDTO;
import com.safetynet.alerts.dto.PersonFloodDTO;
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
               LOGGER.error("The address entered for: {} already exists for "
                           + "a mapping. Please delete or update a mapping.",
                           firestationMappingToCreate.values());
               return isAdded;
            }
         }
         firestationRecovered.addAddress(newAddress);
         isAdded = true;
         return isAdded;
      } catch (NullPointerException np) {
         throw new NullPointerException(
                     "NullPointerException. Please verify the station number"
                                 + " entered."
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
               isUpdated = true;
               return isUpdated;
            }
         }
         LOGGER.error("The address entered for: {} does not exist.",
                     firestationMappingToCreate.values());
         return isUpdated;
      } catch (NullPointerException np) {
         throw new NullPointerException(
                     "NullPointerException. Please verify the station "
                                 + "number entered."
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
            isDeleted = true;
            return isDeleted;
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
    * This method service is used to retrieve phone number of persons covered by
    * the station.
    *
    * @param firestation the station number
    * @return phoneNumberList
    */
   public List<String> phoneAlert(final String firestation) {
      Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                  .getFirestations();
      Set<String> addressesRecovered = new HashSet<>();

      for (Entry<String, FireStation> entry : allFirestationsMapping
                  .entrySet()) {
         FireStation firestationToRecover = entry.getValue();
         if (firestationToRecover.getStation().toString()
                     .equals(firestation.toString())) {
            addressesRecovered = firestationToRecover.getAddresses();
         }
      }
      List<Person> allPersonsList = entitiesInfosStorage.getPersonsList();
      List<String> phoneNumberList = new ArrayList<>();

      for (Person person : allPersonsList) {
         if (addressesRecovered.contains(person.getAddress())) {
            phoneNumberList.add(person.getPhone());
         }
      }
      return phoneNumberList;
   }

   /**
    * This method service is used to retrieve informations of persons leaving at
    * the address entered, with the firestation number indication.
    *
    * @param address
    * @return fireDtoPersonsList
    */
   public List<FireDTO> fire(final String address) {

      Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                  .getFirestations();
      List<FireDTO> fireDtoPersonsList = new ArrayList<>();

      for (Entry<String, FireStation> entry : allFirestationsMapping
                  .entrySet()) {
         FireStation firestation = entry.getValue();
         if (firestation.getAddresses().contains(address)) {
            String stationNumber = firestation.getStation();

            // Recover persons by household
            Map<String, List<Person>> households = entitiesInfosStorage
                        .getHouseholds();
            for (Entry<String, List<Person>> entryset : households.entrySet()) {
               String householdAddress = entryset.getKey();
               if (!address.equals(householdAddress)) {
                  continue;
               }
               List<Person> householdMembersList = entryset.getValue();
               for (Person person : householdMembersList) {
                  FireDTO fireDtoPerson = new FireDTO(stationNumber,
                              person.getFirstName(), person.getLastName(),
                              person.getMedicalRecord().getAge(),
                              person.getPhone(),
                              person.getMedicalRecord().getMedications(),
                              person.getMedicalRecord().getAllergies());
                  fireDtoPersonsList.add(fireDtoPerson);
               }
            }
         }
      }
      return fireDtoPersonsList;
   }

   /**
    * This method service is used to recover the households served by the
    * station. The list must group persons by address. The information are:
    * name, age, telephone and medical record.
    *
    * @param stations a String list
    * @return floodDtoList a FloodDTO List
    */
   public List<FloodDTO> flood(final List<String> stations) {

      List<FloodDTO> floodDtoList = new ArrayList<>();
      Map<String, FireStation> allFirestationsMapping = entitiesInfosStorage
                  .getFirestations();
      List<Person> allPersonsList = entitiesInfosStorage.getPersonsList();

      for (String station : stations) {

         // Recover all the addresses for the entered stations
         Set<String> addressesRecovered = new HashSet<>();
         for (Entry<String, FireStation> entry : allFirestationsMapping
                     .entrySet()) {
            FireStation firestationToRecover = entry.getValue();
            if (firestationToRecover.getStation().toString()
                        .contains(station.toString())) {
               addressesRecovered = firestationToRecover.getAddresses();
               break;
            }
         }
         if (addressesRecovered.isEmpty()) {
            LOGGER.error("No station founded for {}.", station);
            return null;
         }
         // Group persons leaving at the station addresses by household
         Map<AddressDTO, List<PersonFloodDTO>> householdDTO = new HashMap<>();
         for (Person person : allPersonsList) {

            if (addressesRecovered.contains(person.getAddress())) {
               PersonFloodDTO floodPerson = new PersonFloodDTO(
                           person.getFirstName(), person.getLastName(),
                           person.getPhone(),
                           person.getMedicalRecord().getAge(),
                           person.getMedicalRecord().getMedications(),
                           person.getMedicalRecord().getAllergies());
               AddressDTO addressDto = new AddressDTO(person.getAddress(),
                           person.getCity(), person.getZip());

               boolean isSameHousehold = false;
               for (Entry<AddressDTO, List<PersonFloodDTO>> entry : householdDTO
                           .entrySet()) {
                  if (entry.getKey().getAddress()
                              .equals(addressDto.getAddress())
                              && entry.getKey().getCity()
                                          .equals(addressDto.getCity())
                              && entry.getKey().getZip()
                                          .equals(addressDto.getZip())) {
                     entry.getValue().add(floodPerson);
                     isSameHousehold = true;
                  }
               }
               if (!isSameHousehold) {
                  householdDTO.put(addressDto, new ArrayList<>());
                  for (Entry<AddressDTO, List<PersonFloodDTO>> entry : householdDTO
                              .entrySet()) {
                     if (entry.getKey().getAddress()
                                 .equals(addressDto.getAddress())
                                 && entry.getKey().getCity()
                                             .equals(addressDto.getCity())
                                 && entry.getKey().getZip()
                                             .equals(addressDto.getZip())) {
                        entry.getValue().add(floodPerson);
                     }
                  }
               }
            }
         }
         List<HouseholdsFloodDTO> householdsFloodList = new ArrayList<>();
         for (Entry<AddressDTO, List<PersonFloodDTO>> entry : householdDTO
                     .entrySet()) {
            HouseholdsFloodDTO householdsFloodDTO = new HouseholdsFloodDTO(
                        entry.getKey(), entry.getValue());
            householdsFloodList.add(householdsFloodDTO);
         }
         FloodDTO flood = new FloodDTO(station, householdsFloodList);
         floodDtoList.add(flood);
      }
      return floodDtoList;
   }

}
