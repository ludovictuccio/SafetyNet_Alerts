package com.safetynet.alerts.dto;

import java.util.List;

/**
 * HouseholdsFloodDTO class.
 *
 * @author Ludovic Tuccio
 */
public class HouseholdsFloodDTO {

   /**
    * Household address.
    */
   private AddressDTO address;
   /**
    * Persons under station responsibility list.
    */
   private List<PersonFloodDTO> personsList;

   /**
    * @param pAddress
    * @param persons
    */
   public HouseholdsFloodDTO(final AddressDTO pAddress,
               final List<PersonFloodDTO> persons) {
      this.address = pAddress;
      this.personsList = persons;
   }

   /**
    * @return the address
    */
   public AddressDTO getAddress() {
      return address;
   }

   /**
    * @return the personsList
    */
   public List<PersonFloodDTO> getPersonsList() {
      return personsList;
   }

}
