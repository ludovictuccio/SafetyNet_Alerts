package com.safetynet.alerts.model;

import java.util.List;

/**
 * Households class.
 *
 * @author Ludovic Tuccio
 *
 */
public class Households {

   /**
    * The list of persons by household.
    */
   private List<Person> householdsComposition;

   /**
    * @param personsPerHousehold
    */
   public Households(final List<Person> personsPerHousehold) {
      super();
      this.householdsComposition = personsPerHousehold;
   }

   /**
    * @return householdsComposition
    */
   public List<Person> getPersonsPerHousehold() {
      return householdsComposition;
   }

   /**
    * @param personsPerHousehold the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(final List<Person> personsPerHousehold) {
      this.householdsComposition = personsPerHousehold;
   }

   /**
    * A toString class method().
    */
   public String toString() {
      return "Households composition [Persons per household="
                  + householdsComposition
                  + "]";
   }

}
