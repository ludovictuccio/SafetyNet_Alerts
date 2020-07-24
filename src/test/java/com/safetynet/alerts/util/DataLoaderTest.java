package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Person;

/**
 * DataLoader units tests class.
 *
 * @author Ludovic Tuccio
 */
@SpringBootTest
public class DataLoaderTest {

   private EntitiesInfosStorage entitiesInfoStorage;

   @Value("${info.data}")
   private String jsonFile;

   private Person person1;
   private Person person2;
   private Person personFounded;

   @BeforeEach
   private void setUp() throws IOException {
      person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6512", "jaboyd@email.com");
      person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451",
                  "841-874-6513", "drk@email.com");
   }

   @Test
   @Tag("searchPerson")
   @DisplayName("SearchPerson - Valid person")
   public void givenValidPerson_whenSearchPerson_thenReturnPerson() {
      List<Person> personsList = new ArrayList<>();
      personsList.add(person1);
      personsList.add(person2);

      personFounded = DataLoader.searchPerson("Jacob", "Boyd", personsList);

      assertThat(personFounded).isNotNull();
      assertThat(personsList).contains(personFounded);
   }

   @Test
   @Tag("searchPerson")
   @DisplayName("SearchPerson - Unknow person")
   public void givenUnknowPerson_whenSearchPerson_thenReturnIllegalArgumentException()
               throws IOException {
      List<Person> personsList = new ArrayList<>();
      personsList.add(person1);
      personsList.add(person2);

      assertThatIllegalArgumentException().isThrownBy(() -> {
         DataLoader.searchPerson("Unknow", "Person", personsList);
      });
   }

   @Test
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Null json file ")
   public void givenNullFilepath_whenReadJsonFile_thenReturnNullPointerException()
               throws IOException {

      assertThatNullPointerException().isThrownBy(() -> {
         DataLoader.readJsonFile(null);
      });
   }

   @Test
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Person")
   public void givenCorrectFilepath_whenReadJsonFileWithPersons_thenReturnVariableLoaded()
               throws IOException {

      entitiesInfoStorage = DataLoader.readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getPersonsList()).isNotNull();
   }

   @Test
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - FireStations")
   public void givenCorrectFilepath_whenReadJsonFileWithFireStations_thenReturnVariableLoaded()
               throws IOException {

      entitiesInfoStorage = DataLoader.readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getFirestations()).isNotNull();
   }

   @Test
   @Tag("readJsonFile")
   @DisplayName("ReadJsonFile - Households")
   public void givenCorrectFilepath_whenReadJsonFileWithHouseholds_thenReturnVariableLoaded()
               throws IOException {

      entitiesInfoStorage = DataLoader.readJsonFile(jsonFile);

      assertThat(entitiesInfoStorage.getHouseholds()).isNotNull();
   }

}
