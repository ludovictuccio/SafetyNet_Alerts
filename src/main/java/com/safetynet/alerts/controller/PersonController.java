package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

/**
 * Person controller class.
 *
 * @author Ludovic Tuccio
 *
 */
public class PersonController {

   /**
    * Used to retrieve persons informations.
    */
   private EntitiesInfosStorage entitiesInfosStorage;
   /**
    * Used to retrieve persons service informations.
    */
   private IPersonService personService;

   /**
    * Public class constructor.
    */
   public PersonController() {

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
    * @param firstName
    * @param lastName
    * @return
    */
   public List<Person> deletePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @return
    */
   public List<Person> childAlert() {
      return null;

   }

   /**
    * @return
    */
   public List<Person> personInfos() {
      return null;

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
