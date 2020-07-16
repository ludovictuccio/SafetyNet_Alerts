package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.model.Person;

/**
 * Age calculator class, used to determine the person's age.
 *
 * @author Ludovic Tuccio
 *
 */
public class AgeCalculator {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(AgeCalculator.class);
   /**
    * Minor age threshold; more is considerate as adult person.
    */
   private static final int MINOR_AGE_THRESHOLD = 18;

   /**
    * Age calculation method.
    *
    * @param birthdate
    * @return age
    * @throws NullPointerException
    * @throws IllegalArgumentException
    */
   public static int ageCalculation(final String birthdate) {
      DateTimeFormatter formatter = DateTimeFormatter
                  .ofPattern(Constants.DATE_PATTERN);
      LocalDate personsBirthdate = LocalDate.parse(birthdate, formatter);
      LocalDate currentDate = LocalDate.now();

      int age = Period.between(personsBirthdate, currentDate).getYears();

      if (personsBirthdate.isAfter(currentDate)) {
         LOGGER.error("Person's bithdate invalid (in the future)");
         throw new IllegalArgumentException(
                     "ERROR - Person's bithdate in the future");
      } else if (age == 0) {
         LOGGER.debug("Baby with age less than 1 year");
         age++;
      } else {
         return age;
      }
      return age;
   }

   /**
    * Method used to determinate adults and children persons.
    *
    * @param person
    * @throws NullPointerException
    * @return isChild boolean
    */
   public static boolean isChild(final Person person) {
      boolean isChild = false;
      int personsAge = AgeCalculator
                  .ageCalculation(person.getMedicalRecord().getBirthdate());
      if (personsAge > MINOR_AGE_THRESHOLD) {
      } else {
         isChild = true;
      }
      return isChild;
   }

}
