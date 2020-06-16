package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.InfosRetrieval;
import com.safetynet.alerts.model.Person;

/**
 * Data loader interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IDataLoader {

   /**
    * readfile() method.
    * 
    * @param jsonFile
    */
   InfosRetrieval readFile(String jsonFile);

   /**
    * searchPerson() method.
    * 
    * @param firstName
    * @param lastName
    * @param persons
    */
   Person searchPerson(String firstName, String lastName, List<Person> persons);

}
