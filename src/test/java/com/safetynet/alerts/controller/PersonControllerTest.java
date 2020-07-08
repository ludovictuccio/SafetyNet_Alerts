package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * PersonController unit tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(PersonController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
public class PersonControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext webContext;

   @Before
   public void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
   }

   @Test
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Valid city entry (Culver) ")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesListForCityPersons()
               throws Exception {
      this.mockMvc.perform(get("/communityEmail")
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
      this.mockMvc.perform(get("/communityEmail").contentType(APPLICATION_JSON)
                  .param("city", "Los Angeles"))
                  .andExpect(status().isNotFound()).andExpect(content().string(
                              "[\"No person's email adresses founded for: Los Angeles\"]"));
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Correct last name")
   public void givenLastnameEntry_whenPersonInfoRequest_thenReturnInfosForPersonsWithThisLastname()
               throws Exception {
      this.mockMvc.perform(get("/personInfo").contentType(APPLICATION_JSON)
                  .param("firstName", "Tessa").param("lastName", "Carman"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.length()", is(1)))
                  .andExpect(content().string(
                              "[{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"tenz@email.com\",\"medicalRecord\":{\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[],\"age\":8}}]"));
   }

   @Test
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow last name")
   public void givenUnknowLastnameEntry_whenPersonInfoRequest_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(get("/personInfo").contentType(APPLICATION_JSON)
                  .param("firstName", "Unknow").param("lastName", "Unknow"))
                  .andExpect(status().isNotFound())
                  .andExpect(jsonPath("$.length()", is(0)));
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address with children)")
   public void givenAddressWithChildrenAnAdults_whenChildAlert_thenReturnHouseholdComposition()
               throws Exception {
      this.mockMvc.perform(get("/childAlert").contentType(APPLICATION_JSON)
                  .param("address", "1509 Culver St"))
                  .andExpect(status().isOk()).andExpect(content().string(
                              "[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"age\":36},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"age\":31},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"age\":8},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"age\":2},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"age\":34}]"));
   }

   @Test
   @Tag("ChildAlert")
   @DisplayName("ChildAlert - Address without child")
   public void givenAddressWithoutChild_whenChildAlert_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(get("/childAlert").contentType(APPLICATION_JSON)
                  .param("address", "Unknow")).andExpect(status().isNotFound())
                  .andExpect(jsonPath("$.length()", is(0)));
   }

}
