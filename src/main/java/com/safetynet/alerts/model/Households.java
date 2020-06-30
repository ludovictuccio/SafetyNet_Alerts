package com.safetynet.alerts.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   private Map<String, List<Person>> householdsComposition = new HashMap<>();

   /**
    * @param personsPerHousehold
    */
   public Households(final Map<String, List<Person>> personsPerHousehold) {
      this.householdsComposition = personsPerHousehold;
   }

   /**
    * @return householdsComposition
    */
   public Map<String, List<Person>> getPersonsPerHousehold() {
      return householdsComposition;
   }

   /**
    * @param personsPerHousehold the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(
               final Map<String, List<Person>> personsPerHousehold) {
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
