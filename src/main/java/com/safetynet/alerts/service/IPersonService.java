package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

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
   Person createPerson(Person person);

   /**
    * @param firstName
    * @param lastName
    */
   List<Person> updatePerson(String firstName, String lastName);

   /**
    * @param firstName
    * @param lastName
    */
   List<Person> deletePerson(String firstName, String lastName);

   /**
    * @param firstName
    * @param lastName
    * @param personsList
    */
   List<Person> personInfo(String firstName, String lastName,
               List<Person> personsList);

   /**
    * @param adress
    * @param personsList
    * @param household
    */
   List<Person> childAlert(String address, List<Person> personsList,
               Map<String, List<Person>> household);

   /**
    * @param city
    * @param personsList
    */
   List<String> communityEmail(String city, List<Person> personsList);
}
