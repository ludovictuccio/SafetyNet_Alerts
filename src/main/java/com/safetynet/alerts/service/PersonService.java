package com.safetynet.alerts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.InfosRetrieval;
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
   private InfosRetrieval infosRetrieval;

   /**
    * Public class constructor.
    */
   public PersonService() {

   }

   /**
    * @param person
    * @return
    */
   public List<Person> createPerson(final Person person) {
      return null;

   }

   /**
    * @param person
    * @return
    */
   public List<Person> updatePerson(final Person person) {
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
   public List<Person> getPersonByName(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @param birthdate
    * @return boolean
    */
   public boolean isChildren(final LocalDate birthdate) {
      return false;

   }

   /**
    * @return
    */
   public List<Person> getChildrenList() {
      return null;

   }

   /**
    * @param age
    * @return
    */
   public int getPersonsAge(final LocalDate age) {
      return 0;

   }

   /**
    * @param email
    * @param communityPersons
    * @return
    */
   public List<String> getCommunityEmail(final String email,
               final List<Person> communityPersons) {
      return null;

   }

   /**
    * @param phoneNumber
    * @param responsibleFireStation
    * @param personsUnderFirestationResponsibility
    * @return
    */
   public List<String> getPhoneAlert(final String phoneNumber,
               final Map<Integer, FireStation> responsibleFireStation,
               final List<Person> personsUnderFirestationResponsibility) {
      return null;

   }

}
