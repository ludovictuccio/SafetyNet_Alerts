# SafetyNet_Alerts

<p>The java application "SafetyNet Alerts" is run with Maven and Spring Boot.</p>

## Infos

**Author:** Ludovic Tuccio

- **Java version:** 1.8

- **Actuators in service:** healt, info, httptrace & metrics

- **JSON data:** available in *"src/main/resources/data"*, and it's **loaded from application.properties** in *"src/main/resources"*.

- **Loggers:** the logging properties file is available in *"src/main/resources"*.

- **Tests:** the app has unit tests and integration tests written.

## Installing

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

## Testing

**To run :**

- the tests from maven: **mvn test**

- coverage jacoco report: **mvn jacoco:report**

- checkstyle and findbugs reports: **mvn site**

- maven surefire plugin: **mvn surefire-report:report**

**To test endpoints:**
<ol>
	<li>Install Postman https://www.postman.com/</li> 
		OR
<li>Use localhost http://localhost:8080/{}</li> 
</ol>

## Person endpoint

**POST** - http://localhost:8080/person >>> add a new person at the all persons list.
<pre><code>
	[
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
**PUT** -  http://localhost:8080/person >>> updates a person's information, except first and last name.
<pre><code>
		{
	        "firstName": "John",
	        "lastName": "Boyd",
	        "address": "1509 Culver St",
	        "city": "Culver",
	        "zip": "97451",
	        "phone": "012-345-678",
	        "email": "NEW-MAIL@email.com"
		}
		</pre></code>

**DELETE** -  http://localhost:8080/person >>> delete a person by entering his first and last name.
<pre><code>
		{
	        "firstName": "John",
	        "lastName": "Boyd"
		}
		</pre></code>

## Firestation endpoint

**POST** - http://localhost:8080/firestation >>> add a new address for an existing firestation number. If address already exists for a mapping, impossible add.

<pre><code> 
	{
        "station": "1",
        "address": "New address"
	}
	</pre></code>

**PUT** - http://localhost:8080/firestation >>> update firestation number for an entered address.

<pre><code>
	{
        "station": "1",
        "address": "1509 Culver St"
	}
</pre></code>

**DELETE** - http://localhost:8080/firestation{address} >>> delete an address with his firestation number association.

## Medicalrecord endpoint

**POST** - http://localhost:8080/medicalRecord >>> add a new medicalrecord for a person created without medicalrecord.

<pre><code>
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthDate": "03/06/1984",
        "medications":["aznol:350mg", "hydrapermazol:100mg"],
        "allergies":["nillacilan"]
    }</pre></code>

**PUT** - http://localhost:8080/medicalRecord >>> update a person's medicalrecord. 

<pre><code>
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthDate": "03/06/1984",
        "medications":["aznol:350mg", "hydrapermazol:100mg"],
        "allergies":["NEW ALLERGIE"] 
    }</pre></code>

**DELETE** - http://localhost:8080/medicalRecord{firstName}/{lastName} >>> delete a person's medicalrecord.


## Firestation number endpoint 

**GET** - http://localhost:8080/firestation?stationNumber={station_number} >>> returns a persons list covered by a firestation, with name, address and phone number as informations, and a total adults and children counting.

<pre><code>
{
    "personsStationList": [
        {
            "firstName": "Tony",
            "lastName": "Cooper",
            "address": "112 Steppes Pl",
            "phoneNumber": "841-874-6874"
        },
        {
            "firstName": "Lily",
            "lastName": "Cooper",
            "address": "489 Manchester St",
            "phoneNumber": "841-874-9845"
        },
        {
            "firstName": "Ron",
            "lastName": "Peters",
            "address": "112 Steppes Pl",
            "phoneNumber": "841-874-8888"
        },
        {
            "firstName": "Allison",
            "lastName": "Boyd",
            "address": "112 Steppes Pl",
            "phoneNumber": "841-874-9888"
        }
    ],
    "totalAdultsNumber": 4,
    "totalChildrenNumber": 0
}</pre></code>

## ChildAlert endpoint 

**GET** - http://localhost:8080/childAlert?address={address} >>> returns the household composition for the address entered, only if the household contains children. The informations returned are: firstName, lastName, age.

<pre><code>
{
    "address": "1509 Culver St",
     [
        {
            "firstName": "John",
            "lastName": "Boyd",
            "age": 36
        },
        {
            "firstName": "Jacob",
            "lastName": "Boyd",
            "age": 31
        }
	{
            "firstName": "Tenley",
            "lastName": "Boyd",
            "age": 8
        }
	{
            "firstName": "Roger",
            "lastName": "Boyd",
            "age": 21
        }
	{
            "firstName": "Felicia",
            "lastName": "Boyd",
            "age": 34
        }
    ]
}</pre></code>
  
## PhoneAlert endpoint 
  
**GET** - http://localhost:8080/phoneAlert?firestation={firestation_number} >>> returns the list of all the phone numbers of the persons served by the firestation entered. 
<pre><code>
Example for: firestation=4

[
    "841-874-6874",
    "841-874-9845",
    "841-874-8888",
    "841-874-9888"
]</pre></code>

## Fire endpoint 

**GET** - http://localhost:8080/fire?address={address} >>> returns the list of persons who live at the address, and the firestation number that serving it. The list must include: name, phone number, age and medical record.
<pre><code>
Example for: address=951 LoneTree Rd
[
    {
        "stationNumber": "2",
        "firstName": "Eric",
        "lastName": "Cadigan",
        "age": 74,
        "phoneNumber": "841-874-7458",
        "medications": [
            "tradoxidine:400mg"
        ],
        "allergies": []
    }
]</pre></code>

## Flood endpoint 

**GET** - http://localhost:8080/flood/stations?stations={a_list_of_station_numbers} >>> returns the list of all the households served by the firestation. It must group persons by address, and include the following information: name, phone number, age and medical record.

## PersonInfo endpoint 

**GET** - http://localhost:8080/personInfo?firstName={firstName}&lastName={lastName} >>> returns the name, address, age, email and medical record of all persons with the same last name.
<pre><code>
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "age": 36,
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": "97451",
        "email": "jaboyd@email.com",
        "medications": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    },
	...
    {
        "firstName": "Allison",
        "lastName": "Boyd",
        "age": 55,
        "address": "112 Steppes Pl",
        "city": "Culver",
        "zip": "97451",
        "email": "aly@imail.com",
        "medications": [
            "aznol:200mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    }
]</pre></code>

## CommunityEmail endpoint 

**GET** - http://localhost:8080/communityEmail?city={city} >>> returns all the person's email addresses who live at the city entered.
<pre><code>
{
    "city": "Culver",    
	["jaboyd@email.com","drk@email.com","tenz@email.com","jaboyd@email.com","jaboyd@email.com","drk@email.com","tenz@email.com","jaboyd@email.com","jaboyd@email.com","tcoop@ymail.com","lily@email.com","soph@email.com","ward@email.com","zarc@email.com","reg@email.com","jpeter@email.com","jpeter@email.com","aly@imail.com","bstel@email.com","ssanw@email.com","bstel@email.com","clivfd@ymail.com","gramps@email.com"]
}</pre></code>
  
