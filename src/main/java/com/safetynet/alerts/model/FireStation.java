package com.safetynet.alerts.model;

import java.util.HashSet;

/**
 * FireStation class.
 *
 * @author Ludovic Tuccio
 *
 */
public class FireStation {

   /**
    * A String HashSet used to group adresses under responsibility.
    */
   private HashSet<String> adresses;
   /**
    * The fire station number.
    */
   private int station;

   /**
    * @param stationsAdresses
    * @param stationNumber
    */
   public FireStation(final HashSet<String> stationsAdresses,
               final int stationNumber) {
      super();
      this.adresses = stationsAdresses;
      this.station = stationNumber;
   }

   /**
    * @return the adresses
    */
   public HashSet<String> getAdresses() {
      return adresses;
   }

   /**
    * @param stationsAdresses the stations adresses to set
    */
   public void setAdresses(final HashSet<String> stationsAdresses) {
      this.adresses = stationsAdresses;
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
      return "FireStation [adresses="
                  + adresses
                  + ", station="
                  + station
                  + "]";
   }

}
