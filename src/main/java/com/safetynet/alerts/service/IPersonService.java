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
    * @return
    */
   Person createPerson(Map<String, String> personToCreate);

   /**
    * @param personToUpdate
    * @return
    */
   boolean updatePerson(Person personToUpdate);

   /**
    * @param personToDelete
    * @return
    */
   boolean deletePerson(Person personToDelete);

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   List<PersonInfoDTO> personInfo(String firstName, String lastName);

   /**
    * @param address
    * @return
    */
   List<ChildAlertDTO> childAlert(String address);

   /**
    * @param city
    * @return
    */
   List<String> communityEmail(String city);
}
