package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * PersonInfoDTO class.
 *
 * @author Ludovic Tuccio
 */
public class PersonInfoDTO {

// int age ? dans medicalrecord ?

   /**
    * The person's first name.
    */
   private String firstName;
   /**
    * The person's last name.
    */
   private String lastName;
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
    * The person's medical record.
    */
   private MedicalRecord medicalRecord;

   public PersonInfoDTO(final String personsFirstName,
               final String personsLastName, final String personsAddress,
               final String personsCity, final String personsZip,
               final String personsEmail,
               final MedicalRecord personsMedicalRecord) {
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.address = personsAddress;
      this.city = personsCity;
      this.zip = personsZip;
      this.email = personsEmail;
      this.medicalRecord = personsMedicalRecord;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param personsFirstName the firstName to set
    */
   public void setFirstName(final String personsFirstName) {
      this.firstName = personsFirstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param personsLastName the lastName to set
    */
   public void setLastName(final String personsLastName) {
      this.lastName = personsLastName;
   }

   /**
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @param personsAddress the address to set
    */
   public void setAddress(final String personsAddress) {
      this.address = personsAddress;
   }

   /**
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * @param personsCity the city to set
    */
   public void setCity(final String personsCity) {
      this.city = personsCity;
   }

   /**
    * @return the zip
    */
   public String getZip() {
      return zip;
   }

   /**
    * @param personsZip the zip to set
    */
   public void setZip(final String personsZip) {
      this.zip = personsZip;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param personsEmail the email to set
    */
   public void setEmail(final String personsEmail) {
      this.email = personsEmail;
   }

   /**
    * @return the medicalRecord
    */
   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   /**
    * @param personsMedicalRecord the medicalRecord to set
    */
   public void setMedicalRecord(final MedicalRecord personsMedicalRecord) {
      this.medicalRecord = personsMedicalRecord;
   }

   public String toString() {
      return "PersonInfoDTO [FirstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", address="
                  + address
                  + ", city="
                  + city
                  + ", zip="
                  + zip
                  + ", email="
                  + email
                  + ", medicalRecord="
                  + medicalRecord
                  + "]";
   }

}
