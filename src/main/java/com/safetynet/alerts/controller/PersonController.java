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

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

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
   @Autowired
   private EntitiesInfosStorage entitiesInfosStorage;
   /**
    * Used to retrieve persons service informations.
    */
   @Autowired
   private PersonService personService;

   private List<Person> listPerson = null;

   /**
    * This method service is used to return the email addresses of people in the
    * city entered.
    *
    * @param city
    * @return communityEmail
    */
   @GetMapping(value = "/communityEmail")
   public List<String> getCommunityEmail(@RequestParam final String city,
               final HttpServletResponse response) {
      LOGGER.debug("GET request received for getCommunityEmail: {}", city);

      listPerson = entitiesInfosStorage.getPersonsList();

      List<String> communityEmail = personService.communityEmail(city,
                  listPerson);

      if (!communityEmail.isEmpty()) {
         LOGGER.debug("SUCCESS getCommunityEmail HTTP GET request");
         response.setStatus(200);
      } else {
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
    * @return
    */
   public List<Person> personInfos() {
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
