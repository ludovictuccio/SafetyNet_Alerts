package com.safetynet.alerts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    * @param birthdate
    * @return age
    * @throws ParseException
    */
   public int ageCalculation(final String birthdate) throws ParseException {
      LOGGER.debug("Age calculation initialization");

      SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
      Date personsBirthdate = null;
      personsBirthdate = dateFormatter.parse(birthdate);

      Calendar birthdateData = Calendar.getInstance();
      Calendar currentDate = Calendar.getInstance();
      birthdateData.setTime(personsBirthdate);

      if (birthdateData.after(currentDate)) {
         LOGGER.error("Bithdate person's invalid (in the future)");
         throw new IllegalArgumentException("Can't be born in the future");
      }

      int year1 = currentDate.get(Calendar.YEAR);
      int year2 = birthdateData.get(Calendar.YEAR);
      int age = year1 - year2;
      int month1 = currentDate.get(Calendar.MONTH);
      int month2 = birthdateData.get(Calendar.MONTH);
      if (month2 > month1) {
         age--;
      } else if (month1 == month2) {
         int day1 = currentDate.get(Calendar.DAY_OF_MONTH);
         int day2 = birthdateData.get(Calendar.DAY_OF_MONTH);
         if (day2 > day1) {
            age--;
         }
      }
      return age;
   }

}
