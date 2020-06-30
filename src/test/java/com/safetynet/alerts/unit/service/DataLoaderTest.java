package com.safetynet.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.DataLoader;

/**
 * DataLoader units tests class.
 *
 * @author Ludovic Tuccio
 *
 */
@WebMvcTest(DataLoader.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class DataLoaderTest {

   private DataLoader dataLoader;

   private String jsonFile = "./src/main/resources/data.json";

   @BeforeEach
   private void setUp() throws IOException {
      dataLoader = new DataLoader();
   }

   @Test
   @Order(1)
   @Tag("searchPerson")
   @DisplayName("SearchPerson - Valid person")
   public void givenValidPerson_whenSearchPerson_thenReturnPerson() {
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com");
      Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);

      Person personFounded = dataLoader.searchPerson("Jacob", "Boyd",
                  personsList);

      assertThat(personFounded).isNotNull();
      assertThat(personsList).contains(personFounded);
   }

   @Test
   @Order(2)
   @Tag("searchPerson")
   @DisplayName("SearchPerson - Unknow person")
   public void givenUnknowPerson_whenSearchPerson_thenReturnIllegalArgumentException()
               throws IOException {
      List<Person> personsList = new ArrayList<>();
      Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6512", "jaboyd@email.com");
      Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver",
                  "97451", "841-874-6513", "drk@email.com");
      personsList.add(person1);
      personsList.add(person2);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         dataLoader.searchPerson("Unknow", "Person", personsList);
      });
   }

   @Test
   @Order(3)
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Null json file ")
   public void givenNullFilepath_whenReadJsonFile_thenReturnNullPointerException()
               throws IOException {

      assertThatNullPointerException().isThrownBy(() -> {
         dataLoader.readJsonFile(null);
      });
   }

   @Test
   @Order(4)
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Person")
   public void givenCorrectFilepath_whenReadJsonFileWithPersons_thenReturnVariableLoaded()
               throws IOException {

      EntitiesInfosStorage entitiesInfoStorage = dataLoader
                  .readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getPersonsList()).isNotNull();
   }

   @Test
   @Order(5)
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - FireStations")
   public void givenCorrectFilepath_whenReadJsonFileWithFireStations_thenReturnVariableLoaded()
               throws IOException {

      EntitiesInfosStorage entitiesInfoStorage = dataLoader
                  .readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getFirestations()).isNotNull();
   }

   @Test
   @Order(6)
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Households")
   public void givenCorrectFilepath_whenReadJsonFileWithHouseholds_thenReturnVariableLoaded()
               throws IOException {

      EntitiesInfosStorage entitiesInfoStorage = dataLoader
                  .readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getPersonsPerHousehold()).isNotNull();
   }

}
