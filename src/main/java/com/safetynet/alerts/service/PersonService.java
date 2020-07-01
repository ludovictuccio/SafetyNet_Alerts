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
    * Used to retrieve persons informations.
    */
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;

   private AgeCalculator ageCalculator = new AgeCalculator();;

   /**
    * @param firstName
    * @param lastName
    * @param personsList
    * @return personsInfosList, Persons List
    */
   public List<Person> personInfo(final String firstName, final String lastName,
               final List<Person> personsList) {
      LOGGER.debug("PersonInfo request initialization");
      List<Person> personsInfosList = new ArrayList<>();

      for (Person person : personsList) {
         if (person.getLastName().equals(lastName)) {

            int personsAge = ageCalculator.ageCalculation(
                        person.getMedicalRecord().getBirthdate());

            Person personsInfos = new Person(person.getFirstName(),
                        person.getLastName(), personsAge, person.getAdress(),
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
      LOGGER.debug("CommunityEmail request successfuly");
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

   /**
    * @return all persons, entitiesInfosStorage.getPersonsList()
    */
   public List<Person> getAllPersons() {

      return entitiesInfosStorage.getPersonsList();
   }

   /**
    * @param address
    * @return personsList
    */
   public List<Person> childAlert(final String address) {

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
