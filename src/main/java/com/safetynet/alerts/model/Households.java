package com.safetynet.alerts.model;

import java.util.List;

/**
 * @author Ludovic Tuccio
 *
 */
public class Households {

   private List<Person> personsPerHousehold;

   /**
    * @param personsPerHousehold
    */
   public Households(List<Person> personsPerHousehold) {
      super();
      this.personsPerHousehold = personsPerHousehold;
   }

   /**
    * @return the personsPerHousehold
    */
   public List<Person> getPersonsPerHousehold() {
      return personsPerHousehold;
   }

   /**
    * @param personsPerHousehold the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(List<Person> personsPerHousehold) {
      this.personsPerHousehold = personsPerHousehold;
   }

   public String toString() {
      return "Households composition [Persons per household="
                  + personsPerHousehold
                  + "]";
   }

}
