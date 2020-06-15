package com.safetynet.alerts.model;

/**
 * @author Ludovic Tuccio
 *
 */
public class Person {

   private String firstName;
   private String lastName;
   private int age;
   private String adress;
   private String city;
   private int zip;
   private String phone;
   private String email;
   private MedicalRecord medicalRecord;

   /**
    * @param firstName
    * @param lastName
    * @param age
    * @param adress
    * @param city
    * @param zip
    * @param phone
    * @param email
    * @param medicalRecord
    */
   public Person(String firstName, String lastName, int age, String adress,
               String city, int zip, String phone, String email,
               MedicalRecord medicalRecord) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.adress = adress;
      this.city = city;
      this.zip = zip;
      this.phone = phone;
      this.email = email;
      this.medicalRecord = medicalRecord;
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
   public void setFirstName(String firstName) {
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
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the age
    */
   public int getAge() {
      return age;
   }

   /**
    * @param age the age to set
    */
   public void setAge(int age) {
      this.age = age;
   }

   /**
    * @return the adress
    */
   public String getAdress() {
      return adress;
   }

   /**
    * @param adress the adress to set
    */
   public void setAdress(String adress) {
      this.adress = adress;
   }

   /**
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * @param city the city to set
    */
   public void setCity(String city) {
      this.city = city;
   }

   /**
    * @return the zip
    */
   public int getZip() {
      return zip;
   }

   /**
    * @param zip the zip to set
    */
   public void setZip(int zip) {
      this.zip = zip;
   }

   /**
    * @return the phone
    */
   public String getPhone() {
      return phone;
   }

   /**
    * @param phone the phone to set
    */
   public void setPhone(String phone) {
      this.phone = phone;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param email the email to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * @return the medicalRecord
    */
   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   /**
    * @param medicalRecord the medicalRecord to set
    */
   public void setMedicalRecord(MedicalRecord medicalRecord) {
      this.medicalRecord = medicalRecord;
   }

   public String toString() {
      return "Person [firstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", age="
                  + age
                  + ", adress="
                  + adress
                  + ", city="
                  + city
                  + ", zip="
                  + zip
                  + ", phone="
                  + phone
                  + ", email="
                  + email
                  + ", medicalRecord="
                  + medicalRecord
                  + "]";
   }

}
