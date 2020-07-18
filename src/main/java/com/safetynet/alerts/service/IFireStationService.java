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
    * @return
    */
   boolean addAddressForFirestation(Map<String, String> firestationToCreate);

   /**
    * @param firestationToUpdate
    * @return
    */
   boolean updateAddressForFireStation(Map<String, String> firestationToUpdate);

   /**
    * @param address
    * @return
    */
   boolean deleteAddressForFireStation(String address);

   /**
    * @param stationNumber
    * @return
    */
   PersonStationCounterDTO firestationNumber(String stationNumber);

   /**
    * @param firestation
    * @return
    */
   List<String> phoneAlert(String firestation);

   /**
    * @param address
    * @return
    */
   List<FireDTO> fire(String address);

   /**
    * @param stations
    * @return
    */
   List<FloodDTO> flood(List<String> stations);

}
