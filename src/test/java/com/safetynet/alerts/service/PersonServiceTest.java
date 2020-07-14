package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.EntitiesInfosStorage;
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

      List<PersonInfoDTO> result = personService.personInfo("John", "Boyd");

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

      List<PersonInfoDTO> result = personService.personInfo("Unknow", "Person");

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
      List<ChildAlertDTO> childAlert = new ArrayList<>();

      // Child 1 - Good address
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordChild);
      int childAge = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      ChildAlertDTO child1 = new ChildAlertDTO(childAge, person1.getFirstName(),
                  person1.getLastName());
      childAlert.add(child1);

      // Adult - Good address
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      ChildAlertDTO adult1 = new ChildAlertDTO(adultAge, person2.getFirstName(),
                  person2.getLastName());
      childAlert.add(adult1);

      // Child 2 - Bad address
      person3 = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalRecordChild);
      int childAge2 = AgeCalculator
                  .ageCalculation(person3.getMedicalRecord().getBirthdate());
      ChildAlertDTO child2 = new ChildAlertDTO(childAge2,
                  person3.getFirstName(), person3.getLastName());
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

      List<ChildAlertDTO> result = personService.childAlert("1509 Culver St");

      assertThat(result.size()).isEqualTo(2);
      assertThat(result.isEmpty()).isFalse();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address without children")
   public void givenTwoAdultsOnTheSameAddress_whenChildAlertAddress_thenReturnEmptyList() {
      List<ChildAlertDTO> childAlert = new ArrayList<>();

      // Adult 1
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordAdult);
      int adultAge1 = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      ChildAlertDTO adult1 = new ChildAlertDTO(adultAge1,
                  person1.getFirstName(), person1.getLastName());
      childAlert.add(adult1);

      // Adult 2
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge2 = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      ChildAlertDTO adult2 = new ChildAlertDTO(adultAge2,
                  person2.getFirstName(), person2.getLastName());
      childAlert.add(adult2);

      List<Person> householdMembersList = new ArrayList<>();
      householdMembersList.add(person1);
      householdMembersList.add(person2);

      Map<String, List<Person>> households = new HashMap<>();
      households.put(person1.getAddress(), householdMembersList);
      households.put(person2.getAddress(), householdMembersList);

      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      List<ChildAlertDTO> result = personService.childAlert("1509 Culver St");

      assertThat(result.size()).isEqualTo(0);
      assertThat(result.isEmpty()).isTrue();
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Unknow address")
   public void givenUnknowAddressEntered_whenChildAlert_thenReturnEmptyList() {
      List<ChildAlertDTO> childAlert = new ArrayList<>();

      // Adult 1
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com", medicalRecordChild);
      int adultAge1 = AgeCalculator
                  .ageCalculation(person1.getMedicalRecord().getBirthdate());
      ChildAlertDTO adult1 = new ChildAlertDTO(adultAge1,
                  person1.getFirstName(), person1.getLastName());
      childAlert.add(adult1);

      // Adult 2
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com", medicalRecordAdult);
      int adultAge2 = AgeCalculator
                  .ageCalculation(person2.getMedicalRecord().getBirthdate());
      ChildAlertDTO adult2 = new ChildAlertDTO(adultAge2,
                  person2.getFirstName(), person2.getLastName());
      childAlert.add(adult2);

      List<Person> householdMembersList = new ArrayList<>();
      householdMembersList.add(person1);
      householdMembersList.add(person2);

      Map<String, List<Person>> households = new HashMap<>();
      households.put(person1.getAddress(), householdMembersList);
      households.put(person2.getAddress(), householdMembersList);

      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      List<ChildAlertDTO> result = personService.childAlert("Unknow address");

      assertThat(result.size()).isEqualTo(0);
      assertThat(result.isEmpty()).isTrue();
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - New person")
   public void givenPersonEndpoint_whenCreateNewPerson_thenReturnPersonCreated() {
      // GIVEN
      Map<String, String> personToCreate = new HashMap<String, String>();
      Person newPerson = new Person("New", "Person", "address", "City", "zip",
                  "phone", "email");
      personToCreate.put("firstName", newPerson.getFirstName());
      personToCreate.put("lastName", newPerson.getLastName());
      personToCreate.put("address", newPerson.getAddress());
      personToCreate.put("city", newPerson.getCity());
      personToCreate.put("zip", newPerson.getZip());
      personToCreate.put("phone", newPerson.getPhone());
      personToCreate.put("email", newPerson.getEmail());

      List<Person> personsList = new ArrayList<>();
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Map<String, List<Person>> households = new HashMap<>();
      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      // WHEN
      Person result = personService.createPerson(personToCreate);

      // THEN
      assertThat(result).isNotNull()
                  .hasFieldOrPropertyWithValue("firstName",
                              newPerson.getFirstName())
                  .hasFieldOrPropertyWithValue("lastName",
                              newPerson.getLastName())
                  .hasFieldOrPropertyWithValue("address",
                              newPerson.getAddress())
                  .hasFieldOrPropertyWithValue("city", newPerson.getCity())
                  .hasFieldOrPropertyWithValue("zip", newPerson.getZip())
                  .hasFieldOrPropertyWithValue("phone", newPerson.getPhone())
                  .hasFieldOrPropertyWithValue("email", newPerson.getEmail());
      assertThat(personsList.contains(result)).isTrue();
      assertThat(households.containsKey(result.getAddress())).isTrue();
      assertThat(households.get(result.getAddress()).contains(result));
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - Verification persons added to all Persons list")
   public void givenListWithOnePerson_whenSuccessNewPersonCreation_thenReturnSizeListAtTwo() {
      // GIVEN
      Map<String, String> personToCreate = new HashMap<String, String>();
      Person householdPerson1 = new Person("New", "Person", "1509 Culver St",
                  "Culver", "97451", "841-874-6512", "jaboyd@email.com");
      personToCreate.put("firstName", householdPerson1.getFirstName());
      personToCreate.put("lastName", householdPerson1.getLastName());
      personToCreate.put("address", householdPerson1.getAddress());
      personToCreate.put("city", householdPerson1.getCity());
      personToCreate.put("zip", householdPerson1.getZip());
      personToCreate.put("phone", householdPerson1.getPhone());
      personToCreate.put("email", householdPerson1.getEmail());

      List<Person> personsList = new ArrayList<>();
      Person householdPerson2 = new Person("John", "Boyd", "1509 Culver St",
                  "Culver", "97451", "841-874-6512", "jaboyd@email.com");
      personsList.add(householdPerson2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Map<String, List<Person>> households = new HashMap<>();
      when(entitiesInfosStorage.getHouseholds()).thenReturn(households);

      // WHEN
      Person result = personService.createPerson(personToCreate);

      // THEN
      assertThat(personsList.contains(result)).isTrue();
      assertThat(households.containsKey(result.getAddress())).isTrue();
      assertThat(households.get(result.getAddress()).contains(result));
      assertThat(personsList.size()).isEqualTo(2);
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - Existing person - List size unchanged")
   public void givenPersonEndpoint_whenCreateExistingPerson_thenReturnListSizeUnchanged() {
      // GIVEN
      Map<String, String> personToCreate = new HashMap<String, String>();
      Person newPerson = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com");
      personToCreate.put("firstName", newPerson.getFirstName());
      personToCreate.put("lastName", newPerson.getLastName());
      personToCreate.put("address", newPerson.getAddress());
      personToCreate.put("city", newPerson.getCity());
      personToCreate.put("zip", newPerson.getZip());
      personToCreate.put("phone", newPerson.getPhone());
      personToCreate.put("email", newPerson.getEmail());

      List<Person> personsList = new ArrayList<>();
      Person existingPerson = new Person("John", "Boyd", "1509 Culver St",
                  "Culver", "97451", "841-874-6512", "jaboyd@email.com");
      personsList.add(existingPerson);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      Person result = personService.createPerson(personToCreate);

      // THEN
      assertThat(personsList.contains(result)).isFalse();
      assertThat(personsList.contains(existingPerson)).isTrue();
      assertThat(personsList.size()).isEqualTo(1);
   }

   @Test
   @Tag("POST")
   @DisplayName("Create - Null information entered")
   public void givenExistingPersonCreation_whenEmptyInformationEntered_thenReturnException() {
      Map<String, String> personToCreate = new HashMap<String, String>();
      Person personWithoutFirstname = new Person("", "Person", "address",
                  "City", "zip", "phone", "email");
      personToCreate.put("firstName", personWithoutFirstname.getFirstName());
      personToCreate.put("lastName", personWithoutFirstname.getLastName());
      personToCreate.put("address", personWithoutFirstname.getAddress());
      personToCreate.put("city", personWithoutFirstname.getCity());
      personToCreate.put("zip", personWithoutFirstname.getZip());
      personToCreate.put("phone", personWithoutFirstname.getPhone());
      personToCreate.put("email", personWithoutFirstname.getEmail());

      assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                  .isThrownBy(() -> {
                     personService.createPerson(personToCreate);
                  });
   }

   @Test
   @Tag("PUT")
   @DisplayName("Update - Existing person ")
   public void givenPersonEndpoint_whenUpdatePerson_thenReturnUpdatedPerson() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com");
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Person personToUpdate = new Person("John", "Boyd", "NEW ADDRESS", "city",
                  "zip", "phone", "email");

      // WHEN
      personService.updatePerson(personToUpdate);

      // THEN
      assertThat(personsList.size()).isEqualTo(1);
      assertThat(personsList.toString()).contains(personToUpdate.toString());
   }

   @Test
   @Tag("PUT")
   @DisplayName("Update - Unknow person")
   public void givenPersonEndpoint_whenUpdateUnknowPerson_thenReturnNull() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com");
      personsList.add(person1);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Person personToUpdate = new Person("UNKNOW", "PERSON", "NEW ADDRESS",
                  "city", "zip", "phone", "email");

      // WHEN
      personService.updatePerson(personToUpdate);

      // THEN
      assertThat(personsList.size()).isEqualTo(1);
      assertThat(personsList.toString())
                  .doesNotContain(personToUpdate.toString());
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - Existing person")
   public void givenPersonEndpoint_whenDeletePerson_thenReturnDeletedPerson() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com");
      person2 = new Person("Jacob", "Tenley", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Person personToDelete = person1;

      // WHEN
      personService.deletePerson(personToDelete);

      // THEN
      assertThat(personsList.size()).isEqualTo(1);
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - Unknow person")
   public void givenPersonEndpoint_whenDeleteUnknowPerson_thenReturnAllPersonsListSizeUnchanged() {
      // GIVEN
      List<Person> personsList = new ArrayList<>();
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com");
      person2 = new Person("Jacob", "Tenley", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      Person personToDelete = new Person("Unknow", "Person", "1509 Culver St",
                  "Culver", "97451", "841-874-6512", "jaboyd@email.com");

      // WHEN
      personService.deletePerson(personToDelete);

      // THEN
      assertThat(personsList.size()).isEqualTo(2); // Check unchanged size list
   }

}
