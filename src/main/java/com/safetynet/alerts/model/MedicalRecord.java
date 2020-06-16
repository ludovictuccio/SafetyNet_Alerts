package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.List;

/**
 * MedicalRecord class.
 * 
 * @author Ludovic Tuccio
 *
 */
public class MedicalRecord {

   /**
    * The person's birthdate.
    */
   private LocalDate birthdate;
   /**
    * The person medications list.
    */
   private List<String> medications;
   /**
    * The person allergies list.
    */
   private List<String> allergies;

   /**
    * @param personsBirthdate
    * @param personMedicationsFollowed
    * @param personAllergies
    */
   public MedicalRecord(final LocalDate personsBirthdate,
               final List<String> personMedicationsFollowed,
               final List<String> personAllergies) {
      super();
      this.birthdate = personsBirthdate;
      this.medications = personMedicationsFollowed;
      this.allergies = personAllergies;
   }

   /**
    * @return the birthdate
    */
   public LocalDate getBirthdate() {
      return birthdate;
   }

   /**
    * @param personsBirthdate the birthdate to set
    */
   public void setBirthdate(final LocalDate personsBirthdate) {
      this.birthdate = personsBirthdate;
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

}
