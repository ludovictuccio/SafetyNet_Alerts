package com.safetynet.alerts.unit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safetynet.alerts.controller.PersonController;

/**
 * PersonController unit tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(PersonController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
public class PersonControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @Order(1)
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Valid city entry (Culver) ")
   public void givenCityEntry_whenExistingCity_thenReturnAllPersonsEmailAdressesListForCityPersons()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders
                  .get("/communityEmail").param("city", "Culver"))
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
                  .andExpect(status().isOk());
   }

   @Test
   @Order(2)
   @Tag("CommunityEmail")
   @DisplayName("CommunityEmail - Bad city entry (Los Angeles)")
   public void givenCityEntry_whenIncorrectCity_thenReturnEmptyList()
               throws Exception {
      this.mockMvc.perform(get("/communityEmail").param("city", "Los Angeles"))
                  .andExpect(status().isNotFound()).andExpect(content().string(
                              "[\"No person's email adresses founded for: Los Angeles\"]"));
   }

}
