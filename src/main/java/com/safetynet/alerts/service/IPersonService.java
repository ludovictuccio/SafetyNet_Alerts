package com.safetynet.alerts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

/**
 * Person service interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IPersonService {

   /**
    * @param person
    */
   List<Person> createPerson(Person person);

   /**
    * @param person
    */
   List<Person> updatePerson(Person person);

   /**
    * @param firstName
    * @param lastName
    */
   List<Person> deletePerson(String firstName, String lastName);

   /**
    * @return
    */
   List<Person> getAllPersons();

   /**
    * @param firstName
    * @param lastName
    */
   List<Person> getPersonByName(String firstName, String lastName);

   /**
    * @param birthdate
    */
   boolean isChildren(LocalDate birthdate);

   /**
    * @return
    */
   List<Person> getChildrenList();

   /**
    * @param age
    */
   int getPersonsAge(LocalDate age);

   /**
    * @param email
    * @param communityPersons
    */
   List<String> getCommunityEmail(String email, List<Person> communityPersons);

   /**
    * @param phoneNumber
    * @param responsibleFireStation
    * @param personsUnderFirestationResponsibility
    */
   List<String> getPhoneAlert(String phoneNumber,
               Map<Integer, FireStation> responsibleFireStation,
               List<Person> personsUnderFirestationResponsibility);

}
