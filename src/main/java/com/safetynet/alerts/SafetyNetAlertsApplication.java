package com.safetynet.alerts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    * Json file path, defined in the configuration file.
    */
   // @Value("${spring.config.location}")
   private static String jsonFile = "./src/main/resources/data/data.json";

   /**
    * App method main.
    *
    * @param args
    */
   public static void main(final String[] args) {
      SpringApplication.run(SafetyNetAlertsApplication.class, args);
   }

   /**
    * Method used to read json file.
    *
    * @return entitiesInfosStorage
    * @throws IOException
    */
   @Bean
   public static EntitiesInfosStorage jsonFileLoader() throws IOException {
      LOGGER.debug("Json File Loader");
      return DataLoader.readJsonFile(jsonFile);
   }

}
