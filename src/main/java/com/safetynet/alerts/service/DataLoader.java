package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.Person;

/**
 * Data loader class.
 *
 * @author Ludovic Tuccio
 *
 */
@Service
public class DataLoader implements IDataLoader {

   /**
    * Public class constructor.
    */
   public DataLoader() {

   }

   /**
    * @param jsonFile
    * @return
    */
   public EntitiesInfosStorage readFile(final String jsonFile) {

      return null;
   }

   /**
    * @param firstName
    * @param lastName
    * @param personsList
    * @return
    */
   public Person searchPerson(final String firstName, final String lastName,
               final List<Person> personsList) {

      return null;
   }

}
