package com.safetynet.alerts.model;

import java.util.HashSet;
import java.util.Set;

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
   private Set<String> adresses = new HashSet<>();
   /**
    * The fire station number.
    */
   private int station;

   public FireStation() {

   }

   /**
    *
    * @param stationNumber
    */
   public FireStation(final int stationNumber) {
      this.station = stationNumber;
   }

   public void addAddress(final String newAdress) {
      adresses.add(newAdress);
   }

   /**
    * @return the adresses
    */
   public Set<String> getAdresses() {
      return adresses;
   }

//   /**
//    * @param stationsAdresses the stations adresses to set
//    */
//   public void setAdresses(final String stationsAdresses) {
//      adresses.add(stationsAdresses);
//   }

   /**
    * @return the station
    */
   public int getStation() {
      return station;
   }
//
//   /**
//    * @param stationNumber the station number to set
//    */
//   public void setStation(final int stationNumber) {
//      this.station = stationNumber;
//   }

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
