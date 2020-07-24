package com.safetynet.alerts.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * PersonController integration tests class.
 *
 * @author Ludovic Tuccio
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MedicalRecordControllerIT {

   @Autowired
   private MockMvc mockMvc;
   @Autowired
   private WebApplicationContext wac;

   @Before
   public void setupMockMvc() {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
   }

   @Test
   @Tag("CREATE")
   @DisplayName("CREATE - ERROR")
   public void givenPersonWithMedicalrecord_whenCreation_thenReturnConflict()
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

   @Test
   @Tag("UPDATE")
   @DisplayName("UPDATE - OK")
   public void givenPersonWithMedicalrecord_whenUpdate_thenReturnMedicalrecordUpdated()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
                  .contentType(APPLICATION_JSON).content(" { \r\n"
                              + "     \"firstName\":\"John\", \r\n"
                              + "     \"lastName\":\"Boyd\", \r\n"
                              + "     \"birthdate\":\"01/01/1950\", \r\n"
                              + "     \"medications\":[\"NEW MEDICATION:350mg\", \"hydrapermazol:100mg\"], \r\n"
                              + "     \"allergies\":[\"NEW ALLERGY\"] \r\n"
                              + "     }")
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("UPDATE")
   @DisplayName("UPDATE - ERROR")
   public void givenUnknowPerson_whenUpdate_thenReturnNotFoundedPerson()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
                  .contentType(APPLICATION_JSON).content(" { \r\n"
                              + "     \"firstName\":\"UNKNOW\", \r\n"
                              + "     \"lastName\":\"PERSON\", \r\n"
                              + "     \"birthdate\":\"01/01/1950\", \r\n"
                              + "     \"medications\":[\"NEW MEDICATION:350mg\", \"hydrapermazol:100mg\"], \r\n"
                              + "     \"allergies\":[\"NEW ALLERGY\"] \r\n"
                              + "     }"))
                  .andExpect(status().isNotFound());
   }

   @Test
   @Tag("DELETE")
   @DisplayName("DELETE - OK")
   public void givenPersonInTheListWithMedicalrecord_whenDeleteMedicalrecord_thenReturnDeleted()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                  .param("firstName", "John").param("lastName", "Boyd"))
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("DELETE")
   @DisplayName("DELETE - ERROR")
   public void givenUnknowPerson_whenDeleteMedicalrecord_thenReturnNotFounded()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                  .param("firstName", "Unknow").param("lastName", "Person"))
                  .andExpect(status().isNotFound());
   }

}
