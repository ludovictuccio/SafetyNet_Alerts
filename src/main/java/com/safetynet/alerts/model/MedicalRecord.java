package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Ludovic Tuccio
 *
 */
public class MedicalRecord {

   private LocalDate birthdate;
   private List<String> medications;
   private List<String> allergies;

   /**
    * @param birthdate
    * @param medications
    * @param allergies
    */
   public MedicalRecord(LocalDate birthdate, List<String> medications,
               List<String> allergies) {
      super();
      this.birthdate = birthdate;
      this.medications = medications;
      this.allergies = allergies;
   }

   /**
    * @return the birthdate
    */
   public LocalDate getBirthdate() {
      return birthdate;
   }

   /**
    * @param birthdate the birthdate to set
    */
   public void setBirthdate(LocalDate birthdate) {
      this.birthdate = birthdate;
   }

   /**
    * @return the medications
    */
   public List<String> getMedications() {
      return medications;
   }

   /**
    * @param medications the medications to set
    */
   public void setMedications(List<String> medications) {
      this.medications = medications;
   }

   /**
    * @return the allergies
    */
   public List<String> getAllergies() {
      return allergies;
   }

   /**
    * @param allergies the allergies to set
    */
   public void setAllergies(List<String> allergies) {
      this.allergies = allergies;
   }

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
