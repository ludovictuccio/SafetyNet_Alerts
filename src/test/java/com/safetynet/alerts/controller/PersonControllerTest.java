package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
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
@WebMvcTest(PersonController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
public class PersonControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @Tag("CreatePerson")
   @DisplayName("CreatePerson - OK")
   public void givenPersonCreation_whenAllCorrectInfos_thenReturnPersonCreated()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.post("/person")
                  .contentType(APPLICATION_JSON)
                  .content("{\"firstName\": \"Ludovic\",\"lastName\": \"Tuccio\",\"address\": \"1 rue albert\",\"city\": \"Orleans\",\"zip\": \"45000\",\"phone\": \"06123456789\",\"email\": \"ludotuc@hot.fr\"}")
                  .accept(APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isCreated());
   }

   @Test
   @Tag("CreatePerson")
   @DisplayName("CreatePerson - ERROR ")
   public void givenPersonCreation_whenAlreadyExistingPerson_thenReturnErrorConflict()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.post("/person")
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content("{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                  .andExpect(status().isConflict());
   }

   @Test
   @Tag("UpdatePerson")
   @DisplayName("UpdatePerson - OK")
   public void givenUpdatingPerson_whenPersonExists_thenReturnUpdatedPerson()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/person")
                  .contentType(APPLICATION_JSON)
                  .content("{\"firstName\": \"Jacob\",\"lastName\": \"Boyd\",\"address\": \"1509 Culver St\",\"city\": \"Culver\",\"zip\": \"97451\",\"phone\": \"841-874-6513\",\"email\": \"drk@email.com\"}")
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("UpdatePerson")
   @DisplayName("UpdatePerson - ERROR ")
   public void givenUpdatingPerson_whenUnkowPerson_thenReturnPersonNotFounded()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/person")
                  .contentType(APPLICATION_JSON)
                  .content("{\"firstName\":\"UNKNOW\",\"lastName\":\"PERSON\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                  .andExpect(status().isNotFound());
   }

   @Test
   @Tag("DeletePerson")
   @DisplayName("DeletePerson - OK")
   public void givenPersonInTheList_whenDeletePerson_thenReturnDeletedPerson()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/person")
                  .contentType(APPLICATION_JSON)
                  .content("{\"firstName\": \"John\",\"lastName\": \"Boyd\"}")
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("DeletePerson")
   @DisplayName("DeletePerson - ERROR")
   public void givenUnknowPerson_whenDeletePerson_thenReturnPersonNotFounded()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/person")
                  .contentType(APPLICATION_JSON)
                  .content("{\"firstName\": \"Unknow\",\"lastName\": \"Person\"}")
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isNotFound());
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Valid city entry (Culver) ")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesListForCityPersons()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                  .contentType(APPLICATION_JSON).param("city", "Culver"))
                  .andExpect(status().isOk())
                  .andExpect(content().string("[\"jaboyd@email.com\","
                              + "\"drk@email.com\","
                              + "\"tenz@email.com\","
                              + "\"jaboyd@email.com\","
                              + "\"jaboyd@email.com\","
                              + "\"drk@email.com\","
                              + "\"tenz@email.com\","
                              + "\"jaboyd@email.com\","
                              + "\"jaboyd@email.com\","
                              + "\"tcoop@ymail.com\","
                              + "\"lily@email.com\","
                              + "\"soph@email.com\","
                              + "\"ward@email.com\","
                              + "\"zarc@email.com\","
                              + "\"reg@email.com\","
                              + "\"jpeter@email.com\","
                              + "\"jpeter@email.com\","
                              + "\"aly@imail.com\","
                              + "\"bstel@email.com\","
                              + "\"ssanw@email.com\","
                              + "\"bstel@email.com\","
                              + "\"clivfd@ymail.com\","
                              + "\"gramps@email.com\"]"))
                  .andExpect(jsonPath("$.length()", is(23)));
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Bad city entry (Los Angeles)")
   public void givenCityEntry_whenIncorrectCity_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                  .contentType(APPLICATION_JSON).param("city", "Los Angeles"))
                  .andExpect(status().isNotFound()).andExpect(content().string(
                              "[\"No person's email adresses founded for: Los Angeles\"]"));
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Correct last name")
   public void givenLastnameEntry_whenPersonInfoRequest_thenReturnInfosForPersonsWithThisLastname()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/personInfo")
                  .contentType(APPLICATION_JSON).param("firstName", "Tessa")
                  .param("lastName", "Carman")).andExpect(status().isOk())
                  .andExpect(jsonPath("$.length()", is(1)))
                  .andExpect(content().string(
                              "[{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"city\":\"Culver\",\"zip\":\"97451\",\"email\":\"tenz@email.com\",\"medicalRecord\":{\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[],\"age\":8}}]"));
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow last name")
   public void givenUnknowLastnameEntry_whenPersonInfoRequest_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/personInfo")
                  .contentType(APPLICATION_JSON).param("firstName", "Unknow")
                  .param("lastName", "Unknow")).andExpect(status().isNotFound())
                  .andExpect(jsonPath("$.length()", is(0)));
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address with children)")
   public void givenAddressWithChildrenAnAdults_whenChildAlert_thenReturnHouseholdComposition()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/childAlert")
                  .contentType(APPLICATION_JSON)
                  .param("address", "1509 Culver St"))
                  .andExpect(status().isOk()).andExpect(content().string(
                              "[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"age\":36},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"age\":31},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"age\":8},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"age\":2},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"age\":34}]"));
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address without child")
   public void givenAddressWithoutChild_whenChildAlert_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/childAlert")
                  .contentType(APPLICATION_JSON).param("address", "Unknow"))
                  .andExpect(status().isNotFound())
                  .andExpect(jsonPath("$.length()", is(0)));
   }

}
