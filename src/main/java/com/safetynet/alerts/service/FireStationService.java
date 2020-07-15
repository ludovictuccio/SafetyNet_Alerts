package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

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
            if (firestationsNumber.getAdresses().toString()
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
    * @param firestationNumber
    * @param firestationAdress
    * @return
    */
   public Map<Integer, FireStation> updateFireStation(
               final int firestationNumber, final String firestationAdress) {
      return null;

   }

   /**
    * @param firestationNumber
    * @param firestationAdress
    * @return
    */
   public Map<Integer, FireStation> deleteFireStation(
               final int firestationNumber, final String firestationAdress) {
      return null;

   }

   /**
    * @param stationNumber
    * @return
    */
   public List<Person> firestationNumber(final int stationNumber) {
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

}
