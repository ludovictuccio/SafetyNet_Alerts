package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.PersonStationCounterDTO;
import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * FireStationService units tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(FireStationService.class)
@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

   @InjectMocks
   private FireStationService fireStationService;
   @Mock
   private EntitiesInfosStorage entitiesInfosStorage;

   @Test
   @Tag("CREATE")
   @DisplayName("Create - OK")
   public void givenExistingStationAndNoEmptyAddress_whenCreate_thenReturnNewMapping() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);

      // Add a new address for firestation1
      Map<String, String> firestationMappingToCreate = new HashMap<String, String>();
      firestationMappingToCreate.put("station", "1");
      firestationMappingToCreate.put("address", "new address");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isAdded = fireStationService
                  .addAddressForFirestation(firestationMappingToCreate);

      assertThat(isAdded).isTrue();
      assertThat(firestationsList.get("1").getAddresses()
                  .contains("new address")).isTrue();
   }

   @Test
   @Tag("CREATE")
   @DisplayName("Create - Bad firestation entered")
   public void givenNonexistantStationNumber_whenCreate_thenReturnNull() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);

      // Add a new address for firestation1
      Map<String, String> firestationMappingToCreate = new HashMap<String, String>();
      firestationMappingToCreate.put("station", "488");
      firestationMappingToCreate.put("address", "new address");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      assertThatNullPointerException().isThrownBy(() -> {
         fireStationService
                     .addAddressForFirestation(firestationMappingToCreate);
      });
   }

   @Test
   @Tag("CREATE")
   @DisplayName("Create - Existing address")
   public void givenExistingMapping_whenCreate_thenReturnNull() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");

      // Initilization station=2 with 1 addresses
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("29 15th St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);

      // Add existant address
      Map<String, String> firestationMappingToCreate = new HashMap<String, String>();
      firestationMappingToCreate.put("station", "2");
      firestationMappingToCreate.put("address", "908 73rd St");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isAdded = fireStationService
                  .addAddressForFirestation(firestationMappingToCreate);

      assertThat(isAdded).isFalse();
      assertThat(firestationsList.get("2").getAddresses()
                  .contains("908 73rd St")).isFalse();
   }

   @Test
   @Tag("UPDATE")
   @DisplayName("Update - OK")
   public void givenExistingAddressAndExistingStation_whenUpdate_thenReturnUpdated() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");

      // Initilization station=2 with 1 addresses
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("29 15th St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);

      // Add existing address for other station
      Map<String, String> firestationMappingToUpdate = new HashMap<String, String>();
      firestationMappingToUpdate.put("station", "2");
      firestationMappingToUpdate.put("address", "908 73rd St");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isUpdated = fireStationService
                  .updateAddressForFireStation(firestationMappingToUpdate);

      assertThat(isUpdated).isTrue();
      assertThat(firestationsList.get("1").getAddresses()
                  .contains("908 73rd St")).isFalse();
      assertThat(firestationsList.get("2").getAddresses()
                  .contains("908 73rd St")).isTrue();
   }

   @Test
   @Tag("UPDATE")
   @DisplayName("Update - OK - browse all list")
   public void givenThreeStationsToBrowse_whenUpdate_thenReturnUpdated() {
      // Initilization station=1 with 1 address
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("644 Gershwin Cir");

      // Initilization station=2 with 1 addresses
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("29 15th St");

      // Initilization station=3 with 1 addresses
      FireStation firestation3 = new FireStation("3");
      firestation3.addAddress("908 73rd St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      firestationsList.put("3", firestation3);

      // Add existing address for other station
      Map<String, String> firestationMappingToUpdate = new HashMap<String, String>();
      firestationMappingToUpdate.put("station", "2");
      firestationMappingToUpdate.put("address", "908 73rd St");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isUpdated = fireStationService
                  .updateAddressForFireStation(firestationMappingToUpdate);

      assertThat(isUpdated).isTrue();
      assertThat(firestationsList.get("3").getAddresses()
                  .contains("908 73rd St")).isFalse();
      assertThat(firestationsList.get("2").getAddresses()
                  .contains("908 73rd St")).isTrue();
   }

   @Test
   @Tag("UPDATE")
   @DisplayName("Update - Bad firestation entered")
   public void givenExistingAddressAndNonExistantStation_whenUpdate_thenReturnError() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);

      // Add a new address for firestation1
      Map<String, String> firestationMappingToUpdate = new HashMap<String, String>();
      firestationMappingToUpdate.put("station", "488");
      firestationMappingToUpdate.put("address", "908 73rd St");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      assertThatNullPointerException().isThrownBy(() -> {
         fireStationService
                     .updateAddressForFireStation(firestationMappingToUpdate);
      });
   }

   @Test
   @Tag("UPDATE")
   @DisplayName("Update - Bad address entered")
   public void givenNonexistantAddressEntered_whenUpdate_thenReturnError() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");

      // Initilization station=2 with 1 addresses
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("29 15th St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);

      // Add existant address
      Map<String, String> firestationMappingToUpdate = new HashMap<String, String>();
      firestationMappingToUpdate.put("station", "2");
      firestationMappingToUpdate.put("address", "Unknow address");

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isUpdated = fireStationService
                  .updateAddressForFireStation(firestationMappingToUpdate);

      assertThat(isUpdated).isFalse();
      assertThat(firestationsList.get("1").getAddresses()
                  .contains("908 73rd St")).isTrue();
      assertThat(firestationsList.get("1").getAddresses()
                  .contains("644 Gershwin Cir")).isTrue();
      assertThat(firestationsList.get("2").getAddresses()
                  .contains("29 15th St")).isTrue();
      assertThat(firestationsList.get("2").getAddresses()
                  .contains("Unknow address")).isFalse();
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - OK - Valid address")
   public void givenValidAddress_whenDelete_thenReturnAddressDeletedFomStationMapping() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isDeleted = fireStationService
                  .deleteAddressForFireStation("908 73rd St");

      assertThat(isDeleted).isTrue();
      assertThat(firestationsList.get("1").getAddresses()
                  .contains("908 73rd St")).isFalse();
      assertThat(firestationsList.size()).isEqualTo(1);
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - Bad address entered")
   public void givenUnknowAddress_whenDelete_thenReturnFalse() {

      // Initilization station=1 with 2 addresses
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);

      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      boolean isDeleted = fireStationService
                  .deleteAddressForFireStation("Unknow address");

      assertThat(isDeleted).isFalse();
   }

   @Test
   @Tag("FirestationNumber")
   @DisplayName("FirestationNumber - Valid station number")
   public void givenOneAdultLeavingAtTheStationCoverageAddress_whenStationNumberEntry_thenReturnCorrectData() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      List<Person> personsList = new ArrayList<>();
      MedicalRecord medicalrecordAdult = new MedicalRecord("01/01/1990", null,
                  null);
      MedicalRecord medicalrecordChild = new MedicalRecord("01/01/2020", null,
                  null);
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalrecordAdult);
      Person person2 = new Person("Eric", "Cadigan", "951 LoneTree Rd",
                  "Culver", "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordChild);
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      PersonStationCounterDTO result = fireStationService
                  .firestationNumber("2");

      // THEN
      assertThat(result).isNotNull();
      assertThat(result.getPersonsStationList().size()).isEqualTo(1);
      assertThat(result.getTotalAdultsNumber()).isEqualTo(1);
      assertThat(result.getTotalChildrenNumber()).isEqualTo(0);
   }

   @Test
   @Tag("FirestationNumber")
   @DisplayName("FirestationNumber - Unknow station number")
   public void givenUnknowStationNumber_whenStationNumberEntry_thenReturnNullPointerException() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      // WHEN
      PersonStationCounterDTO result = fireStationService
                  .firestationNumber("499");
      // THEN
      assertThat(result.getPersonsStationList()).isNull();
   }

   @Test
   @Tag("PhoneAlert")
   @DisplayName("PhoneAlert - Valid station number")
   public void givenStationTwoWithTwoPersons_whenStationTwoEntry_thenReturnTwoPhoneNumbers() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com");
      Person person2 = new Person("Eric", "Cadigan", "1509 Culver St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com");
      Person person3 = new Person("Tessa", "Carman", "908 73rd St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com");
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      List<String> result = fireStationService.phoneAlert("2");

      // THEN
      assertThat(result).isNotNull();
      assertThat(result.size()).isEqualTo(2);
      assertThat(result.contains(person1.getPhone())).isTrue();
      assertThat(result.contains(person2.getPhone())).isTrue();
   }

   @Test
   @Tag("PhoneAlert")
   @DisplayName("PhoneAlert - Unknow station number")
   public void givenUnknowStation_whenPhoneAlert_thenReturnEmptyList() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      firestation1.addAddress("644 Gershwin Cir");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      List<Person> personsList = new ArrayList<>();
      MedicalRecord medicalrecordAdult = new MedicalRecord("01/01/1990", null,
                  null);
      MedicalRecord medicalrecordChild = new MedicalRecord("01/01/2020", null,
                  null);
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalrecordAdult);
      Person person2 = new Person("Eric", "Cadigan", "951 LoneTree Rd",
                  "Culver", "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordChild);
      personsList.add(person1);
      personsList.add(person2);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      List<String> result = fireStationService.phoneAlert("444");

      // THEN
      assertThat(result.isEmpty()).isTrue();
   }

   @Test
   @Tag("Fire")
   @DisplayName("Fire - Valid address")
   public void givenValidAddress_whenFire_thenReturnCorrectListReturned() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      List<Person> personsList = new ArrayList<>();
      MedicalRecord medicalrecordAdult = new MedicalRecord("01/01/1990", null,
                  null);
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalrecordAdult);
      Person person2 = new Person("Eric", "Cadigan", "1509 Culver St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordAdult);
      Person person3 = new Person("Tessa", "Carman", "908 73rd St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordAdult);
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      List<FireDTO> result = fireStationService.fire("1509 Culver St");

      // THEN
      assertThat(result).isNotNull();
      assertThat(result.size()).isEqualTo(2);
      assertThat(result.toString().contains(person1.getFirstName())).isTrue();
      assertThat(result.toString().contains(person2.getFirstName())).isTrue();
      assertThat(result.toString().contains(person3.getFirstName())).isFalse();
   }

   @Test
   @Tag("Fire")
   @DisplayName("Fire - Unknow address - Empty list")
   public void givenUnknowAddress_whenFire_thenReturnEmptyList() {
      // GIVEN
      FireStation firestation1 = new FireStation("1");
      firestation1.addAddress("908 73rd St");
      FireStation firestation2 = new FireStation("2");
      firestation2.addAddress("1509 Culver St");

      Map<String, FireStation> firestationsList = new HashMap<String, FireStation>();
      firestationsList.put("1", firestation1);
      firestationsList.put("2", firestation2);
      when(entitiesInfosStorage.getFirestations()).thenReturn(firestationsList);

      List<Person> personsList = new ArrayList<>();
      MedicalRecord medicalrecordAdult = new MedicalRecord("01/01/1990", null,
                  null);
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com",
                  medicalrecordAdult);
      Person person2 = new Person("Eric", "Cadigan", "1509 Culver St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordAdult);
      Person person3 = new Person("Tessa", "Carman", "908 73rd St", "Culver",
                  "97451", "841-874-7458", "gramps@email.com",
                  medicalrecordAdult);
      personsList.add(person1);
      personsList.add(person2);
      personsList.add(person3);
      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);

      // WHEN
      List<FireDTO> result = fireStationService.fire("Unknow address");

      // THEN
      assertThat(result.isEmpty()).isTrue();
   }
}
