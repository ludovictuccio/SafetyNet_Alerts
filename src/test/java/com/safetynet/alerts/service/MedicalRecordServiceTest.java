package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

   private static String adultBirthdate = "01/01/1990";
   private static String childBirthdate = "01/01/2020";
   private static MedicalRecord medicalRecordAdult;
   private static MedicalRecord medicalRecordChild;
   private static Person person1;
   private static Person person2;
   private static Person person3;

   @BeforeEach
   private void setUpPerTest() {

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      medicalRecordAdult = new MedicalRecord(adultBirthdate, medicationsList,
                  allergiesList);
      medicalRecordChild = new MedicalRecord(childBirthdate, medicationsList,
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
                  adultBirthdate, medicationsList, allergiesList);
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
                  adultBirthdate, medicationsList, allergiesList);
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
                  adultBirthdate, medicationsList, allergiesList);
      // WHEN
      MedicalRecord result = medicalRecordService
                  .createMedicalRecord(newMedicalRecord);

      // THEN
      assertThat(result).isNull();
   }
}
