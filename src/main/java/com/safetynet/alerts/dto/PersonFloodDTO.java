package com.safetynet.alerts.dto;

import java.util.List;

/**
 * PersonFloodDTO class.
 *
 * @author Ludovic Tuccio
 */
public class PersonFloodDTO {

   /**
    * The person's first name.
    */
   private String firstName;
   /**
    * The person's last name.
    */
   private String lastName;
   /**
    * The person's phone number.
    */
   private String phone;
   /**
    * The person's age.
    */
   private int age;
   /**
    * The person's medications list.
    */
   private List<String> medications;
   /**
    * The person's allergies list.
    */
   private List<String> allergies;

   /**
    * @param pFirstName
    * @param plastName
    * @param pphoneNumber
    * @param pAge
    * @param pMedications
    * @param pAllergies
    */
   public PersonFloodDTO(final String pFirstName, final String plastName,
               final String pphoneNumber, final int pAge,
               final List<String> pMedications, final List<String> pAllergies) {
      this.firstName = pFirstName;
      this.lastName = plastName;
      this.phone = pphoneNumber;
      this.age = pAge;
      this.medications = pMedications;
      this.allergies = pAllergies;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @return the medications
    */
   public List<String> getMedications() {
      return medications;
   }

   /**
    * @return the allergies
    */
   public List<String> getAllergies() {
      return allergies;
   }

   /**
    * @return the age
    */
   public int getAge() {
      return age;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @return the phone number
    */
   public String getPhoneNumber() {
      return phone;
   }

   /**
    * toString method class.
    */
   @Override
   public String toString() {
      return "PersonFloodDTO [firstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", phone="
                  + phone
                  + ", age="
                  + age
                  + ", medications="
                  + medications
                  + ", allergies="
                  + allergies
                  + "]";
   }

}
