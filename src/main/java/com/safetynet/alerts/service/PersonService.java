package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Household;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;

/**
 * Person service class.
 *
 * @author Ludovic Tuccio
 */
@Service
public class PersonService implements IPersonService {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(PersonService.class);
   /**
    * EntitiesInfosStorage variable used to retrieve persons informations from
    * model classes.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * This method service is used to return the persons informations for the
    * same last name entered.
    * 
    * @param firstName
    * @param lastName
    * @param personsList
    * @return personsInfosList, Persons List
    */
   public List<Person> personInfo(final String firstName,
               final String lastName) {
      LOGGER.debug("PersonInfo request - initialization");
      List<Person> personsInfosList = new ArrayList<>();
      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      for (Person person : personsList) {
         if (person.getLastName().equals(lastName)) {
            Person personsInfos = new Person(person.getFirstName(),
                        person.getLastName(), person.getAddress(),
                        person.getCity(), person.getZip(), person.getPhone(),
                        person.getEmail(), person.getMedicalRecord());
            personsInfosList.add(personsInfos);
         }
      }
      LOGGER.debug("PersonInfo request successfuly");
      return personsInfosList;
   }

   /**
    * This method service is used to create a list of persons for a city
    * entered.
    *
    * @param city
    * @param personsList
    * @return personsEmail, emails List<String>
    */
   public List<String> communityEmail(final String city) {
      LOGGER.debug("CommunityEmail request initialization");
      List<String> personsEmail = new ArrayList<>();
      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      for (Person person : personsList) {
         if (person.getCity().equals(city)) {
            personsEmail.add(person.getEmail());
         }
      }
      LOGGER.debug("CommunityEmail request successfuly");
      return personsEmail;
   }

   /**
    * This method service is used to return the households composition, if the
    * adress entered contains children.
    *
    * @param address
    * @return childAlert, a Household List
    */
   public List<Household> childAlert(final String address) {
      LOGGER.debug("ChildAlert request initialization");
      List<Household> childAlert = new ArrayList<>();
      Map<String, List<Person>> households = entitiesInfosStorage
                  .getHouseholds();
      boolean isListWithChildren = false;

      for (Entry<String, List<Person>> entry : households.entrySet()) {
         String householdAddress = entry.getKey();
         if (!address.equals(householdAddress)) {
            continue;
         }
         List<Person> householdMembersList = entry.getValue();
         for (Person person : householdMembersList) {

            // If child
            if (AgeCalculator.isChild(person)) {
               int childAge = AgeCalculator.ageCalculation(
                           person.getMedicalRecord().getBirthdate());
               Household child = new Household(childAge,
                           person.getFirstName(), person.getLastName());
               childAlert.add(child);
               isListWithChildren = true;

               // If adult
            } else if (!AgeCalculator.isChild(person)) {
               int adultAge = AgeCalculator.ageCalculation(
                           person.getMedicalRecord().getBirthdate());
               Household adultHouseholdMember = new Household(adultAge,
                           person.getFirstName(), person.getLastName());
               childAlert.add(adultHouseholdMember);
            }
         }
      }
      // Return empty list if no contains child
      if (isListWithChildren) {
         return childAlert;
      } else {
         childAlert.clear();
         return childAlert;
      }
   }

   /**
    * @param person
    * @return
    */
   public Person createPerson(final Person person) {

      return person;

   }

   /**
    * @param person
    * @return
    */
   public List<Person> updatePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @param person
    * @return
    */
   public List<Person> deletePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * Method used to retrieve the list of all persons.
    *
    * @return all persons list, entitiesInfosStorage.getPersonsList()
    */
   public List<Person> getAllPersons() {
      return entitiesInfosStorage.getPersonsList();
   }

}
