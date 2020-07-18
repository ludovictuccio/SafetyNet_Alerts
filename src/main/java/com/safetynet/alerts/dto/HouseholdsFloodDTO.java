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
    * @param pAddress the address to set
    */
   public void setAddress(final AddressDTO pAddress) {
      this.address = pAddress;
   }

   /**
    * @return the personsList
    */
   public List<PersonFloodDTO> getPersonsList() {
      return personsList;
   }

   /**
    * @param persons the personsList to set
    */
   public void setPersonsList(final List<PersonFloodDTO> persons) {
      this.personsList = persons;
   }

   /**
    * toString method class.
    */
   @Override
   public String toString() {
      return "HouseholdsFloodDTO [address="
                  + address
                  + ", personsList="
                  + personsList
                  + "]";
   }

}
