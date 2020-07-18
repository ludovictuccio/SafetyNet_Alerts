package com.safetynet.alerts.dto;

import java.util.List;

/**
 * FloodDTO class.
 *
 * @author Ludovic Tuccio
 */
public class FloodDTO {

   /**
    * The station number.
    */
   private String station;

   /**
    * The households list covered by the station.
    */
   private List<HouseholdsFloodDTO> householdsList;

   /**
    * @param firestation
    * @param households
    */
   public FloodDTO(final String firestation,
               final List<HouseholdsFloodDTO> households) {
      this.station = firestation;
      this.householdsList = households;
   }

   /**
    * @return the station
    */
   public String getStationNumber() {
      return station;
   }

   /**
    * @return the householdList
    */
   public List<HouseholdsFloodDTO> getHouseholdList() {
      return householdsList;
   }

   /**
    * toString method class.
    */
   @Override
   public String toString() {
      return "FloodDTO [station="
                  + station
                  + ", householdsList="
                  + householdsList
                  + "]";
   }

}