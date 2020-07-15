package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

/**
 * FireStation service interface.
 *
 * @author Ludovic Tuccio
 */
public interface IFireStationService {

   /**
    * @param firestationToCreate
    */
   boolean addAddressForFirestation(
               final Map<String, String> firestationToCreate);

   /**
    * @param firestationNumber
    * @param firestationAdress
    */
   Map<Integer, FireStation> updateFireStation(int firestationNumber,
               String firestationAdress);

   /**
    * @param firestationNumber
    * @param firestationAdress
    */
   Map<Integer, FireStation> deleteFireStation(int firestationNumber,
               String firestationAdress);

   /**
    * @param stationNumber
    * @return
    */
   List<Person> firestationNumber(int stationNumber);

   /**
    * @param householdAdress
    */
   List<Person> fire(String householdAdress);

   /**
    * @param stationsNumber
    */
   List<Person> flood(int stationsNumber);

   /**
    * @param fireStationNumber
    */
   List<String> phoneAlert(int fireStationNumber);

}
