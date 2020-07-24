package com.safetynet.alerts.model;

/**
 * Person class.
 *
 * @author Ludovic Tuccio
 */
public class Person {

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
    * The person's phone number.
    */
   private String phone;
   /**
    * The person's email adress.
    */
   private String email;
   /**
    * The person's medical record.
    */
   private MedicalRecord medicalRecord;

   /**
    * Empty class constructor.
    */
   public Person() {

   }

   /**
    * Constructor used to convert json data in java object.
    *
    * @param personsFirstName
    * @param personsLastName
    * @param personsAdress
    * @param personsCity
    * @param personsZip
    * @param personsPhoneNumber
    * @param personsEmail
    *
    */
   public Person(final String personsFirstName, final String personsLastName,
               final String personsAdress, final String personsCity,
               final String personsZip, final String personsPhoneNumber,
               final String personsEmail) {
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.address = personsAdress;
      this.city = personsCity;
      this.zip = personsZip;
      this.phone = personsPhoneNumber;
      this.email = personsEmail;
   }

   /**
    * Constructor with medicalrecord.
    *
    * @param personsFirstName
    * @param personsLastName
    * @param personsAdress
    * @param personsCity
    * @param personsZip
    * @param personsPhoneNumber
    * @param personsEmail
    * @param personsMedicalRecord
    */
   public Person(final String personsFirstName, final String personsLastName,
               final String personsAdress, final String personsCity,
               final String personsZip, final String personsPhoneNumber,
               final String personsEmail,
               final MedicalRecord personsMedicalRecord) {
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.address = personsAdress;
      this.city = personsCity;
      this.zip = personsZip;
      this.phone = personsPhoneNumber;
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
    * @param personsFirstName the person's first name to set
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
    * @param personsLastName the person's last name to set
    */
   public void setLastName(final String personsLastName) {
      this.lastName = personsLastName;
   }

   /**
    * @return the adress
    */
   public String getAddress() {
      return address;
   }

   /**
    * @param personsAddress the person's adress to set
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
    * @param personsCity the person's city to set
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
    * @return the phone
    */
   public String getPhone() {
      return phone;
   }

   /**
    * @param personsPhoneNumber the person's phone number to set
    */
   public void setPhone(final String personsPhoneNumber) {
      this.phone = personsPhoneNumber;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param personsEmail the person's email to set
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
    * @param personsMedicalRecord the person's medical record to set
    */
   public void setMedicalRecord(final MedicalRecord personsMedicalRecord) {
      this.medicalRecord = personsMedicalRecord;
   }

}
