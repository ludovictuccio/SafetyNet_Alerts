package com.safetynet.alerts.service;

/**
 * Data loader interface.
 *
 * @author Ludovic Tuccio
 *
 */
public interface IDataLoader {

   /**
    * Json file loader method.
    */
   void startAppWithJsonFile();

   /**
    * Error display method when json file failed to load.
    */
   void EventListenerExecuteFailed();

}
