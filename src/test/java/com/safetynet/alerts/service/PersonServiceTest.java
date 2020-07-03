package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * PersonService units tests class.
 *
 * @author Ludovic Tuccio
 */
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

   private static PersonService personService;
   private static EntitiesInfosStorage entitiesInfosStorage;
   private static String adultBirthdate = "01/01/1990";
   private static MedicalRecord medicalRecord;
   private static Person person1;
   private static Person person2;
   private static Person person3;

   @BeforeAll
   private static void setUp() {
      personService = new PersonService();
   }

   @BeforeEach
   private void setUpPerTest() {
      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication1");

      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      medicalRecord = new MedicalRecord(adultBirthdate, medicationsList,
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
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow person")
   public void givenPersonInfoMethodAndEntryFirstAndLastName_whenNoOneHasThatName_thenReturnEmptyList() {
      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      List<Person> personsInfosList = personService.personInfo("Unknow",
                  "Person", personsList);

      assertThat(personsInfosList.isEmpty()).isTrue();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Adult and child with the same address")
   public void givenChildAndAdultOnTheSameAddress_whenChildAlertAddress_thenReturnTwoHouseholdListSize() {
      List<Person> personsList = new ArrayList<>();
      Map<String, List<Person>> household = entitiesInfosStorage
                  .getPersonsPerHousehold();
      household.clear();

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication");
      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      // Child
      String person1Birthdate = "01/01/2020";
      MedicalRecord medicalRecordP1 = new MedicalRecord(person1Birthdate,
                  medicationsList, allergiesList);
      person1.setMedicalRecord(medicalRecordP1);
      personsList.add(person1);

      // Adult
      String person2Birthdate = "01/01/1990";
      MedicalRecord medicalRecordP2 = new MedicalRecord(person2Birthdate,
                  medicationsList, allergiesList);
      person2.setMedicalRecord(medicalRecordP2);
      personsList.add(person2);

      List<Person> result = personService.childAlert("1509 Culver St",
                  personsList, household);

      assertThat(result.size()).isEqualTo(2);
      assertThat(result.isEmpty()).isFalse();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address without children")
   public void givenTwoAdultsOnTheSameAddress_whenChildAlertAddress_thenReturnEmptyList() {
      List<Person> personsList = new ArrayList<>();
      Map<String, List<Person>> household = entitiesInfosStorage
                  .getPersonsPerHousehold();
      household.clear();

      List<String> medicationsList = new ArrayList<>();
      medicationsList.add("medication");
      List<String> allergiesList = new ArrayList<>();
      allergiesList.add("allergies");

      // Adult 1
      String person1Birthdate = "01/01/1960";
      MedicalRecord medicalRecordP1 = new MedicalRecord(person1Birthdate,
                  medicationsList, allergiesList);
      person1.setMedicalRecord(medicalRecordP1);
      personsList.add(person1);

      // Adult 2
      String person2Birthdate = "01/01/1990";
      MedicalRecord medicalRecordP2 = new MedicalRecord(person2Birthdate,
                  medicationsList, allergiesList);
      person2.setMedicalRecord(medicalRecordP2);
      personsList.add(person2);

      List<Person> result = personService.childAlert("1509 Culver St",
                  personsList, household);

      assertThat(result.size()).isEqualTo(0);
      assertThat(result.isEmpty()).isTrue();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Unknow address")
   public void givenUnknowAddressEntered_whenChildAlert_thenReturnEmptyList() {

      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      Map<String, List<Person>> household = entitiesInfosStorage
                  .getPersonsPerHousehold();
      personsList.clear();
      household.clear();

      List<Person> result = personService.childAlert("Unknow address",
                  personsList, household);

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
