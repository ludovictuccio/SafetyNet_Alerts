package com.safetynet.alerts.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * PersonController tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(MedicalRecordController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
public class MedicalRecordControllerTest {

   @Autowired
   private MockMvc mockMvc;

//   @Mock
//   private PersonService personService;
//
//   @Mock
//   private EntitiesInfosStorage entitiesInfosStorage;
//
//   private static List<Person> personsList;
//   private static Person person;
//
//   @BeforeEach
//   public void setUpPerTest() {
//      personsList = new ArrayList<>();
//      person = new Person("Donald", "Trump", "1509 Culver St", "Culver",
//                  "97451", "841-874-6512", "jaboyd@email.com");
//   }
//
//   @Test
//   @Tag("CreateMedicalRecord")
//   @DisplayName("CREATE - OK")
//   public void givenPersonWithoutMedicalrecord_whenCreation_thenReturnMedicalrecordAttribution()
//               throws Exception {
//
//      // Need to simulate an new person creation
//      Map<String, String> personToCreate = new HashMap<>();
//      Person newPerson = new Person(personToCreate.get("firstName").toString(),
//                  personToCreate.get("lastName").toString(),
//                  personToCreate.get("address").toString(),
//                  personToCreate.get("city").toString(),
//                  personToCreate.get("zip").toString(),
//                  personToCreate.get("phone").toString(),
//                  personToCreate.get("email").toString());
//
////      when(personService.createPerson(personToCreate))
////                  .thenReturn(entitiesInfosStorage.getPersonsList());
//
////      when(entitiesInfosStorage.getPersonsList()).thenReturn(personsList);
//
//      personService.createPerson(personToCreate);
//
//      this.mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
//                  .contentType(APPLICATION_JSON).content(" { \r\n"
//                              + "     \"firstName\":\"Donald\", \r\n"
//                              + "     \"lastName\":\"Trump\", \r\n"
//                              + "     \"birthdate\":\"01/01/1970\", \r\n"
//                              + "     \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \r\n"
//                              + "     \"allergies\":[\"coronavirus\"] \r\n"
//                              + "     }")
//                  .accept(APPLICATION_JSON))
//                  .andDo(MockMvcResultHandlers.print())
//                  .andExpect(status().isCreated());
//   }

   @Test
   @Tag("CreateMedicalRecord")
   @DisplayName("CREATE - ERROR ")
   public void givenUnknowPerson_whenCreation_thenReturnError()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                  .contentType(APPLICATION_JSON).content(" { \r\n"
                              + "     \"firstName\":\"Unknow\", \r\n"
                              + "     \"lastName\":\"Person\", \r\n"
                              + "     \"birthdate\":\"03/06/1984\", \r\n"
                              + "     \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \r\n"
                              + "     \"allergies\":[\"nillacilan\"] \r\n"
                              + "     }")
                  .accept(APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isConflict());
   }
}
