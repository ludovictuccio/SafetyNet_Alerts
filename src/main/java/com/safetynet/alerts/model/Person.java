package com.safetynet.alerts.model;

/**
 * Person class.
 *
 * @author Ludovic Tuccio
 *
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
    * The person's age .
    */
   private int age;
   /**
    * The person's adress.
    */
   private String adress;
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

//   /**
//    * Empty class constructor;
//    */
//   public Person() {
//
//   }
   /**
    *
    * @param personsFirstName
    * @param personsLastName
    * @param personsAdress
    * @param personsPhoneNumber
    * @param personsEmail
    * @param personsMedicalRecord
    */
   public Person(final String personsFirstName, final String personsLastName,
               final String personsAdress, final String personsPhoneNumber,
               final String personsEmail,
               final MedicalRecord personsMedicalRecord) {
      super();
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.adress = personsAdress;
      this.phone = personsPhoneNumber;
      this.email = personsEmail;
      this.medicalRecord = personsMedicalRecord;
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
      super();
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.adress = personsAdress;
      this.city = personsCity;
      this.zip = personsZip;
      this.phone = personsPhoneNumber;
      this.email = personsEmail;
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
    * @return the age
    */
   public int getAge() {
      return age;
   }

   /**
    * @param age the person's age to set
    */
   public void setAge(final int personsAge) {
      this.age = personsAge;
   }

   /**
    * @return the adress
    */
   public String getAdress() {
      return adress;
   }

   /**
    * @param personsAdress the person's adress to set
    */
   public void setAdress(final String personsAdress) {
      this.adress = personsAdress;
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
    * @param zip the zip to set
    */
   public void setZip(String zip) {
      this.zip = zip;
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

   /**
    * The toString() method class.
    */
   public String toString() {
      return "Person [firstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", adress="
                  + adress
                  + ", phone="
                  + phone
                  + ", email="
                  + email
                  + ", medicalRecord="
                  + medicalRecord
                  + "]";
   }

}
