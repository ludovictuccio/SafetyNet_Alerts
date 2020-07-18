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
    * @param stationNumber
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

   /**
    * Equals method class.
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj instanceof AddressDTO) {
         AddressDTO o = (AddressDTO) obj;
         return address == o.address && city == o.city && zip == o.zip;
      }
      return false;
   }

   /**
    * toString method class.
    */
   @Override
   public String toString() {
      return "AddressDTO [address="
                  + address
                  + ", city="
                  + city
                  + ", zip="
                  + zip
                  + "]";
   }

}
