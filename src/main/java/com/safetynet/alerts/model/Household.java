package com.safetynet.alerts.model;

/**
 * Households class.
 *
 * @author Ludovic Tuccio
 */
public class Household {

   private String firstName;
   private String lastName;
   private int age;

   /**
    * @param childAge
    * @param personFirstName
    * @param personLastName
    */
   public Household(final int childAge, final String personFirstName,
               final String personLastName) {
      this.age = childAge;
      this.firstName = personFirstName;
      this.lastName = personLastName;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(final String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(final String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return age
    */
   public int getAge() {
      return age;
   }

   /**
    * @param personAge
    */
   public void setAge(final int personAge) {
      this.age = personAge;
   }

   public String toString() {
      return "Households"
                  + " [FirstName="
                  + firstName
                  + ", LastName="
                  + lastName
                  + ", Age="
                  + age
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
