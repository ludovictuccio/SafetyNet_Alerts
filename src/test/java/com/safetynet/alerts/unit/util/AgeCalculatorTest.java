package com.safetynet.alerts.unit.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.alerts.SafetyNetAlertsApplication;
import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.util.AgeCalculator;

/**
 * AgeCalculator unit tests class.
 *
 * @author Ludovic Tuccio
 *
 */
@WebMvcTest(AgeCalculator.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SafetyNetAlertsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
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
   @Order(1)
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
   @Order(2)
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
   @Order(3)
   @Tag("Valid")
   @DisplayName("Valid - 6 months baby - Return 1 yo")
   public void givenASixMonthsBaby_whenAgeCalculation_thenReturnOne() {

      currentDate = LocalDate.now().minusMonths(6);
      birthdate = formatter.format(currentDate);

      int age = ageCalculator.ageCalculation(birthdate);

      assertThat(age).isEqualTo(1);
   }

   @Test
   @Order(4)
   @Tag("Invalid")
   @DisplayName("Invalid - Null birthdate")
   public void givenNuyllBirthdate_whenAgeCalculation_thenReturnNullPointerException() {

      String birthdate = null;

      assertThatNullPointerException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Order(5)
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 yo")
   public void givenOneNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusYears(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Order(6)
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 month")
   public void givenOneMonthNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusMonths(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

   @Test
   @Order(7)
   @Tag("Invalid")
   @DisplayName("Invalid - Minus 1 day")
   public void givenOneDayNegativeYo_whenAgeCalculation_thenReturnIllegalArgumentException() {

      currentDate = LocalDate.now().plusDays(1);
      birthdate = formatter.format(currentDate);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         ageCalculator.ageCalculation(birthdate);
      });
   }

}
