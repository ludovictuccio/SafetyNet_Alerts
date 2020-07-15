package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;

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
                  .updateFireStation(firestationMappingToUpdate);

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
                  .updateFireStation(firestationMappingToUpdate);

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
         fireStationService.updateFireStation(firestationMappingToUpdate);
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
                  .updateFireStation(firestationMappingToUpdate);

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

//   @Test
//   @DisplayName("Delete firestation")
//   public void givenFirestationEndpoint_whenDeleteFirestation_thenReturnDeletedFirestation() {
//
//       Map<Integer, FireStation> deleteFireStation(
//               final int firestationNumber, final String firestationAdress) ;
//
//      FireStation firestation1 = new FireStation("", 1);
//      FireStation firestation2 = new FireStation("", 2);
//      FireStation firestation3 = new FireStation("", 3);
//
//      Map<Integer, FireStation> firestationsList = new HashMap<>();
//      firestationsList.put(1, firestation1);
//      firestationsList.put(2, firestation2);
//      firestationsList.put(3, firestation3);
//
//      assertThat(firestationsList.size() == 3).isTrue();
//
//      fireStationService.deleteFireStation(2, "1509 Culver St");
//
//      assertThat(firestationsList.size() == 2).isTrue();
//   }
//
//   @Test
//   @DisplayName("stationNumber")
//   public void givenFirestationEndpoint_whenFirestationNumberInput_thenReturnPersonsUnderStationResponsibilityList() {
//
//AdultsAndChildrenPerStation stationNumber();
//   }
//
//   @Test
//   @DisplayName("Fire")
//   public void givenFireEndpoint_whenAdressInput_thenReturnHouseholdsInformationsAndFirestationResponsibleNumber() {
//
//List<Person> fire();
//   }
//
//   @Test
//   @DisplayName("Flood - One firestation")
//   public void givenFloodEndpoint_whenOneFirestationsNumberInput_thenReturnHouseholdsFirestationList() {
//List<Person> flood()
//   }
//
//   @Test
//   @DisplayName("Flood - Many firestation")
//   public void givenFloodEndpoint_whenManyFirestationsNumberInput_thenReturnHouseholdsFirestationList() {
//List<Person> flood()
//   }
//
//   @Test
//   @DisplayName("getPhoneAlert - correct firestation number")
//   public void givenPhoneAlert_whenCorrectFirestationNumberEntry_thenReturnAllPhoneNumberPersonsUnderStationResponsibilityList() {
//
//      fireStation.setStation(1);
//      fireStation.setAdresses("1509 Culver St");
//
//      List<String> phoneNumberList = fireStationService.getPhoneAlert(1);
//
//      assertThat(phoneNumberList.get(1)).isEqualTo("111-111-111");
//
//   }
//
//   @Test
//   @DisplayName("getPhoneAlert - incorrect firestation number")
//   public void givenPhoneAlert_whenIncorrectFirestationNumberEntry_thenReturnEmptyList() {
//
//      fireStation.setStation(1);
//
//      List<String> phoneNumberList = fireStationService.getPhoneAlert(0);
//
//      assertThat(phoneNumberList.isEmpty()).isTrue();
//   }

}
