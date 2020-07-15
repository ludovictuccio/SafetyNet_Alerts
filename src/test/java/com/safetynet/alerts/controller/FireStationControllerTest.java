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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * FireStationController tests class.
 *
 * @author Ludovic Tuccio
 */
@WebMvcTest(FireStationController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
@ComponentScan({ "com.safetynet.alerts.service", "com.safetynet.alerts.model" })
public class FireStationControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @Tag("POST")
   @DisplayName("AddAddress - OK")
   public void givenExistingFireStationAndNonAlreadyAddedAddress_whenAdd_thenReturnAdded()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
                  .contentType(APPLICATION_JSON)
                  .content("{\"address\": \"NEW ADDRESS\",\"station\": \"1\"}")
                  .accept(APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isCreated());
   }

   @Test
   @Tag("POST")
   @DisplayName("AddAddress - ERROR - Nonexistant station")
   public void givenNonexistantFirestation_whenAdd_thenReturnConflict()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content("{\"address\": \"NEW ADDRESS\",\"station\": \"999\"}"))
                  .andExpect(status().isConflict());
   }
}
