package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * PersonController unit tests class.
 *
 * @author Ludovic Tuccio
 */
//@WebMvcTest(PersonController.class)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@TestMethodOrder(OrderAnnotation.class)
//@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
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
   @Order(1)
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
   @Order(2)
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
   @Order(3)
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Correct last name")
   public void givenLastnameEntry_whenPersonInfoRequest_thenReturnInfosForPersonsWithThisLastname()
               throws Exception {
      this.mockMvc.perform(get("/personInfo").contentType(APPLICATION_JSON)
                  .param("firstName", "Tessa").param("lastName", "Carman"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.length()", is(1)))
                  .andExpect(content().string(
                              "[{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"age\":8,\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"tenz@email.com\",\"medicalRecord\":{\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[],\"age\":8},\"adress\":\"834 Binoc Ave\"}]"));
   }

   @Test
   @Order(4)
   @Tag("PersonInfo")
   @DisplayName("PersonInfo - Unknow last name")
   public void givenUnknowLastnameEntry_whenPersonInfoRequest_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(get("/personInfo").contentType(APPLICATION_JSON)
                  .param("firstName", "Unknow").param("lastName", "Unknow"))
                  .andExpect(status().isNotFound())
                  .andExpect(jsonPath("$.length()", is(0)));
   }

}
