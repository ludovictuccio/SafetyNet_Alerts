package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.PersonStationCounterDTO;

/**
 * FireStation service interface.
 *
 * @author Ludovic Tuccio
 */
public interface IFireStationService {

   /**
    * @param firestationToCreate
    * @return a boolean
    */
   boolean addAddressForFirestation(Map<String, String> firestationToCreate);

   /**
    * @param firestationToUpdate
    * @return a boolean
    */
   boolean updateAddressForFireStation(Map<String, String> firestationToUpdate);

   /**
    * @param address
    * @return a boolean
    */
   boolean deleteAddressForFireStation(String address);

   /**
    * @param stationNumber
    * @return a PersonStationCounterDTO object
    */
   PersonStationCounterDTO firestationNumber(String stationNumber);

   /**
    * @param firestation
    * @return a String list of phone numbers
    */
   List<String> phoneAlert(String firestation);

   /**
    * @param address
    * @return a FireDTO list
    */
   List<FireDTO> fire(String address);

   /**
    * @param stations
    * @return a FloodDTO list
    */
   List<FloodDTO> flood(List<String> stations);

}
