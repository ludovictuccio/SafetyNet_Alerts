package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IFireStationService;

/**
 * FireStation controller class.
 *
 * @author Ludovic Tuccio
 *
 */
public class FireStationController {

   /**
    * Used to retrieve persons informations.
    */
   private EntitiesInfosStorage entitiesInfosStorage;
   /**
    * Used to retrieve firestations informations.
    */
   private IFireStationService fireStationService;

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
