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
    * @param firstName the firstName to set
    */
   public void setFirstName(final String pfirstName) {
      this.firstName = pfirstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(final String plastName) {
      this.lastName = plastName;
   }

   /**
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @param address the address to set
    */
   public void setAddress(final String paddress) {
      this.address = paddress;
   }

   /**
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   /**
    * @param phoneNumber the phoneNumber to set
    */
   public void setPhoneNumber(final String pphoneNumber) {
      this.phoneNumber = pphoneNumber;
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
