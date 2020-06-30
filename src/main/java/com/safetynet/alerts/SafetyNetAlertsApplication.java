package com.safetynet.alerts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.service.IDataLoader;

/**
 * SafetyNetAlertsApplication class with method main.
 *
 * @author Ludovic Tuccio
 *
 */
@SpringBootApplication
public class SafetyNetAlertsApplication {

   /**
    * Logger class.
    */
   private static Logger LOGGER = LogManager
               .getLogger(SafetyNetAlertsApplication.class);
   /**
    * Json file path.
    */
   private String jsonFile = "./src/main/resources/data.json";
   /**
    * IDataLoader variable.
    */
   @Autowired
   private IDataLoader dataLoader;

   /**
    * App method main.
    *
    * @param args
    */
   public static void main(final String[] args) {
      LOGGER.debug("SafetyNet Alerts Application initialization");
      SpringApplication.run(SafetyNetAlertsApplication.class, args);
   }

   /**
    * Method used to read json file.
    *
    * @return entitiesInfosStorage
    * @throws IOException
    */
   @Bean
   public EntitiesInfosStorage jsonFileLoader() throws IOException {
      LOGGER.debug("Reading file");
      return dataLoader.readJsonFile(jsonFile);
   }
}
