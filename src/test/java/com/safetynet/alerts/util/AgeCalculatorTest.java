package com.safetynet.alerts.util;

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
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * AgeCalculator unit tests class.
 *
 * @author Ludovic Tuccio
 */
public class AgeCalculatorTest {

   private String birthdate;
   private DateTimeFormatter formatter;
   private LocalDate currentDate;
   private MedicalRecord medicalRecord;
   private Person person1;

   @BeforeEach
   private void setUp() {
      formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
      medicalRecord = new MedicalRecord("01/01/2020", null, null);
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecord);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 20 yo")
   public void givenATwentyYoPersons_whenAgeCalculation_thenReturnCorrectAge() {

      int age = 20;
      currentDate = LocalDate.now().minusYears(age);
      birthdate = formatter.format(currentDate);

      int result = AgeCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(result);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 1 yo")
   public void givenAOneYoPersons_whenAgeCalculation_thenReturnCorrectAge() {

      int age = 1;
      currentDate = LocalDate.now().minusYears(age);
      birthdate = formatter.format(currentDate);

      int result = AgeCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(result);
   }

   @Test
   @Tag("Valid")
   @DisplayName("Valid - 6 months baby - Return 1 yo")
   public void givenASixMonthsBaby_whenAgeCalculation_thenReturnOne() {

      currentDate = LocalDate.now().minusMonths(6);
      birthdate = formatter.format(currentDate);

      int age = AgeCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(1);
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Null birthdate")
   public void givenNuyllBirthdate_whenAgeCalculation_thenReturnNullPointerException() {

      String birthdate = null;

      assertThatNullPointerException().isThrownBy(() -> {
         AgeCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 yo")
   public void givenOneNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusYears(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         AgeCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 month")
   public void givenOneMonthNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusMonths(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         AgeCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 day")
   public void givenOneDayNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusDays(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         AgeCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - Child")
   public void givenChild_whenIsChildrenMethod_thenReturnTrue() {

      boolean isChild;
      person1.getMedicalRecord().setBirthdate("01/01/2020");

      isChild = AgeCalculator.isChild(person1);

      assertThat(isChild).isTrue();
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - Adult")
   public void givenAdult_whenIsChildrenMethod_thenReturnFalse() {
      boolean isChild;
      person1.getMedicalRecord().setBirthdate("01/01/2000");

      isChild = AgeCalculator.isChild(person1);

      assertThat(isChild).isFalse();
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - Null birthdate")
   public void givenNullPersonsBirthdate_whenIsChildrenMethod_thenReturnNullPointerException() {
      person1.getMedicalRecord().setBirthdate(null);

      assertThatNullPointerException().isThrownBy(() -> {
         AgeCalculator.isChild(person1);
      });
   }

}
