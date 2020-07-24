package com.safetynet.alerts.dto;

import java.util.List;

/**
 * PersonInfoDTO class.
 *
 * @author Ludovic Tuccio
 */
public class PersonInfoDTO {

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
    * The person's adress.
    */
   private String address;
   /**
    * The person's city.
    */
   private String city;
   /**
    * The person's zip.
    */
   private String zip;
   /**
    * The person's email adress.
    */
   private String email;
   /**
    * The person's medications list.
    */
   private List<String> medications;
   /**
    * The person's allergies list.
    */
   private List<String> allergies;

   /**
    * @param personsFirstName
    * @param personsLastName
    * @param pAge
    * @param personsAddress
    * @param personsCity
    * @param personsZip
    * @param personsEmail
    * @param pMedications
    * @param pAllergies
    */
   public PersonInfoDTO(final String personsFirstName,
               final String personsLastName, final int pAge,
               final String personsAddress, final String personsCity,
               final String personsZip, final String personsEmail,
               final List<String> pMedications, final List<String> pAllergies) {
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.age = pAge;
      this.address = personsAddress;
      this.city = personsCity;
      this.zip = personsZip;
      this.email = personsEmail;
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
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * @return the zip
    */
   public String getZip() {
      return zip;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

}
