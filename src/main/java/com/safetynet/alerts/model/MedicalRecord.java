package com.safetynet.alerts.model;

import java.util.List;

/**
 * MedicalRecord class.
 * 
 * @author Ludovic Tuccio
 *
 */
public class MedicalRecord {

   private String firstName;
   private String lastName;
   /**
    * The person's birthdate.
    */
   private String birthdate;
   /**
    * The person medications list.
    */
   private List<String> medications;
   /**
    * The person allergies list.
    */
   private List<String> allergies;

   /**
    * Empty class constructor.
    */
   public MedicalRecord() {
   }

   /**
    * @param personsBirthdate
    * @param personMedicationsFollowed
    * @param personAllergies
    */
   public MedicalRecord(final String personsBirthdate,
               final List<String> personMedicationsFollowed,
               final List<String> personAllergies) {
      super();
      this.birthdate = personsBirthdate;
      this.medications = personMedicationsFollowed;
      this.allergies = personAllergies;
   }

   /**
    * @param firstName
    * @param lastName
    * @param birthdate
    */
   public MedicalRecord(final String personsFirstName,
               final String personsLastName, final String personsBirthdate) {
      super();
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.birthdate = personsBirthdate;
   }

   // Jsoniterato & any constructor.
   public MedicalRecord(final String personsFirstName,
               final String personsLastName, final String personsBirthdate,
               final List<String> personMedicationsFollowed,
               final List<String> personAllergies) {
      super();
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.birthdate = personsBirthdate;
      this.medications = personMedicationsFollowed;
      this.allergies = personAllergies;
   }

   /**
    * @return the birthdate
    */
   public String getBirthdate() {
      return birthdate;
   }

   /**
    * @param personBirthdate the birthdate to set
    */
   public void setBirthdate(final String personBirthdate) {
      this.birthdate = personBirthdate;
   }

   /**
    * @return the medications
    */
   public List<String> getMedications() {
      return medications;
   }

   /**
    * @param personMedicationsFollowed the medications to set
    */
   public void setMedications(final List<String> personMedicationsFollowed) {
      this.medications = personMedicationsFollowed;
   }

   /**
    * @return the allergies
    */
   public List<String> getAllergies() {
      return allergies;
   }

   /**
    * @param personAllergies the person's allergies to set
    */
   public void setAllergies(final List<String> personAllergies) {
      this.allergies = personAllergies;
   }

   /**
    * A toString class method().
    */
   public String toString() {
      return "Medical Record [birthdate="
                  + birthdate
                  + ", medications="
                  + medications
                  + ", allergies="
                  + allergies
                  + "]";
   }

   public void addAllergies(String string) {
      // TODO Auto-generated method stub

   }

   public void addMedications(String string) {
      // TODO Auto-generated method stub

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

}
