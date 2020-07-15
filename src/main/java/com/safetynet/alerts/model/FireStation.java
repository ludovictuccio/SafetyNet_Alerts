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
   private Set<String> addresses = new HashSet<>();
   /**
    * The fire station number.
    */
   private String station;

   /**
    * Empty class constructor.
    */
   public FireStation() {

   }

   /**
    * Class constructor.
    *
    * @param stationNumber
    */
   public FireStation(final String stationNumber) {
      this.station = stationNumber;
   }

   /**
    * @param newAddress
    */
   public void addAddress(final String newAddress) {
      addresses.add(newAddress);
   }

   /**
    * @return the adresses
    */
   public Set<String> getAdresses() {
      return addresses;
   }

   /**
    * @return the station number
    */
   public String getStation() {
      return station;
   }

   /**
    * A toString class method().
    */
   public String toString() {
      return "FireStation [addresses="
                  + addresses
                  + ", station="
                  + station
                  + "]";
   }

}
