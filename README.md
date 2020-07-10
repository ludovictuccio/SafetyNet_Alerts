# SafetyNet_Alerts

<p>The java application "SafetyNet Alerts" is run with Maven and Spring Boot.</p>

## Infos
**Author:** Ludovic Tuccio

**Java version:** 1.8

**Actuators in service:** healt, info, httptrace & metrics

The **JSON data** is available in *"src/main/resources/data"*, and it's **loaded from application.properties** available in *"src/main/resources"*.

The **logging properties file** is available in *"src/main/resources"*.

The app has **unit tests** and **integration tests** written.

## Installing

**1.Install Java:**

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

**2.Install Maven:**

https://maven.apache.org/install.html

## Testing

To run the tests from maven: **mvn test**

To run coverage jacoco report:**mvn jacoco:report**

To run checkstyle and findbugs reports:**mvn site**

To run maven surefire plugin: **mvn surefire-report:report**

To test endpoints:
<ol>
	<li>Install Postman https://www.postman.com/</li> 
		OR
<li>Use localhost http://localhost:8080/{}</li> 
</ol>

## Person endpoint

**POST** - http://localhost:8080/person >>> add a new person at the all persons list.

<pre><code>Response 201 Created or 409 Conflict (if already existing person)
	[
	    {
	        "firstName": "John",
	        "lastName": "Boyd",
	        "address": "1509 Culver St",
	        "city": "Culver",
	        "zip": "97451",
	        "phone": "841-874-6512",
	        "email": "jaboyd@email.com"
	    },
	    {
	        "firstName": "New",
	        "lastName": "Person",
	        "address": "951 LoneTree Rd",
	        "city": "Culver",
	        "zip": "97451",
	        "phone": "841-874-7458",
	        "email": "gramps@email.com"
	    }
	]
</pre></code>
**PUT** -  http://localhost:8080/person/{firstName}/{lastName} >>> updates a person's information, except first and last name.

<pre><code> {
	        "firstName": "John",
	        "lastName": "Boyd",
	        "address": "1509 Culver St",
	        "city": "Culver",
	        "zip": "97451",
	        "phone": "012-345-678",
	        "email": "NEW-MAIL@email.com"
	    }</pre></code>

**DELETE** -  http://localhost:8080/person/{firstName}/{lastName} >>> delete a person by entering his first and last name.


## Firestation endpoint

**POST** - http://localhost:8080/firestation >>> add a new address for a firestation number.

 <pre><code> 
Response 201 Created or 409 Conflict (if already existing address mapping)
  {
        "address": "New address",
        "station": "2"
    }</pre></code>

**PUT** - http://localhost:8080/firestation{address} >>> update firestation number for an entered address.

<pre><code>{
        "address": "New address",
        "station": "4"
    }</pre></code>

**DELETE** - http://localhost:8080/firestation{address} >>> delete an address with his firestation number association.

## Medicalrecord endpoint

**POST** - http://localhost:8080/medicalRecord/{firstName}/{lastName} >>> add a new medicalrecord for a person who has not assigned one.

**PUT** - http://localhost:8080/medicalRecord/{firstName}/{lastName} >>> update a person's medicalrecord. 

Response 201 Created or 404 Not found
<pre><code>	{
        "firstName": "John",
        "lastName": "Boyd",
        "birthDate": "03/06/1984",
        "medications":["aznol:350mg", "hydrapermazol:100mg"],
        "allergies":["NEW ALLERGIE"] 
    }</pre></code>

**DELETE** - http://localhost:8080/medicalRecord/{firstName}/{lastName} >>> delete a person's medicalrecord.

## Firestation number endpoint 

**GET** - http://localhost:8080/firestation?stationNumber=<station_number> >>> returns a persons list covered by a firestation, with name, address and phone number as informations, and a total adults and children counting.

## ChildAlert endpoint 

**GET** - http://localhost:8080/childAlert?address=<address> >>> returns the household composition for the address entered, only if the household contains children. The informations returned are: firstName, lastName, age.
  
## PhoneAlert endpoint 
  
**GET** - http://localhost:8080/phoneAlert?firestation=<firestation_number> >>> returns the list of all the phone numbers of the persons served by the firestation entered. 

## Fire endpoint 

**GET** - http://localhost:8080/fire?address=<address> >>> returns the list of persons who live at the address, and the firestation number that serving it. The list must include: name, phone number, age and medical record.

## Flood endpoint 

**GET** - http://localhost:8080/flood/stations?stations=<a list of station_numbers> >>> returns the list of all the households served by the firestation. It must group persons by address, and include the following information: name, phone number, age and medical record.

## PersonInfo endpoint 

**GET** - http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName> >>> returns the name, address, age, email and medical record of all persons with the same last name.

## CommunityEmail endpoint 

**GET** - http://localhost:8080/communityEmail?city=<city> >>> returns all the person's email addresses who live at the city entered.
  
