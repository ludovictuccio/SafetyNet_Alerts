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

   /**
    * hashCode method class.
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((address == null) ? 0 : address.hashCode());
      result = prime * result + ((city == null) ? 0 : city.hashCode());
      result = prime * result + ((zip == null) ? 0 : zip.hashCode());
      return result;
   }

   /**
    * equals method class.
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AddressDTO other = (AddressDTO) obj;
      if (address == null) {
         if (other.address != null)
            return false;
      } else if (!address.equals(other.address))
         return false;
      if (city == null) {
         if (other.city != null)
            return false;
      } else if (!city.equals(other.city))
         return false;
      if (zip == null) {
         if (other.zip != null)
            return false;
      } else if (!zip.equals(other.zip))
         return false;
      return true;
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
