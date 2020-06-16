package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.AdultsAndChildrenPerStation;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.InfosRetrieval;
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
   private InfosRetrieval infosRetrieval;
   /**
    * Used to retrieve firestations informations.
    */
   private IFireStationService fireStationService;

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
