package com.safetynet.alerts.unit.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.util.AgeCalculator;

/**
 * AgeCalculator unit tests class.
 *
 * @author Ludovic Tuccio
 *
 */
public class AgeCalculatorTest {

   private AgeCalculator ageCalculator;
   private String birthdate;
   private DateTimeFormatter formatter;
   private LocalDate currentDate;

   @BeforeEach
   private void setUp() {
      ageCalculator = new AgeCalculator();
      formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 20 yo")
   public void givenATwentyYoPersons_whenAgeCalculation_thenReturnCorrectAge() {

      int age = 20;
      currentDate = LocalDate.now().minusYears(age);
      birthdate = formatter.format(currentDate);

      int result = ageCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(result);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 1 yo")
   public void givenAOneYoPersons_whenAgeCalculation_thenReturnCorrectAge() {

      int age = 1;
      currentDate = LocalDate.now().minusYears(age);
      birthdate = formatter.format(currentDate);

      int result = ageCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(result);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 6 months baby - Return 1 yo")
   public void givenASixMonthsBaby_whenAgeCalculation_thenReturnOne() {

      currentDate = LocalDate.now().minusMonths(6);
      birthdate = formatter.format(currentDate);

      int age = ageCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(1);
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Null birthdate")
   public void givenNuyllBirthdate_whenAgeCalculation_thenReturnException() {

      String birthdate = null;

      assertThatNullPointerException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 yo")
   public void givenOneNegativeYo_whenAgeCalculation_thenReturnException() {

      currentDate = LocalDate.now().plusYears(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 month")
   public void givenOneMonthNegativeYo_whenAgeCalculation_thenReturnException() {

      currentDate = LocalDate.now().plusMonths(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 day")
   public void givenOneDayNegativeYo_whenAgeCalculation_thenReturnException() {

      currentDate = LocalDate.now().plusDays(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

}
