package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

/**
 * Person controller class.
 *
 * @author Ludovic Tuccio
 *
 */
@RestController
public class PersonController {

   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(PersonController.class);
   /**
    * Used to retrieve persons informations.
    */
//   @Autowired
//   private EntitiesInfosStorage entitiesInfosStorage;
   /**
    * Used to retrieve persons service informations.
    */
   @Autowired
   private PersonService personService;

   private List<Person> listPerson = null;

   /**
    * This method service is used to return the persons informations for the
    * same last name entered.
    *
    * @param firstName
    * @param lastName
    * @return personInfos, Persons list
    */
   @GetMapping(value = "/personInfo")
   public List<Person> personInfo(@RequestParam String firstName,
               @NotNull @RequestParam String lastName,
               HttpServletResponse response) {
      LOGGER.debug("GET request received for personInfos: {}", lastName);
      // listPerson = entitiesInfosStorage.getPersonsList();
      List<Person> personInfos = personService.personInfo(firstName, lastName,
                  listPerson);

      if (!personInfos.isEmpty()) {
         LOGGER.debug("SUCCESS - personInfos HTTP GET request sucess");
         response.setStatus(200);
      } else {
         LOGGER.debug("FAILED - No person's founded for last name: {}",
                     lastName);
         response.setStatus(404);
      }
      return personInfos;
   }

   /**
    * This method service is used to return the email addresses of persons in
    * the city entered.
    *
    * @param city
    * @return communityEmail, email addresses list
    */
   @GetMapping(value = "/communityEmail")
   public List<String> getCommunityEmail(
               @NotNull @RequestParam(value = "city") String city,
               HttpServletResponse response) {
      LOGGER.debug("GET request received for getCommunityEmail: {}", city);
      // listPerson = entitiesInfosStorage.getPersonsList();
      List<String> communityEmail = personService.communityEmail(city,
                  listPerson);

      if (!communityEmail.isEmpty()) {
         LOGGER.debug("SUCCESS - CommunityEmail HTTP GET request sucess");
         response.setStatus(200);
      } else {
         LOGGER.debug("FAILED - No person's email adresses founded for: {}",
                     city);
         String notFounded = "No person's email adresses founded for: "
                     + city;
         communityEmail.add(notFounded);
         response.setStatus(404);
      }
      return communityEmail;
   }

   /**
    * @param person
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.POST)
   public List<Person> createPerson(final Person person) {
      return null;

   }

   /**
    * @param person
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.PUT)
   public List<Person> updatePerson(final Person person) {
      return null;

   }

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   @RequestMapping(value = "/person", method = RequestMethod.DELETE)
   public List<Person> deletePerson(final String firstName,
               final String lastName) {
      return null;

   }

   /**
    * @return
    */
   public List<Person> childAlert() {
      return null;

   }

   /**
    * @param phoneNumber
    * @param responsibleFireStation
    * @param personsUnderFirestationResponsibility
    * @return
    */
   public List<String> getPhoneAlert(final String phoneNumber,
               final Map<Integer, FireStation> responsibleFireStation,
               final List<Person> personsUnderFirestationResponsibility) {
      return null;

   }

}
