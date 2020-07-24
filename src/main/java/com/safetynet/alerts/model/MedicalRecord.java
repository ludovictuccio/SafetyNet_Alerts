package com.safetynet.alerts.model;

import java.util.List;

import com.safetynet.alerts.util.AgeCalculator;

/**
 * MedicalRecord model class.
 *
 * @author Ludovic Tuccio
 */
public class MedicalRecord {

   /**
    * The person's first name.
    */
   private String firstName;
   /**
    * The person's last name.
    */
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
    * @param personsFirstName
    * @param personsLastName
    * @param personsBirthdate
    * @param personMedicationsFollowed
    * @param personAllergies
    */
   public MedicalRecord(final String personsFirstName,
               final String personsLastName, final String personsBirthdate,
               final List<String> personMedicationsFollowed,
               final List<String> personAllergies) {
      this.firstName = personsFirstName;
      this.lastName = personsLastName;
      this.birthdate = personsBirthdate;
      this.medications = personMedicationsFollowed;
      this.allergies = personAllergies;
   }

   /**
    * @param personsBirthdate
    * @param personMedicationsFollowed
    * @param personAllergies
    */
   public MedicalRecord(final String personsBirthdate,
               final List<String> personMedicationsFollowed,
               final List<String> personAllergies) {
      this.birthdate = personsBirthdate;
      this.medications = personMedicationsFollowed;
      this.allergies = personAllergies;
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
    * @return personsAge
    */
   public int getAge() {
      int personsAge = AgeCalculator.ageCalculation(this.birthdate);
      return personsAge;
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

}
