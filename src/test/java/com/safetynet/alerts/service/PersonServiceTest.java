package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.safetynet.alerts.model.Household;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;

/**
 * PersonService units tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(PersonService.class)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

   @InjectMocks
   private PersonService personService;

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

//      List<Person> personsList = new ArrayList<>();
//      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
//                  "841-874-6512", "jaboyd@email.com", medicalRecord);
//      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
//                  "841-874-6513", "drk@email.com", medicalRecord);
//      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
//                  "97451", "841-874-7458", "gramps@email.com", medicalRecord);
//      personsList.add(person1);
//      personsList.add(person2);
//
//      Map<Integer, FireStation> stations = new HashMap<Integer, FireStation>();
//      Map<String, List<Person>> households = new HashMap<String, List<Person>>();
//
//      entitiesInfosStorage = new EntitiesInfosStorage(personsList, stations,
//                  households);
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Existing person")
   public void givenPersonInfo_whenEntryFirstAndLastName_thenReturnNoEmptyPersonsList() {
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);

      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<Person> result = personService.personInfo("John", "Boyd");

      assertThat(result.isEmpty()).isFalse();
      assertThat(result.size()).isEqualTo(2);
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow person")
   public void givenPersonInfoMethodAndEntryFirstAndLastName_whenNoOneHasThatName_thenReturnEmptyList() {
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);

      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<Person> result = personService.personInfo("Unknow", "Person");

      assertThat(result.isEmpty()).isTrue();
      assertThat(result.size()).isEqualTo(0);
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Valid city entry")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesList() {
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordAdult);
      Person otherCityPerson = new Person("Other", "Unknow", "Other city",
                  "Other city", "00000", "111-111-111", "other@email.com");
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);
      personsList.add(otherCityPerson);

      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> personsEmails = personService.communityEmail("Culver");

      assertThat(personsEmails.size()).isEqualTo(3);
      assertThat(personsEmails.toString()).isEqualTo(
                  "[jaboyd@email.com, drk@email.com, gramps@email.com]");
      assertThat(personsEmails.get(0)).isEqualTo("jaboyd@email.com");
      assertThat(personsEmails.get(1)).isEqualTo("drk@email.com");
      assertThat(personsEmails.get(2)).isEqualTo("gramps@email.com");
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Bad city entry")
   public void givenCityEntry_whenUnknowCityEntry_thenReturnEmptyPersonsList() {
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordAdult);
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);

      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      List<String> personsEmails = personService.communityEmail("Other city");

      assertThat(personsEmails.size()).isEqualTo(0);
      assertThat(personsEmails).isEmpty();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Adult & child - Same address")
   public void givenChildAndAdultOnTheSameAddress_whenChildAlertAddress_thenReturnTwoHouseholdListSize() {
      List<Household> childAlert = new ArrayList<>();

      // Child 1 - Good address
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordChild);
      int childAge = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      Household child1 = new Household(childAge, person1.getFirstName(),
                  person1.getLastName());
      childAlert.add(child1);

      // Adult - Good address
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      Household adult1 = new Household(adultAge, person2.getFirstName(),
                  person2.getLastName());
      childAlert.add(adult1);

      // Child 2 - Bad address
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordChild);
      int childAge2 = AgeCalculator
                  .ageCalculation(person3.getMedicalRecord().getBirthdate());
      Household child2 = new Household(childAge2, person3.getFirstName(),
                  person3.getLastName());
      childAlert.add(child2);

      // Add only same household persons
      List<Person> householdMembersList = new ArrayList<>();
      householdMembersList.add(person1);
      householdMembersList.add(person2);

      Map<String, List<Person>> households = new HashMap<>();
      households.put(person1.getAddress(), householdMembersList);
      households.put(person2.getAddress(), householdMembersList);
      households.put(person3.getAddress(), householdMembersList);

      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      List<Household> result = personService.childAlert("1509 Culver St");

      assertThat(result.size()).isEqualTo(2);
      assertThat(result.isEmpty()).isFalse();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address without children")
   public void givenTwoAdultsOnTheSameAddress_whenChildAlertAddress_thenReturnEmptyList() {
      List<Household> childAlert = new ArrayList<>();

      // Adult 1
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      int adultAge1 = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      Household adult1 = new Household(adultAge1, person1.getFirstName(),
                  person1.getLastName());
      childAlert.add(adult1);

      // Adult 2
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge2 = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      Household adult2 = new Household(adultAge2, person2.getFirstName(),
                  person2.getLastName());
      childAlert.add(adult2);

      List<Person> householdMembersList = new ArrayList<>();
      householdMembersList.add(person1);
      householdMembersList.add(person2);

      Map<String, List<Person>> households = new HashMap<>();
      households.put(person1.getAddress(), householdMembersList);
      households.put(person2.getAddress(), householdMembersList);

      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      List<Household> result = personService.childAlert("1509 Culver St");

      assertThat(result.size()).isEqualTo(0);
      assertThat(result.isEmpty()).isTrue();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Unknow address")
   public void givenUnknowAddressEntered_whenChildAlert_thenReturnEmptyList() {
      List<Household> childAlert = new ArrayList<>();

      // Adult 1
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordChild);
      int adultAge1 = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      Household adult1 = new Household(adultAge1, person1.getFirstName(),
                  person1.getLastName());
      childAlert.add(adult1);

      // Adult 2
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge2 = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      Household adult2 = new Household(adultAge2, person2.getFirstName(),
                  person2.getLastName());
      childAlert.add(adult2);

      List<Person> householdMembersList = new ArrayList<>();
      householdMembersList.add(person1);
      householdMembersList.add(person2);

      Map<String, List<Person>> households = new HashMap<>();
      households.put(person1.getAddress(), householdMembersList);
      households.put(person2.getAddress(), householdMembersList);

      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      List<Household> result = personService.childAlert("Unknow address");

      assertThat(result.size()).isEqualTo(0);
      assertThat(result.isEmpty()).isTrue();
   }
//   @Test
//   @Tag("POST")
//   @DisplayName("Create - new person")
//   public void givenPersonEndpoint_whenCreatePerson_thenReturnPersonCreated() {
//
//      Person newPerson = new Person("New", "Person", "new adress", "123-456",
//                  "newperson@email.com", medicalRecord1);
//
//      Person personCreated = personService.createPerson(newPerson);
//
//      assertThat(personCreated).isEqualTo(newPerson);
//   }
//
//   @Test
//   @Tag("POST")
//   @DisplayName("Create - existing person - error")
//   public void givenPersonEndpoint_whenCreateExistingPerson_thenReturnNull() {
//
//      Person newPerson = new Person("John", "Boyd", "1509 Culver St",
//                  "841-874-6512", "jaboyd@email.com", medicalRecord1);
//
//      Person personCreated = personService.createPerson(newPerson);
//
//      assertThat(personCreated).isNull();
//   }

//   @Test
//   @Tag("PUT")
//   @DisplayName("Update - existing person")
//   public void givenPersonEndpoint_whenUpdatePerson_thenReturnUpdatedPerson() {
//
//      Person personToUpdate = person1;
//      personToUpdate.setAdress("NEW ADRESS");
//
//      personService.updatePerson("John", "Boyd");
//
//      assertThat(personToUpdate.getAdress()).isEqualTo("NEW ADRESS");
//   }
//
//   @Test
//   @Tag("PUT")
//   @DisplayName("Update - unknow person - error")
//   public void givenPersonEndpoint_whenUpdateUnknowPerson_thenReturnNull() {
//
//      Person unknowPerson = new Person("Unknow", "Person", "1509 Culver unknow",
//                  "841-874", "unknowperson@email.com", medicalRecord1);
//
//      personService.updatePerson("John", "Boyd");
//
//      assertThat(personService.personInfo(unknowPerson.getFirstName(),
//                  unknowPerson.getLastName())).isNull();
//   }
//
//   @Test
//   @Tag("DELETE")
//   @DisplayName("Delete - existing person")
//   public void givenPersonEndpoint_whenDeletePerson_thenReturnDeletedPerson() {
//
//      personService.deletePerson("John", "Boyd");
//
//      assertThat(person1).isNull();
//   }

//   @Test
//   @Tag("DELETE")
//   @DisplayName("Delete - unknow person - error")
//   public void givenPersonEndpoint_whenDeleteUnknowPerson_thenReturnAllPersonsListSizeUnchanged() {
//
//      personService.deletePerson("unknow", "person");
//
//      assertThat(personService.getAllPersons().size()).isEqualTo(3);
//   }

//   @Test
//   @Tag("AllPersons")
//   @DisplayName("getAllPersons list")
//   public void givenPersonEndpoint_whenGetAllPersons_thenReturnAllPersonsList() {
//
//      personService.getAllPersons();
//
//      assertThat(personService.getAllPersons().isEmpty()).isFalse();
//      assertThat(personService.getAllPersons().size()).isEqualTo(3);
//      assertThat(personService.getAllPersons()).contains(person1, person2,
//                  person3);
//   }
//   @Test
//   @Tag("getPersonsAge")
//   @DisplayName("getPersonsAge - 20 yo person")
//   public void givenExistingPerson_whenTwentyYearOldPerson_thenReturnCorrectPersonsAge() {
//
//      LocalDate personBirthdate = LocalDate.of(1990, Month.JANUARY, 01);
//      LocalDate currentDate = LocalDate.of(2020, Month.JANUARY, 02);
//
//      person1.setMedicalRecord(medicalRecord);
//      medicalRecord.setBirthdate(personBirthdate);
//
//      int age = AgeCalculator.calculateAge(medicalRecord.getBirthdate(),
//                  currentDate);
//
//      personService.getPersonsAge(person1);
//
//      assertThat(person1.getMedicalRecord().getAge()).isEqualTo(age);
//      assertThat(person1.getMedicalRecord().getAge()).isEqualTo(20);
//   }
//
//   @Test
//   @Tag("getPersonsAge")
//   @DisplayName("getPersonsAge - unknow person")
//   public void givenunknowPerson_whenGetPersonAgeMethod_thenReturnNull() {
//
//      LocalDate personBirthdate = null;
//      LocalDate currentDate = LocalDate.of(2020, Month.JANUARY, 02);
//
//      person1.setMedicalRecord(medicalRecord);
//      medicalRecord.setBirthdate(personBirthdate);
//
//      int age = AgeCalculator.calculateAge(medicalRecord.getBirthdate(),
//                  currentDate);
//
//      personService.getPersonsAge(person1);
//
//      assertThat(person1.getMedicalRecord().getAge()).isNull();
//      assertThat(age).isNull();
//   }

}
