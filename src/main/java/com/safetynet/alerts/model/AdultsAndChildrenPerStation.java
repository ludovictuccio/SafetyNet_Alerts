package com.safetynet.alerts.model;

import java.util.List;

/**
 * @author Ludovic Tuccio
 *
 */
public class AdultsAndChildrenPerStation {

   private List<Person> personsPerStation;
   private int station;

   /**
    * @param personsPerStation
    * @param station
    */
   public AdultsAndChildrenPerStation(List<Person> personsPerStation,
               int station) {
      super();
      this.personsPerStation = personsPerStation;
      this.station = station;
   }

   /**
    * @return the personsPerStation
    */
   public List<Person> getPersonsPerStation() {
      return personsPerStation;
   }

   /**
    * @param personsPerStation the personsPerStation to set
    */
   public void setPersonsPerStation(List<Person> personsPerStation) {
      this.personsPerStation = personsPerStation;
   }

   /**
    * @return the station
    */
   public int getStation() {
      return station;
   }

   /**
    * @param station the station to set
    */
   public void setStation(int station) {
      this.station = station;
   }

   public String toString() {
      return "Adults and children per station [Persons per station="
                  + personsPerStation
                  + ", station="
                  + station
                  + "]";
   }

}
