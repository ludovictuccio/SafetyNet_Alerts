package com.safetynet.alerts.dto;

/**
 * ChildAlertDTO class.
 *
 * @author Ludovic Tuccio
 */
public class ChildAlertDTO {

   /**
    * FirstName String variable.
    */
   private String firstName;
   /**
    * LastName String variable.
    */
   private String lastName;
   /**
    * Age int variable.
    */
   private int age;

   /**
    * @param personsAge
    * @param personFirstName
    * @param personLastName
    */
   public ChildAlertDTO(final int personsAge, final String personFirstName,
               final String personLastName) {
      this.age = personsAge;
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
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @return age
    */
   public int getAge() {
      return age;
   }

   /**
    * toString() method class.
    */
   @Override
   public String toString() {
      return "ChildAlertDTO"
                  + " [FirstName="
                  + firstName
                  + ", LastName="
                  + lastName
                  + ", Age="
                  + age
                  + " years old"
                  + "]";
   }

}
