package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.safetynet.alerts.constants.Constants;

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
    * Age calculation method.
    *
    * @param birthdate
    * @return age
    * @throws NullPointerException
    * @throws IllegalArgumentException
    */
   public int ageCalculation(final String birthdate) {

      LOGGER.debug("Age calculation initialization");
      DateTimeFormatter formatter = DateTimeFormatter
                  .ofPattern(Constants.DATE_PATTERN);
      LocalDate personsBirthdate = LocalDate.parse(birthdate, formatter);
      LocalDate currentDate = LocalDate.now();

      if ((personsBirthdate == null) || (currentDate == null)) {
         LOGGER.error("NULL person's bithdate");
         throw new NullPointerException("Null person's birthdate");
      }

      int age = Period.between(personsBirthdate, currentDate).getYears();

      if (personsBirthdate.isAfter(currentDate)) {
         LOGGER.error("Person's bithdate invalid (in the future)");
         throw new IllegalArgumentException("Can't be born in the future");
      } else if (age == 0) {
         age++;
      } else {
         return age;
      }
      return age;
   }

}
