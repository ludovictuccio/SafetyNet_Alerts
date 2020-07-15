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

   @Test
   @Tag("PUT")
   @DisplayName("Update - OK")
   public void givenAdressMappedWithStationThree_whenUpdateWithStationOne_thenReturnUpdated()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
                  .contentType(APPLICATION_JSON)
                  .content("{\"address\": \"1509 Culver St\",\"station\": \"1\"}")
                  .accept(APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("PUT")
   @DisplayName("Update - ERROR")
   public void givenValidAddress_whenUpdateWithUnknowStationNumber_thenReturnNotFound()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
                  .contentType(APPLICATION_JSON)
                  .content("{\"address\": \"Unknow address\",\"station\": \"3\"}"))
                  .andExpect(status().isNotFound());
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - OK")
   public void givenExistingAddress_whenDelete_thenReturnOk() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                  .contentType(APPLICATION_JSON).param("address", "29 15th St")
                  .accept(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
   }

   @Test
   @Tag("DELETE")
   @DisplayName("Delete - ERROR")
   public void givenNonexistantAddress_whenDelete_thenReturnNotFound()
               throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                  .contentType(APPLICATION_JSON).param("address", "Los angeles")
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect(status().isNotFound());
   }
}
