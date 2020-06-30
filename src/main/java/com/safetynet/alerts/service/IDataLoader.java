package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.List;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Person;

/**
 * Data loader interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IDataLoader {

   /**
    * Method used to read the json file and store data to object.
    *
    * @throws IOException
    */
   EntitiesInfosStorage readJsonFile(String file) throws IOException;

   /**
    * Method used to search a person from the json data file.
    */
   Person searchPerson(String firstName, String lastName, List<Person> persons);

}
