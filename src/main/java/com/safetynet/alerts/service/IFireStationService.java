package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

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
   boolean addAddressForFirestation(Map<String, String> firestationToCreate);

   /**
    * @param firestationToUpdate
    */
   boolean updateFireStation(Map<String, String> firestationToUpdate);

   /**
    * @param firestationNumber
    * @param firestationAdress
    */
   boolean deleteFireStation(String firestationNumber,
               String firestationAdress);

   /**
    * @param stationNumber
    * @return
    */
   List<Person> firestationNumber(String stationNumber);

   /**
    * @param householdAdress
    */
   List<Person> fire(String householdAdress);

   /**
    * @param stationsNumber
    */
   List<Person> flood(String stationsNumber);

   /**
    * @param fireStationNumber
    */
   List<String> phoneAlert(String fireStationNumber);

}
