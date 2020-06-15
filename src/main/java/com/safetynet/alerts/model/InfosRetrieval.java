package com.safetynet.alerts.model;

import java.util.List;
import java.util.Map;

/**
 * @author Ludovic Tuccio
 *
 */
public class InfosRetrieval {

   private List<Person> personsList;
   private Map<Integer, FireStation> firestations;
   private Map<String, List<Person>> personsPerHousehold;

   /**
    * @param personsList
    * @param firestations
    * @param personsPerHousehold
    */
   public InfosRetrieval(List<Person> personsList,
               Map<Integer, FireStation> firestations,
               Map<String, List<Person>> personsPerHousehold) {
      super();
      this.personsList = personsList;
      this.firestations = firestations;
      this.personsPerHousehold = personsPerHousehold;
   }

   /**
    * @return the personsList
    */
   public List<Person> getPersonsList() {
      return personsList;
   }

   /**
    * @param personsList the personsList to set
    */
   public void setPersonsList(List<Person> personsList) {
      this.personsList = personsList;
   }

   /**
    * @return the firestations
    */
   public Map<Integer, FireStation> getFirestations() {
      return firestations;
   }

   /**
    * @param firestations the firestations to set
    */
   public void setFirestations(Map<Integer, FireStation> firestations) {
      this.firestations = firestations;
   }

   /**
    * @return the personsPerHousehold
    */
   public Map<String, List<Person>> getPersonsPerHousehold() {
      return personsPerHousehold;
   }

   /**
    * @param personsPerHousehold the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(
               Map<String, List<Person>> personsPerHousehold) {
      this.personsPerHousehold = personsPerHousehold;
   }

   public String toString() {
      return "Infos retrieval [Persons list="
                  + personsList
                  + ", firestations="
                  + firestations
                  + ", persons per household="
                  + personsPerHousehold
                  + "]";
   }

}
