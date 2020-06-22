package com.safetynet.alerts.util;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration.
 * 
 * @author Ludovic Tuccio
 *
 */
@Configuration
public class AppConfiguration {

   /**
    * Method used to configure httptrace endpoint with actuator.
    * 
    * @return new InMemoryHttpTraceRepository
    */
   @Bean
   public HttpTraceRepository htttpTraceRepository() {
      return new InMemoryHttpTraceRepository();
   }

}
