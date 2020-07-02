package com.safetynet.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

/**
 * PersonService units tests class.
 *
 * @author Ludovic Tuccio
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class PersonServiceTest {

   private static PersonService personService;

   private static EntitiesInfosStorage entitiesInfosStorage;

   // private static AgeCalculator ageCalculator;

   private static String birthdate = "01/01/1990";

   private static Person person1;
   private static Person person2;
   private static Person person3;
   private static MedicalRecord medicalRecord;

   @BeforeAll
   private static void setUp() {
      personService = new PersonService();
      // ageCalculator = new AgeCalculator();
   }

   @BeforeEach
   private void setUpPerTest() {
      // ageCalculator = new AgeCalculator();

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      medicalRecord = new MedicalRecord(birthdate, medicationsList,
                  allergiesList);
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecord);
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecord);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com", medicalRecord);

      List<Person> personList = new ArrayList<>();
      personList.add(person1);
      personList.add(person2);
      personList.add(person3);
      Map<Integer, FireStation> stations = new HashMap<Integer, FireStation>();
      Map<String, List<Person>> households = new HashMap<String, List<Person>>();

      entitiesInfosStorage = new EntitiesInfosStorage(personList, stations,
                  households);
   }

   @Test
   @Order(1)
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Valid city entry")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesList() {
      Person otherCityPerson = new Person("Other", "Unknow", "Other city",
                  "Other city", "00000", "111-111-111", "other@email.com");

      List<Person> persons = entitiesInfosStorage.getPersonsList();
      persons.add(otherCityPerson); // Other city
      entitiesInfosStorage.setPersonsList(persons);

      List<String> personsEmails = personService.communityEmail("Culver",
                  persons);

      assertThat(personsEmails.size()).isEqualTo(3);
      assertThat(personsEmails.toString()).isEqualTo(
                  "[jaboyd@email.com, drk@email.com, gramps@email.com]");
      assertThat(personsEmails.get(0)).isEqualTo("jaboyd@email.com");
      assertThat(personsEmails.get(1)).isEqualTo("drk@email.com");
      assertThat(personsEmails.get(2)).isEqualTo("gramps@email.com");
   }

   @Test
   @Order(2)
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Bad city entry")
   public void givenCityEntry_whenUnknowCityEntry_thenReturnEmptyPersonsList() {
      List<Person> persons = entitiesInfosStorage.getPersonsList();
      List<String> personsEmails = personService.communityEmail("Other city",
                  persons);

      assertThat(personsEmails.size()).isEqualTo(0);
      assertThat(personsEmails).isEmpty();
   }

   @Test
   @Order(3)
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Existing person")
   public void givenPersonInfo_whenEntryFirstAndLastName_thenReturnNoEmptyPersonsList() {
      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      List<Person> personsInfosList = personService.personInfo("John", "Boyd",
                  personsList);

      assertThat(personsInfosList.isEmpty()).isFalse();
      assertThat(personsInfosList.size()).isEqualTo(2);
   }

   @Test
   @Order(4)
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow person")
   public void givenPersonInfoMethodAndEntryFirstAndLastName_whenNoOneHasThatName_thenReturnEmptyList() {
      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      List<Person> personsInfosList = personService.personInfo("Unknow",
                  "Person", personsList);

      assertThat(personsInfosList.isEmpty()).isTrue();
   }

   @Test
   @Order(5)
   @Tag("isChildren")
   @DisplayName("isChildren - Child")
   public void givenChild_whenIsChildrenMethod_thenReturnTrue() {
      boolean isChild;
      person1.getMedicalRecord().setBirthdate("01/01/2020");

      isChild = personService.isChildren(person1);

      assertThat(isChild).isTrue();
   }

   @Test
   @Order(6)
   @Tag("isChildren")
   @DisplayName("isChildren - Adult")
   public void givenAdult_whenIsChildrenMethod_thenReturnFalse() {
      boolean isChild;
      person1.getMedicalRecord().setBirthdate("01/01/2000");

      isChild = personService.isChildren(person1);

      assertThat(isChild).isFalse();
   }

   @Test
   @Order(7)
   @Tag("isChildren")
   @DisplayName("isChildren - Null birthdate")
   public void givenNullPersonsBirthdate_whenIsChildrenMethod_thenReturnNullPointerException() {
      person1.getMedicalRecord().setBirthdate(null);

      assertThatNullPointerException().isThrownBy(() -> {
         personService.isChildren(person1);
      });
   }

//   @Test
//   @Order(3)
//   @Tag("ChildAlert")
//   @DisplayName("childAlert - adress with children")
//   public void givenChildAlert_whenAdressEnteredWithChildren_thenReturnHouseholdsChildrenComposition() {
//
//      // 2 yo
//      LocalDate person1Birthdate = LocalDate.of(2018, Month.JANUARY, 01);
//      person1.setMedicalRecord(medicalRecord1);
//      medicalRecord1.setBirthdate(person1Birthdate);
//      person2.setAdress("1509 Culver St");
//
//      // 40 yo
//      LocalDate person2Birthdate = LocalDate.of(1980, Month.JANUARY, 01);
//      person2.setMedicalRecord(medicalRecord2);
//      medicalRecord2.setBirthdate(person2Birthdate);
//      person2.setAdress("1509 Culver St");
//
//      List<Person> household = personService.childAlert("1509 Culver St");
//
//      assertThat(household.contains(person1)).isTrue();
//      assertThat(household.contains(person2)).isTrue();
//      assertThat(household.size()).isEqualTo(2);
//   }
//
//   @Test
//   @Order(4)
//   @Tag("ChildAlert")
//   @DisplayName("childAlert - adress without children - empty list ")
//   public void givenChildAlert_whenAdressEnteredWithoutChildren_thenReturnEmptyList() {
//
//      // 50 yo
//      LocalDate person1Birthdate = LocalDate.of(1970, Month.JANUARY, 01);
//      person1.setMedicalRecord(medicalRecord1);
//      medicalRecord1.setBirthdate(person1Birthdate);
//      person2.setAdress("1509 Culver St");
//
//      // 40 yo
//      LocalDate person2Birthdate = LocalDate.of(1980, Month.JANUARY, 01);
//      person2.setMedicalRecord(medicalRecord2);
//      medicalRecord2.setBirthdate(person2Birthdate);
//      person2.setAdress("1509 Culver St");
//
//      List<Person> household = personService.childAlert("1509 Culver St");
//
//      assertThat(household.contains(person1)).isFalse();
//      assertThat(household.contains(person2)).isFalse();
//      assertThat(household.size()).isEqualTo(0);
//      assertThat(household.isEmpty()).isTrue();
//   }

//   @Test
//   @Order(3)
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
//   @Order(4)
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
