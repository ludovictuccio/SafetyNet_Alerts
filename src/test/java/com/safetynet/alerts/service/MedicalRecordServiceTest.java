package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * MedicalRecordService units tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(MedicalRecordService.class)
@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

   @InjectMocks
   private MedicalRecordService medicalRecordService;

   @Mock
   private EntitiesInfosStorage entitiesInfosStorage;

   private static final String ADULT_BIRTHDATE = "01/01/1990";

   private MedicalRecord medicalRecordAdult;

   @BeforeAll
   private void setUpPerTest() {

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      medicalRecordAdult = new MedicalRecord(ADULT_BIRTHDATE, medicationsList,
                  allergiesList);
   }

   @Test
   @Tag("POST")
   @DisplayName("CREATE - OK - Person without medicalrecord")
   public void givenPersonWithoutMedicalrecord_whenCreation_thenReturnMedicalrecordAttribution() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      MedicalRecord newMedicalRecord = new MedicalRecord("Jacob", "Boyd",
                  ADULT_BIRTHDATE, medicationsList, allergiesList);
      // WHEN
      MedicalRecord result = medicalRecordService
                  .createMedicalRecord(newMedicalRecord);

      // THEN
      assertThat(result).isNotNull();
   }

   @Test
   @Tag("POST")
   @DisplayName("CREATE - ERROR - Person with already exisiting medicalrecord")
   public void givenPersonWithMedicalrecord_whenCreation_thenReturnError() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      MedicalRecord newMedicalRecord = new MedicalRecord("John", "Boyd",
                  ADULT_BIRTHDATE, medicationsList, allergiesList);
      // WHEN
      MedicalRecord result = medicalRecordService
                  .createMedicalRecord(newMedicalRecord);

      // THEN
      assertThat(result).isNull();
   }

   @Test
   @Tag("POST")
   @DisplayName("CREATE - ERROR - Unfounded person")
   public void givenUnknowPerson_whenCreation_thenReturnError() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      MedicalRecord newMedicalRecord = new MedicalRecord("Unknow", "Person",
                  ADULT_BIRTHDATE, medicationsList, allergiesList);
      // WHEN
      MedicalRecord result = medicalRecordService
                  .createMedicalRecord(newMedicalRecord);

      // THEN
      assertThat(result).isNull();
   }

   @Test
   @Tag("PUT")
   @DisplayName("UPDATE - OK - Person with medicalrecord")
   public void givenPersonWithMedicalrecord_whenUpdate_thenReturnUpdatedMedicalrecord() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("newMedic:100mg");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("newAllergy");

      MedicalRecord medicalRecordUpdated = new MedicalRecord("John", "Boyd",
                  "01/01/1903", medicationsList, allergiesList);

      // WHEN
      boolean result = medicalRecordService
                  .updateMedicalRecord(medicalRecordUpdated);

      // THEN
      assertThat(result).isTrue();
      assertThat(person1.getFirstName()).isEqualTo("John");
      assertThat(person1.getMedicalRecord().getBirthdate())
                  .isEqualTo("01/01/1903");
      assertThat(person1.getMedicalRecord().getAllergies().toString())
                  .isEqualTo("[newAllergy]");
      assertThat(person1.getMedicalRecord().getMedications().toString())
                  .isEqualTo("[newMedic:100mg]");
   }

   @Test
   @Tag("PUT")
   @DisplayName("UPDATE - ERROR - Unknow person")
   public void givenUnknowPerson_whenUpdate_thenReturnNoUpdatedAndBooleanFalse() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("newMedic:100mg");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("newAllergy");

      MedicalRecord medicalRecordUpdated = new MedicalRecord("Unknow", "Person",
                  "01/01/1903", medicationsList, allergiesList);

      // WHEN
      boolean result = medicalRecordService
                  .updateMedicalRecord(medicalRecordUpdated);

      // THEN
      assertThat(result).isFalse();
      assertThat(person1.getMedicalRecord().getBirthdate())
                  .isEqualTo("01/01/1990");
      assertThat(person1.getMedicalRecord().getAllergies().toString())
                  .isEqualTo("[allergies]");
      assertThat(person1.getMedicalRecord().getMedications().toString())
                  .isEqualTo("[medication1]");
   }

   @Test
   @Tag("DELETE")
   @DisplayName("DELETE - OK - Person with medicalrecord")
   public void givenPersonWithMedicalrecord_whenDelete_thenReturnDeletedMedicalrecord() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      boolean result = medicalRecordService.deleteMedicalRecord("John", "Boyd");

      // THEN
      assertThat(result).isTrue();
      assertThat(person1.getFirstName()).isEqualTo("John");
      assertThat(person1.getMedicalRecord()).isNull();
   }

   @Test
   @Tag("DELETE")
   @DisplayName("DELETE - ERROR - Person without medicalrecord")
   public void givenPersonWithoutMedicalrecord_whenDelete_thenReturnError() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com");
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      boolean result = medicalRecordService.deleteMedicalRecord("John", "Boyd");

      // THEN
      assertThat(result).isFalse();
      assertThat(person1.getFirstName()).isEqualTo("John");
      assertThat(person1.getMedicalRecord()).isNull();
   }

   @Test
   @Tag("DELETE")
   @DisplayName("DELETE - ERROR - Unknow Person")
   public void givenUnknowPerson_whenDelete_thenReturnError() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      boolean result = medicalRecordService.deleteMedicalRecord("Unknow",
                  "Person");

      // THEN
      assertThat(result).isFalse();
   }
}
