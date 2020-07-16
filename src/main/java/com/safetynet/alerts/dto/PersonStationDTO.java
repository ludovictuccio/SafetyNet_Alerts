package com.safetynet.alerts.dto;

/**
 * PersonStationDTO class.
 *
 * @author Ludovic Tuccio
 */
public class PersonStationDTO {

   /**
    * The person's first name.
    */
   private String firstName;
   /**
    * The person's last name.
    */
   private String lastName;
   /**
    * The person's adress.
    */
   private String address;
   /**
    * The person's phone number.
    */
   private String phoneNumber;

   /**
    * @param pfirstName
    * @param plastName
    * @param paddress
    * @param pphoneNumber
    */
   public PersonStationDTO(final String pfirstName, final String plastName,
               final String paddress, final String pphoneNumber) {
      this.firstName = pfirstName;
      this.lastName = plastName;
      this.address = paddress;
      this.phoneNumber = pphoneNumber;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   @Override
   public String toString() {
      return "PersonStationDTO [firstName="
                  + firstName
                  + ", lastName="
                  + lastName
                  + ", address="
                  + address
                  + ", phoneNumber="
                  + phoneNumber
                  + "]";
   }

}
