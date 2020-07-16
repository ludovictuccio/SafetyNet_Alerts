package com.safetynet.alerts.dto;

import java.util.List;

/**
 * PersonStationCounterDTO class.
 *
 * @author Ludovic Tuccio
 */
public class PersonStationCounterDTO {

   /**
    * The list of persons covered by the firestation.
    */
   private List<PersonStationDTO> personsStationList;
   /**
    * The total adults number(int).
    */
   private int totalAdultsNumber;
   /**
    * The total adults number (int).
    */
   private int totalChildrenNumber;

   /**
    * @param persons
    * @param totalAdultsNumber
    * @param totalChildrenNumber
    */
   public PersonStationCounterDTO(List<PersonStationDTO> persons,
               int totalAdultsNumber, int totalChildrenNumber) {
      this.personsStationList = persons;
      this.totalAdultsNumber = totalAdultsNumber;
      this.totalChildrenNumber = totalChildrenNumber;
   }

   /**
    * @return the personsStationList
    */
   public List<PersonStationDTO> getPersonsStationList() {
      return personsStationList;
   }

   /**
    * @param personsStationList the personsStationList to set
    */
   public void setPersonsStationList(final List<PersonStationDTO> persons) {
      this.personsStationList = persons;
   }

   /**
    * @return totalAdultsNumber
    */
   public int getTotalAdultsNumber() {
      return totalAdultsNumber;
   }

   /**
    * @param adultsNumber
    */
   public void setTotalAdultsNumber(final int adultsNumber) {
      this.totalAdultsNumber = adultsNumber;
   }

   /**
    * @return totalChildrenNumber
    */
   public int getTotalChildrenNumber() {
      return totalChildrenNumber;
   }

   /**
    * @param childrenNumber
    */
   public void setTotalChildrenNumber(final int childrenNumber) {
      this.totalChildrenNumber = childrenNumber;
   }

   /**
    * toString method class.
    */
   public String toString() {
      return "PersonStationCounterDTO [Persons="
                  + personsStationList
                  + " , Total adults="
                  + totalAdultsNumber
                  + ", Total children="
                  + totalChildrenNumber
                  + "]";
   }

}
