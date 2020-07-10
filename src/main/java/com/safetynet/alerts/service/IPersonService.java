package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.Person;

/**
 * Person service interface.
 *
 * @author Ludovic Tuccio
 */
public interface IPersonService {

   /**
    * @param personToCreate
    */
   Person createPerson(Map<String, String> personToCreate);

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
    */
   List<PersonInfoDTO> personInfo(String firstName, String lastName);

   /**
    * @param adress
    */
   List<ChildAlertDTO> childAlert(String address);

   /**
    * @param city
    */
   List<String> communityEmail(String city);
}
