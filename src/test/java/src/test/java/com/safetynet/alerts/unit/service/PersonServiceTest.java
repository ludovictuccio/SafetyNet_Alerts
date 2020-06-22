package src.test.java.com.safetynet.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import com.safetynet.alerts.util.AgeCalculator;

/**
 *
 * PersonService units tests class.
 *
 * @author Ludovic Tuccio
 *
 */
@RunWith(SpringRunner.class)
public class PersonServiceTest {

   private PersonService personService;

   @MockBean
   private EntitiesInfosStorage entitiesInfosStorage;

   @MockBean
   private MedicalRecord medicalRecord;

   @MockBean
   private AgeCalculator ageCalculator;

   private static Person person1;
   private static Person person2;
   private static Person person3;
   private static MedicalRecord medicalRecord1;
   private static MedicalRecord medicalRecord2;
   private static MedicalRecord medicalRecord3;

   @BeforeEach
   private void setUp() {

      entitiesInfosStorage = new EntitiesInfosStorage();
      personService = new PersonService();
      person1 = new Person();
      person2 = new Person();
      person3 = new Person();
      medicalRecord1 = new MedicalRecord();
      medicalRecord2 = new MedicalRecord();
      ageCalculator = new AgeCalculator();

      // medications
      List<String> medicationsList1 = new ArrayList<>();
      {
         medicationsList1.add("medication1");
      }

      // allergies
      List<String> allergiesList1 = new ArrayList<>();
      {
         allergiesList1.add("allergies1");
      }
      List<String> allergiesList2 = new ArrayList<>();
      {
         allergiesList2.add("allergies2");
      }

      medicalRecord1.setMedications(medicationsList1);
      medicalRecord1.setAllergies(allergiesList1);
      medicalRecord2.setAllergies(allergiesList2);

      person1 = new Person("John", "Boyd", "1509 Culver St", "841-874-6512",
                  "jaboyd@email.com", medicalRecord1);
      person2 = new Person("Clive", "Ferguson", "748 Townings Dr",
                  "841-874-6741", "clivfd@ymail.com", medicalRecord2);
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "841-874-7458",
                  "gramps@email.com", medicalRecord3);

      personService.createPerson(person1);
      personService.createPerson(person2);
      personService.createPerson(person3);

      List<Person> personList = new ArrayList<>();
      {
         personList.add(person1);
         personList.add(person2);
         personList.add(person3);
      }
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - new person")
   public void givenPersonEndpoint_whenCreatePerson_thenReturnPersonCreated() {

      Person newPerson = new Person("New", "Person", "new adress", "123-456",
                  "newperson@email.com", medicalRecord1);

      Person personCreated = personService.createPerson(newPerson);

      assertThat(personCreated).isEqualTo(newPerson);
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - existing person - error")
   public void givenPersonEndpoint_whenCreateExistingPerson_thenReturnNull() {

      Person newPerson = new Person("John", "Boyd", "1509 Culver St",
                  "841-874-6512", "jaboyd@email.com", medicalRecord1);

      Person personCreated = personService.createPerson(newPerson);

      assertThat(personCreated).isNull();
   }

   @Test
   @Tag("PUT")
   @DisplayName("Update - existing person")
   public void givenPersonEndpoint_whenUpdatePerson_thenReturnUpdatedPerson() {

      Person personToUpdate = person1;
      personToUpdate.setAdress("NEW ADRESS");

      personService.updatePerson("John", "Boyd");

      assertThat(personToUpdate.getAdress()).isEqualTo("NEW ADRESS");
   }

   @Test
   @Tag("PUT")
   @DisplayName("Update - unknow person - error")
   public void givenPersonEndpoint_whenUpdateUnknowPerson_thenReturnNull() {

      Person unknowPerson = new Person("Unknow", "Person", "1509 Culver unknow",
                  "841-874", "unknowperson@email.com", medicalRecord1);

      personService.updatePerson("John", "Boyd");

      assertThat(personService.personInfo(unknowPerson.getFirstName(),
                  unknowPerson.getLastName())).isNull();
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - existing person")
   public void givenPersonEndpoint_whenDeletePerson_thenReturnDeletedPerson() {

      personService.deletePerson("John", "Boyd");

      assertThat(person1).isNull();
   }

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

   @Test
   @Tag("PersonInfo")
   @DisplayName("personInfo - existing person ")
   public void givenGetPersonByNameMethod_whenEntryFirstAndLastName_thenReturnAllPersonsWithSameFirstnameList() {

      List<Person> personNameRecovery = personService.personInfo("John",
                  "Boyd");

      assertThat(personNameRecovery).isEqualTo(person1);
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("personInfo - unknow person ")
   public void givenGetPersonByNameMethodAndEntryFirstAndLastName_whenNoOneHasThatName_thenReturnNullList() {

      List<Person> personNameRecovery = personService.personInfo("Unknow",
                  "Person");

      assertThat(personNameRecovery).isNull();
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("communityEmail - valid city entry")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesList() {

      List<Person> personList = new ArrayList<>();
      personList.add(person1);
      personList.add(person2);
      personList.add(person3);
      person1.setEmail("jaboyd@email.com");
      person1.setCity("Culver");
      person2.setEmail("clivfd@ymail.com");
      person2.setCity("Culver");
      person3.setEmail("other@ymail.com");
      person3.setCity("Other city");

      List<String> personsEmails = personService.communityEmail("Culver",
                  personList);

      assertThat(personsEmails.size()).isEqualTo(2);
      assertThat(personsEmails.get(0)).isEqualTo("jaboyd@email.com");
      assertThat(personsEmails.get(1)).isEqualTo("clivfd@ymail.com");
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("communityEmail - bad city entry")
   public void givenCityEntry_whenIncorrectCity_thenReturnEmptyList() {

      List<Person> personList = new ArrayList<>();
      personList.add(person1);
      personList.add(person2);
      personList.add(person3);
      person1.setEmail("jaboyd@email.com");
      person1.setCity("Culver");
      person2.setEmail("clivfd@ymail.com");
      person2.setCity("Culver");
      person3.setEmail("LA@ymail.com");
      person3.setCity("Los Angeles");

      List<String> personsEmails = personService.communityEmail("Other city",
                  personList);

      assertThat(personsEmails.size()).isEqualTo(0);
      assertThat(personsEmails).isEmpty();
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - 5 years - true")
   public void givenAFiveYearOldChild_whenIsChildrenMethod_thenReturnTrue() {

      boolean isChild;
      int personsAge = 5;

      isChild = personService.isChildren(personsAge);

      assertThat(isChild).isTrue();
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - 18 years - true")
   public void givenAEighteenYearOldChild_whenIsChildrenMethod_thenReturnTrue() {

      boolean isChild;
      int personsAge = 18;

      isChild = personService.isChildren(personsAge);

      assertThat(isChild).isTrue();
   }

   @Test
   @Tag("isChildren")
   @DisplayName("isChildren - 19 years - false")
   public void givenANineteenYearOldPerson_whenIsChildrenMethod_thenReturnFalse() {

      boolean isChild;
      int personsAge = 19;

      isChild = personService.isChildren(personsAge);

      assertThat(isChild).isFalse();
   }

   @Test
   @Tag("getPersonsAge")
   @DisplayName("getPersonsAge - 20 yo person")
   public void givenExistingPerson_whenTwentyYearOldPerson_thenReturnCorrectPersonsAge() {

      LocalDate personBirthdate = LocalDate.of(1990, Month.JANUARY, 01);
      LocalDate currentDate = LocalDate.of(2020, Month.JANUARY, 02);

      person1.setMedicalRecord(medicalRecord);
      medicalRecord.setBirthdate(personBirthdate);

      int age = AgeCalculator.calculateAge(medicalRecord.getBirthdate(),
                  currentDate);

      personService.getPersonsAge(person1);

      assertThat(person1.getMedicalRecord().getAge()).isEqualTo(age);
      assertThat(person1.getMedicalRecord().getAge()).isEqualTo(20);
   }

   @Test
   @Tag("getPersonsAge")
   @DisplayName("getPersonsAge - unknow person")
   public void givenunknowPerson_whenGetPersonAgeMethod_thenReturnNull() {

      LocalDate personBirthdate = null;
      LocalDate currentDate = LocalDate.of(2020, Month.JANUARY, 02);

      person1.setMedicalRecord(medicalRecord);
      medicalRecord.setBirthdate(personBirthdate);

      int age = AgeCalculator.calculateAge(medicalRecord.getBirthdate(),
                  currentDate);

      personService.getPersonsAge(person1);

      assertThat(person1.getMedicalRecord().getAge()).isNull();
      assertThat(age).isNull();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("childAlert - adress with children")
   public void givenChildAlert_whenAdressEnteredWithChildren_thenReturnHouseholdsChildrenComposition() {

      // 2 yo
      LocalDate person1Birthdate = LocalDate.of(2018, Month.JANUARY, 01);
      person1.setMedicalRecord(medicalRecord1);
      medicalRecord1.setBirthdate(person1Birthdate);
      person2.setAdress("1509 Culver St");

      // 40 yo
      LocalDate person2Birthdate = LocalDate.of(1980, Month.JANUARY, 01);
      person2.setMedicalRecord(medicalRecord2);
      medicalRecord2.setBirthdate(person2Birthdate);
      person2.setAdress("1509 Culver St");

      List<Person> household = personService.childAlert("1509 Culver St");

      assertThat(household.contains(person1)).isTrue();
      assertThat(household.contains(person2)).isTrue();
      assertThat(household.size()).isEqualTo(2);
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("childAlert - adress without children - empty list ")
   public void givenChildAlert_whenAdressEnteredWithoutChildren_thenReturnEmptyList() {

      // 50 yo
      LocalDate person1Birthdate = LocalDate.of(1970, Month.JANUARY, 01);
      person1.setMedicalRecord(medicalRecord1);
      medicalRecord1.setBirthdate(person1Birthdate);
      person2.setAdress("1509 Culver St");

      // 40 yo
      LocalDate person2Birthdate = LocalDate.of(1980, Month.JANUARY, 01);
      person2.setMedicalRecord(medicalRecord2);
      medicalRecord2.setBirthdate(person2Birthdate);
      person2.setAdress("1509 Culver St");

      List<Person> household = personService.childAlert("1509 Culver St");

      assertThat(household.contains(person1)).isFalse();
      assertThat(household.contains(person2)).isFalse();
      assertThat(household.size()).isEqualTo(0);
      assertThat(household.isEmpty()).isTrue();
   }

}
