package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.constants.Constants;
import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

/**
 * Person controller class.
 *
 * @author Ludovic Tuccio
 */
@RestController
public class PersonController {
   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(PersonController.class);
   /**
    * PersonService interface variable used to acces to service & model classes.
    */
   @Autowired
   private IPersonService personService;

   /**
    * This method controller is used to return the persons informations for the
    * same last name entered.
    *
    * @param firstName
    * @param lastName
    * @return personInfos, PersonInfoDTO list
    */
   @GetMapping("/personInfo")
   public List<PersonInfoDTO> personInfo(@RequestParam final String firstName,
               @NotNull @RequestParam final String lastName,
               final HttpServletResponse response) {
      LOGGER.debug("GET request received for personInfos: {}", lastName);

      List<PersonInfoDTO> personInfos = personService.personInfo(firstName,
                  lastName);

      if (!personInfos.isEmpty()) {
         LOGGER.info("SUCCESS - personInfos GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("FAILED - No person's founded for last name: {}",
                     lastName);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return personInfos;
   }

   /**
    * This method controller is used to return the email addresses of persons in
    * the city entered.
    *
    * @param city
    * @return communityEmail, email addresses list
    */
   @GetMapping("/communityEmail")
   public List<String> getCommunityEmail(
               @NotNull @RequestParam(value = "city") final String city,
               final HttpServletResponse response) {
      LOGGER.debug("GET request received for getCommunityEmail: {}", city);

      List<String> communityEmail = personService.communityEmail(city);

      if (!communityEmail.isEmpty()) {
         LOGGER.info("SUCCESS - CommunityEmail GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("FAILED - No person's email adresses founded for: {}",
                     city);
         String notFounded = "No person's email adresses founded for: "
                     + city;
         communityEmail.add(notFounded);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return communityEmail;
   }

   /**
    * This method controller is used to return the households composition, if
    * the adress entered contains children.
    *
    * @param address
    * @return childAlert, ChildAlertDTO list
    */
   @GetMapping("/childAlert")
   public List<ChildAlertDTO> getChildAlert(
               @NotNull @RequestParam(value = "address") final String address,
               final HttpServletResponse response) {
      LOGGER.debug("GET request received for getChildAlert: {}", address);

      List<ChildAlertDTO> childAlert = personService.childAlert(address);

      if (!childAlert.isEmpty()) {
         LOGGER.info("SUCCESS - ChildAlert GET request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("FAILED - No household with child founded for: {}",
                     address);
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
      return childAlert;
   }

   /**
    * This method controller is used to create a new Person with the service
    * method.
    *
    * @param personToCreate
    */
   @PostMapping("/person")
   public void createPerson(
               @NotNull @RequestBody final Map<String, String> personToCreate,
               final HttpServletResponse response) {
      Person personsCreated = personService.createPerson(personToCreate);

      if (personsCreated != null) {
         LOGGER.info("SUCCESS - CreatePerson POST request");
         response.setStatus(Constants.STATUS_CREATED_201);
      } else {
         response.setStatus(Constants.ERROR_CONFLICT_409);
      }
   }

   /**
    * This void method controller is used to update a person.
    *
    * @param personToUpdate
    */
   @PutMapping("/person")
   public void updatePerson(@NotNull @RequestBody final Person personToUpdate,
               final HttpServletResponse response) {

      boolean isUpdated = personService.updatePerson(personToUpdate);

      if (isUpdated) {
         LOGGER.info("SUCCESS - UpdatePerson PUT request");
         response.setStatus(Constants.STATUS_OK_200);
      } else {
         LOGGER.error("No person founded for: {} {}",
                     personToUpdate.getFirstName(),
                     personToUpdate.getLastName());
         response.setStatus(Constants.ERROR_NOT_FOUND_404);
      }
   }

   /**
    * @param firstName
    * @param lastName
    * @return
    */
   @DeleteMapping("/person")
   public List<Person> deletePerson(
               @NotNull @RequestBody final String firstName,
               @NotNull @RequestBody final String lastName,
               final HttpServletResponse response) {
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
               final List<Person> personsUnderFirestationResponsibility,
               final HttpServletResponse response) {
      return null;

   }

}
