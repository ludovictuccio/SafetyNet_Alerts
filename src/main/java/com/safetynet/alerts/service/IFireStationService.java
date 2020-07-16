package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.dto.PersonStationCounterDTO;
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
   boolean updateAddressForFireStation(Map<String, String> firestationToUpdate);

   /**
    * @param address
    */
   boolean deleteAddressForFireStation(String address);

   /**
    * @param stationNumber
    */
   PersonStationCounterDTO firestationNumber(String stationNumber);

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
