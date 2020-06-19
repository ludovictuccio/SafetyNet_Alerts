package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * @author Ludovic Tuccio
 *
 */
public class AgeCalculator {

   private static final Logger logger = LogManager
               .getLogger(AgeCalculator.class);
   private LocalDate currentDate = LocalDate.now();
   private MedicalRecord medicalRecord = new MedicalRecord();
   private LocalDate birthDate = medicalRecord.getBirthdate();
   private int age;
   private DateTimeFormatter formatter = DateTimeFormatter
               .ofPattern("dd/MM/yyyy");
   private LocalDate date = birthDate;

   public AgeCalculator() {

   }

   public AgeCalculator(int localDate) {
      this.age = localDate;
   }

   public int getAge() {
      return age;
   }

   public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
      if ((birthDate != null) && (currentDate != null)) {
         return Period.between(birthDate, currentDate).getYears();
      } else {
         return 0;
      }
   }
}
