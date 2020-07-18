package com.safetynet.alerts.model;

import java.util.List;

/**
 * Household class.
 *
 * @author Ludovic Tuccio
 */
public class Household {

   /**
    * The list of persons by household.
    */
   private List<Person> householdComposition;

   /**
    * @param personsPerHousehold
    */
   public Household(final List<Person> personsPerHousehold) {
      this.householdComposition = personsPerHousehold;
   }

   /**
    * @return householdsComposition
    */
   public List<Person> getPersonsPerHousehold() {
      return householdComposition;
   }

   /**
    * @param personsPerHousehold the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(final List<Person> personsPerHousehold) {
      this.householdComposition = personsPerHousehold;
   }

   /**
    * A toString class method().
    */
   @Override
   public String toString() {
      return "Household [Household composition="
                  + householdComposition
                  + "]";
   }

}
