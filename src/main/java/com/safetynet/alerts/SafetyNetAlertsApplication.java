package com.safetynet.alerts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.safetynet.alerts.model.EntitiesInfosStorage;
import com.safetynet.alerts.util.DataLoader;

/**
 * SafetyNetAlertsApplication class with method main.
 *
 * @author Ludovic Tuccio
 */
@SpringBootApplication
public class SafetyNetAlertsApplication {
   /**
    * Logger class.
    */
   private static final Logger LOGGER = LogManager
               .getLogger(SafetyNetAlertsApplication.class);
   /**
    * Data filepath, defined in application.properties file.
    */
   @Value("${info.data}")
   private String dataFile;

   /**
    * App method main.
    *
    * @param args
    */
   public static void main(final String[] args) {
      SpringApplication.run(SafetyNetAlertsApplication.class, args);
   }

   /**
    * Method used to read json data file.
    *
    * @return entitiesInfosStorage
    * @throws IOException
    */
   @Bean
   public EntitiesInfosStorage jsonFileLoader() throws IOException {
      LOGGER.debug("Loading Data File");
      return DataLoader.readJsonFile(dataFile);
   }

}
