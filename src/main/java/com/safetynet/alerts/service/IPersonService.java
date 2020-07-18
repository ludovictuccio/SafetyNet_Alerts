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
    * @return a Person object
    */
   Person createPerson(Map<String, String> personToCreate);

   /**
    * @param personToUpdate
    * @return boolean
    */
   boolean updatePerson(Person personToUpdate);

   /**
    * @param personToDelete
    * @return boolean
    */
   boolean deletePerson(Person personToDelete);

   /**
    * @param firstName
    * @param lastName
    * @return a List of PersonInfoDTO
    */
   List<PersonInfoDTO> personInfo(String firstName, String lastName);

   /**
    * @param address
    * @return a List of ChildAlertDTO
    */
   List<ChildAlertDTO> childAlert(String address);

   /**
    * @param city
    * @return a List of emails addresses
    */
   List<String> communityEmail(String city);
}
