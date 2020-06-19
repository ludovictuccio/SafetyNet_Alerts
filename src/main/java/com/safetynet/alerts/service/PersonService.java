package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Households;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * Person service class.
 *
 * @author Ludovic Tuccio
 *
 */
@Service
public class PersonService implements IPersonService {

   /**
    * Used to retrieve persons informations.
    */
   private EntitiesInfosStorage infosRetrieval;
   /**
    * Logger
    */
   private static final Logger LOGGER = LogManager
               .getLogger(PersonService.class);

   /**
    * Public class constructor.
    */
   public PersonService() {

   }

   /**
    * @param person
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.POST)
   public Person createPerson(final Person person) {

      return person;

   }

   /**
    * @param person
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.PUT)
   public List<Person> updatePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @param person
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.DELETE)
   public List<Person> deletePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @return
    */
   public List<Person> getAllPersons() {
      return null;

   }

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
      Households houshold = new Households(personsList);

      return personsList;
   }

   /**
    * @param age
    */
   public boolean isChildren(final int age) {

      MedicalRecord medicalRecord = new MedicalRecord();
      return false;
   }

   /**
    * @param personMedicalRecord
    * @return
    */
   public int getPersonsAge(final Person personMedicalRecord) {
      return 0;

   }

   /**
    * @param city
    * @return
    */
   public List<String> communityEmail(final String city) {
      return null;

   }

}
