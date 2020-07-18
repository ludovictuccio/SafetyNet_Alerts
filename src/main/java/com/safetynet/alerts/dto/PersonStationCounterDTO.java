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
    * @param totalAdults
    * @param totalChildren
    */
   public PersonStationCounterDTO(final List<PersonStationDTO> persons,
               final int totalAdults, final int totalChildren) {
      this.personsStationList = persons;
      this.totalAdultsNumber = totalAdults;
      this.totalChildrenNumber = totalChildren;
   }

   /**
    * @return the personsStationList
    */
   public List<PersonStationDTO> getPersonsStationList() {
      return personsStationList;
   }

   /**
    * @param persons the personsStationList to set
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
   @Override
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
