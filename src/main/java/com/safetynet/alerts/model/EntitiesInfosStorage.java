package com.safetynet.alerts.model;

import java.util.List;
import java.util.Map;

/**
 * EntitiesInfosStorage class. Used to retrieve models classes informations.
 *
 * @author Ludovic Tuccio
 */
public class EntitiesInfosStorage {

   /**
    * The persons list.
    */
   private List<Person> personsList;
   /**
    * The firestations mapping (adresses and firestation number).
    */
   private Map<String, FireStation> firestations;
   /**
    * The households composition mapping (adresses and persons list).
    */
   private Map<String, List<Person>> personsPerHousehold;

   /**
    * @param persons
    * @param stations
    * @param households
    */
   public EntitiesInfosStorage(final List<Person> persons,
               final Map<String, FireStation> stations,
               final Map<String, List<Person>> households) {
      this.personsList = persons;
      this.firestations = stations;
      this.personsPerHousehold = households;
   }

   /**
    * @return the personsList
    */
   public List<Person> getPersonsList() {
      return personsList;
   }

   /**
    * @param persons the personsList to set
    */
   public void setPersonsList(final List<Person> persons) {
      this.personsList = persons;
   }

   /**
    * @return the firestations
    */
   public Map<String, FireStation> getFirestations() {
      return firestations;
   }

   /**
    * @param stations the firestations to set
    */
   public void setFirestations(final Map<String, FireStation> stations) {
      this.firestations = stations;
   }

   /**
    * @return the personsPerHousehold
    */
   public Map<String, List<Person>> getHouseholds() {
      return personsPerHousehold;
   }

   /**
    * @param households the personsPerHousehold to set
    */
   public void setPersonsPerHousehold(
               final Map<String, List<Person>> households) {
      this.personsPerHousehold = households;
   }

   /**
    * A toString class method().
    */
   @Override
   public String toString() {
      return "Infos [Persons list="
                  + personsList
                  + ", firestations="
                  + firestations
                  + ", persons per household="
                  + personsPerHousehold
                  + "]";
   }

}
