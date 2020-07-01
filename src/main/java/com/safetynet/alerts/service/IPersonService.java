package com.safetynet.alerts.service;

import java.util.List;

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
    * @param age
    */
   boolean isChildren(int age);

   /**
    * @param adress
    */
   List<Person> childAlert(String adress);

   /**
    * @param personMedicalRecord
    */
   int getPersonsAge(Person personMedicalRecord);

   /**
    * @param city
    * @param personsList
    */
   List<String> communityEmail(String city, List<Person> personsList);

}
