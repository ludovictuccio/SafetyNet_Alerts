package com.safetynet.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.model.InfosRetrieval;
import com.safetynet.alerts.service.DataLoader;

/**
 * SafetyNetAlertsApplication with method main.
 *
 * @author Ludovic Tuccio
 *
 */
@SpringBootApplication
public class SafetyNetAlertsApplication {

   /**
    * DataLoader variable used to read data file.
    */
   private DataLoader readFile;

   /**
    * Data file access root.
    */
   private String file = "./src/main/resources/data.json";

   /**
    * Method main.
    *
    * @param args
    */
   public static void main(final String[] args) {
      SpringApplication.run(SafetyNetAlertsApplication.class, args);
   }

   /**
    * Method used to read data file.
    * 
    * @return file
    */
   public InfosRetrieval readData() {
      return readFile.readFile(file);
   }

}
