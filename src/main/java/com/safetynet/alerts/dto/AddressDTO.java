package com.safetynet.alerts.dto;

/**
 * AddressDTO class.
 *
 * @author Ludovic Tuccio
 */
public class AddressDTO {

   /**
    * The household address.
    */
   private String address;
   /**
    * The household city.
    */
   private String city;
   /**
    * The household zip.
    */
   private String zip;

   /**
    * @param householdAddress
    * @param householdCity
    * @param householdZip
    */
   public AddressDTO(final String householdAddress, final String householdCity,
               final String householdZip) {
      this.address = householdAddress;
      this.city = householdCity;
      this.zip = householdZip;

   }

   /**
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * @return the zip
    */
   public String getZip() {
      return zip;
   }

}
