package com.safetynet.alerts.model;

import java.util.HashSet;

/**
 * @author Ludovic Tuccio
 *
 */
public class FireStation {

   private HashSet<String> adresses;
   private int station;

   /**
    * @param adresses
    * @param station
    */
   public FireStation(HashSet<String> adresses, int station) {
      super();
      this.adresses = adresses;
      this.station = station;
   }

   /**
    * @return the adresses
    */
   public HashSet<String> getAdresses() {
      return adresses;
   }

   /**
    * @param adresses the adresses to set
    */
   public void setAdresses(HashSet<String> adresses) {
      this.adresses = adresses;
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
      return "FireStation [adresses="
                  + adresses
                  + ", station="
                  + station
                  + "]";
   }

}
