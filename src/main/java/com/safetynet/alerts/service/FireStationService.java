package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.AdultsAndChildrenPerStation;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.InfosRetrieval;
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
   private InfosRetrieval infosRetrieval;

   /**
    * Public class constructor.
    */
   public FireStationService() {

   }

   /**
    * @param firestation
    * @return
    */
   public InfosRetrieval createFireStation(final FireStation firestation) {
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
    * @return
    */
   public AdultsAndChildrenPerStation stationNumber() {
      return null;

   }

   /**
    * @return
    */
   public List<Person> fire() {
      return null;

   }

   /**
    * @return
    */
   public List<Person> flood() {
      return null;

   }

}
