package com.safetynet.alerts.model;

import java.util.List;

/**
 * AdultsAndChildrenPerStation class.
 *
 * @author Ludovic Tuccio
 *
 */
public class AdultsAndChildrenPerStation {

   /**
    * The list of persons per station.
    */
   private List<Person> personsPerStation;

   /**
    * The station number.
    */
   private int station;

   /**
    * @param personsPerStationList
    * @param firestationNumber
    */
   public AdultsAndChildrenPerStation(final List<Person> personsPerStationList,
               final int firestationNumber) {
      super();
      this.personsPerStation = personsPerStationList;
      this.station = firestationNumber;
   }

   /**
    * @return the personsPerStation
    */
   public List<Person> getPersonsPerStation() {
      return personsPerStation;
   }

   /**
    * @param personsPerStationList the personsPerStation to set
    */
   public void setPersonsPerStation(final List<Person> personsPerStationList) {
      this.personsPerStation = personsPerStationList;
   }

   /**
    * @return the station
    */
   public int getStation() {
      return station;
   }

   /**
    * @param stationNumber the station number to set
    */
   public void setStation(final int stationNumber) {
      this.station = stationNumber;
   }

   /**
    * A toString class method().
    */
   public String toString() {
      return "Adults and children per station [Persons per station="
                  + personsPerStation
                  + ", station="
                  + station
                  + "]";
   }

}
