package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.EntitiesInfosStorage;
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
    * This method service is used to create a new Person,and update households
    * list.
    *
    * @param personToCreate
    * @return Person newPerson
    */
   public Person createPerson(final Map<String, String> personToCreate) {
      // Added first letter uppecase for firstname, lastname & city
      Person newPerson = new Person(
                  personToCreate.get("firstName").toString().substring(0, 1)
                              .toUpperCase()
                              + personToCreate.get("firstName").toString()
                                          .substring(1).toLowerCase(),
                  personToCreate.get("lastName").toString().substring(0, 1)
                              .toUpperCase()
                              + personToCreate.get("lastName").toString()
                                          .substring(1).toLowerCase(),
                  personToCreate.get("address").toString(),
                  personToCreate.get("city").toString().substring(0, 1)
                              .toUpperCase()
                              + personToCreate.get("city").toString()
                                          .substring(1).toLowerCase(),
                  personToCreate.get("zip").toString(),
                  personToCreate.get("phone").toString(),
                  personToCreate.get("email").toString());
      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      // Verification for not create a person already existing in the list
      for (Person existingPerson : personsList) {
         if (existingPerson.getFirstName().equals(newPerson.getFirstName())
                     && existingPerson.getLastName()
                                 .equals(newPerson.getLastName())) {
            LOGGER.error(
                        "FAILED to create the person: {} {}, this person already exists.",
                        newPerson.getFirstName(), newPerson.getLastName());
            return null;
         }
      }
      personsList.add(newPerson);

      // Update households
      Map<String, List<Person>> households = entitiesInfosStorage
                  .getHouseholds();
      List<Person> newPersonHousehold = households.computeIfAbsent(
                  newPerson.getAddress(), temp -> new ArrayList<>());
      newPersonHousehold.add(newPerson);
      return newPerson;
   }

   /**
    * This method service is used to update a person.
    *
    * @param personToUpdate
    * @return isUpdated boolean
    */
   public boolean updatePerson(final Person personToUpdate) {
      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      boolean isUpdated = false;

      for (Person existingPerson : personsList) {
         if (existingPerson.getFirstName().equals(personToUpdate.getFirstName())
                     && existingPerson.getLastName()
                                 .equals(personToUpdate.getLastName())) {
            existingPerson.setAddress(personToUpdate.getAddress());
            existingPerson.setCity(personToUpdate.getCity());
            existingPerson.setZip(personToUpdate.getZip());
            existingPerson.setPhone(personToUpdate.getPhone());
            existingPerson.setEmail(personToUpdate.getEmail());

            entitiesInfosStorage.setPersonsList(personsList);
            isUpdated = true;
         }
      }
      return isUpdated;
   }

   /**
    * This method service is used to delete a person.
    *
    * @param personToDelete
    * @return isDeleted boolean
    */
   public boolean deletePerson(final Person personToDelete) {
      List<Person> personsList = entitiesInfosStorage.getPersonsList();
      boolean isDeleted = false;

      for (Iterator<Person> iter = personsList.iterator(); iter.hasNext();) {
         Person existingPerson = iter.next();

         if (existingPerson.getFirstName()
                     .matches(personToDelete.getFirstName())
                     && existingPerson.getLastName()
                                 .matches(personToDelete.getLastName())) {
            iter.remove();
            isDeleted = true;
         }
      }
      return isDeleted;
   }

   /**
    * This method service is used to return the persons informations for the
    * same last name entered.
    * 
    * @param firstName
    * @param lastName
    * @return personsInfosList, PersonInfoDTO List
    */
   public List<PersonInfoDTO> personInfo(final String firstName,
               final String lastName) {
      LOGGER.debug("PersonInfo request - initialization");
      List<PersonInfoDTO> personsInfosList = new ArrayList<>();
      List<Person> personsList = entitiesInfosStorage.getPersonsList();

      for (Person person : personsList) {
         if (person.getLastName().equals(lastName)) {
            PersonInfoDTO personsInfos = new PersonInfoDTO(
                        person.getFirstName(), person.getLastName(),
                        person.getAddress(), person.getCity(), person.getZip(),
                        person.getEmail(), person.getMedicalRecord());
            personsInfosList.add(personsInfos);
         }
      }
      LOGGER.debug("PersonInfo request successfuly");
      return personsInfosList;
   }

   /**
    * This method service is used to return the households composition, if the
    * adress entered contains children.
    *
    * @param address
    * @return childAlert, a ChildAlertDTO List
    */
   public List<ChildAlertDTO> childAlert(final String address) {
      LOGGER.debug("ChildAlert request initialization");
      List<ChildAlertDTO> childAlert = new ArrayList<>();
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
               ChildAlertDTO child = new ChildAlertDTO(childAge,
                           person.getFirstName(), person.getLastName());
               childAlert.add(child);
               isListWithChildren = true;
               // If adult
            } else if (!AgeCalculator.isChild(person)) {
               int adultAge = AgeCalculator.ageCalculation(
                           person.getMedicalRecord().getBirthdate());
               ChildAlertDTO adultHouseholdMember = new ChildAlertDTO(adultAge,
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
    * This method service is used to create a list of persons for a city
    * entered.
    *
    * @param city
    * @return personsEmail, email addresses List
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

}
