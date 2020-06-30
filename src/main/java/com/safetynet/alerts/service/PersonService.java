package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Households;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

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
    * Used to retrieve persons informations.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   /**
    * @param city
    * @param personsList
    * @return personsEmail
    */
   public List<String> communityEmail(final String city,
               final List<Person> personsList) {

      LOGGER.debug("CommunityEmail request initialization");
      List<String> personsEmail = new ArrayList<>();

      for (Person person : personsList) {
         if (person.getCity().equals(city)) {
            personsEmail.add(person.getEmail());
         }
      }
      LOGGER.info("CommunityEmail request successfuly");
      return personsEmail;
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

//   /**
//    * @return all persons, entitiesInfosStorage.getPersonsList()
//    */
//   public List<Person> getAllPersons() {
//
//      return entitiesInfosStorage.getPersonsList();
//   }

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   public List<Person> personInfo(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @param adress
    * @return personsList
    */
   public List<Person> childAlert(final String adress) {

      List<Person> personsList = new ArrayList<>();
      Households houshold = new Households(null);

      return personsList;
   }

   /**
    * @param age
    */
   public boolean isChildren(final int age) {

      MedicalRecord medicalRecord = new MedicalRecord(null, null, null);
      return false;
   }

   /**
    * @param personMedicalRecord
    * @return
    */
   public int getPersonsAge(final Person personMedicalRecord) {
      return 0;

   }

}
