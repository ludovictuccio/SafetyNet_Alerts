package com.safetynet.alerts.model;

import java.util.List;

/**
 * Households class.
 *
 * @author Ludovic Tuccio
 */
public class Household {

   /**
    * The list of persons by household.
    */
   private List<Person> householdsComposition;

//   private int totalAdultsNumber;
//   private int totalChildrenNumber;

   /**
    * @param personsPerHousehold
    */
   public Household(final List<Person> personsPerHousehold) {
      this.householdsComposition = personsPerHousehold;
   }

   /**
    * @return the personsPerHousehold
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
      return "Household [Household composition="
                  + householdsComposition
                  + "]";
   }

//   /**
//    * @return the address
//    */
//   public String getAddress() {
//      return address;
//   }
//
//   /**
//    * @param address the address to set
//    */
//   public void setAddress(String address) {
//      this.address = address;
//   }

//   public Households(int childAge, String pfirstName, String plastName,
//               String paddress) {
//      this.age = childAge;
//      this.firstName = pfirstName;
//      this.lastName = plastName;
//      this.address = paddress;
//
//   }
//   public Person getChild() {
//      return child;
//   }
//
//   public void setChild(final Person personChild) {
//      this.child = personChild;
//   }

//   public List<Person> getHouseholdMembers() {
//      return householdMembers;
//   }
//
//   public void setHouseholdMembers(final List<Person> householdMembersList) {
//      this.householdMembers = householdMembersList;
//   }
//
//   /**
//    * @return householdsComposition
//    */
//   public Map<String, List<Person>> getPersonsPerHousehold() {
//      return householdsComposition;
//   }
//
//   /**
//    * @param personsPerHousehold the personsPerHousehold to set
//    */
//   public void setPersonsPerHousehold(
//               final Map<String, List<Person>> personsPerHousehold) {
//      this.householdsComposition = personsPerHousehold;
//   }

}
