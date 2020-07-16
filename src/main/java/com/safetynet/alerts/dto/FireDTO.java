package com.safetynet.alerts.dto;

import java.util.List;

/**
 * FireDTO class.
 *
 * @author Ludovic Tuccio
 */
public class FireDTO {

   /**
    * The firestation number responsible.
    */
   private String stationNumber;
   /**
    * The person's first name.
    */
   private String firstName;
   /**
    * The person's last name.
    */
   private String lastName;
   /**
    * The person's age.
    */
   private int age;
   /**
    * The person's phone number.
    */
   private String phoneNumber;
   /**
    * The person's medications list.
    */
   private List<String> medications;
   /**
    * The person's allergies list.
    */
   private List<String> allergies;

   /**
    * @param stationNumber
    * @param firstName
    * @param lastName
    * @param phoneNumber
    * @param medicalrecord
    */
   public FireDTO(final String firestationNumber, final String pfirstName,
               final String plastName, final int pAge,
               final String pphoneNumber, final List<String> pMedications,
               final List<String> pAllergies) {
      this.stationNumber = firestationNumber;
      this.firstName = pfirstName;
      this.age = pAge;
      this.lastName = plastName;
      this.phoneNumber = pphoneNumber;
      this.medications = pMedications;
      this.allergies = pAllergies;
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
    * @return the stationNumber
    */
   public String getStationNumber() {
      return stationNumber;
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
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   /**
    * toString method class.
    */
   public String toString() {
      return "FireDTO"
                  + stationNumber
                  + "[firstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", age="
                  + age
                  + ", phoneNumber="
                  + phoneNumber
                  + ", medications="
                  + medications
                  + ", allergies="
                  + allergies
                  + "]";
   }
}
