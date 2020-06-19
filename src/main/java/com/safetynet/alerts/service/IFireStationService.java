package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

/**
 * FireStation service interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IFireStationService {

   /**
    * createFireStation() method.
    * 
    * @param firestation
    */
   EntitiesInfosStorage createFireStation(FireStation firestation);

   /**
    * updateFireStation() method.
    * 
    * @param firestationNumber
    * @param firestationAdress
    */
   Map<Integer, FireStation> updateFireStation(int firestationNumber,
               String firestationAdress);

   /**
    * deleteFireStation() method.
    * 
    * @param firestationNumber
    * @param firestationAdress
    */
   Map<Integer, FireStation> deleteFireStation(int firestationNumber,
               String firestationAdress);

   /**
    * stationNumber() method.
    */
   List<Person> stationNumber(int stationNumber);

   /**
    * fire() method.
    */
   List<Person> fire(String householdAdress);

   /**
    * flood() method.
    */
   List<Person> flood(int stationsNumber);

   /**
    * @param fireStationNumber
    * 
    */
   List<String> phoneAlert(int fireStationNumber);

}
