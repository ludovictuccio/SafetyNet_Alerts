//package com.safetynet.alerts.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.safetynet.alerts.model.FireStation;
//import com.safetynet.alerts.service.FireStationService;
//import com.safetynet.alerts.service.PersonService;
//
///**
// * FireStationService units tests class.
// *
// * @author Ludovic Tuccio
// *
// */
//public class FireStationServiceTest {
//
//   @MockBean
//   private static FireStationService fireStationService;
//   @MockBean
//   private static PersonService personService;
//
//   private static FireStation fireStation;
//
//   @BeforeAll
//   private static void setUp() {
//      fireStationService = new FireStationService();
//      personService = new PersonService();
//   }
//
//   @Test
//   @DisplayName("Create firestation")
//   public void givenFirestationEndpoint_whenCreateFirestation_thenReturnFirestationCreation() {
//
////InfosRetrieval createFireStation(final FireStation firestation);
//   }
//
//   @Test
//   @DisplayName("Update firestation")
//   public void givenFirestationEndpoint_whenUpdateFirestation_thenReturnUpdatedFirestation() {
//
////      Map<Integer, FireStation> updateFireStation(
////                  final int firestationNumber, final String firestationAdress);
//
//   }
//
////   @Test
////   @DisplayName("Delete firestation")
////   public void givenFirestationEndpoint_whenDeleteFirestation_thenReturnDeletedFirestation() {
////
//////        Map<Integer, FireStation> deleteFireStation(
//////               final int firestationNumber, final String firestationAdress) ;
////
////      FireStation firestation1 = new FireStation("", 1);
////      FireStation firestation2 = new FireStation("", 2);
////      FireStation firestation3 = new FireStation("", 3);
////
////      Map<Integer, FireStation> firestationsList = new HashMap<>();
////      firestationsList.put(1, firestation1);
////      firestationsList.put(2, firestation2);
////      firestationsList.put(3, firestation3);
////
////      assertThat(firestationsList.size() == 3).isTrue();
////
////      fireStationService.deleteFireStation(2, "1509 Culver St");
////
////      assertThat(firestationsList.size() == 2).isTrue();
////   }
//
//   @Test
//   @DisplayName("stationNumber")
//   public void givenFirestationEndpoint_whenFirestationNumberInput_thenReturnPersonsUnderStationResponsibilityList() {
//
////AdultsAndChildrenPerStation stationNumber();
//   }
//
//   @Test
//   @DisplayName("Fire")
//   public void givenFireEndpoint_whenAdressInput_thenReturnHouseholdsInformationsAndFirestationResponsibleNumber() {
//
////List<Person> fire();
//   }
//
//   @Test
//   @DisplayName("Flood - One firestation")
//   public void givenFloodEndpoint_whenOneFirestationsNumberInput_thenReturnHouseholdsFirestationList() {
////List<Person> flood()
//   }
//
//   @Test
//   @DisplayName("Flood - Many firestation")
//   public void givenFloodEndpoint_whenManyFirestationsNumberInput_thenReturnHouseholdsFirestationList() {
////List<Person> flood()
//   }
//
////   @Test
////   @DisplayName("getPhoneAlert - correct firestation number")
////   public void givenPhoneAlert_whenCorrectFirestationNumberEntry_thenReturnAllPhoneNumberPersonsUnderStationResponsibilityList() {
////
////      fireStation.setStation(1);
////      fireStation.setAdresses("1509 Culver St");
////
////      List<String> phoneNumberList = fireStationService.getPhoneAlert(1);
////
////      assertThat(phoneNumberList.get(1)).isEqualTo("111-111-111");
////
////   }
////
////   @Test
////   @DisplayName("getPhoneAlert - incorrect firestation number")
////   public void givenPhoneAlert_whenIncorrectFirestationNumberEntry_thenReturnEmptyList() {
////
////      fireStation.setStation(1);
////
////      List<String> phoneNumberList = fireStationService.getPhoneAlert(0);
////
////      assertThat(phoneNumberList.isEmpty()).isTrue();
////   }
//
//}
