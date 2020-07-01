package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

/**
 * FireStation service class.
 *
 * @author Ludovic Tuccio
 *
 */
@Service
public class FireStationService implements IFireStationService {

   /**
    * Used to retrieve persons informations.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * Public class constructor.
    */
   public FireStationService() {

   }

   /**
    * @param firestation
    * @return
    */
   public EntitiesInfosStorage createFireStation(
               final FireStation firestation) {
      return null;

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

}
