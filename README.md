# bookingBoss Project 

This project fullfills the requirements specified in following codility assignment.  
https://github.com/BookingBoss/java-test

#### Techonlogy STACK 

- Java 1.8
- Maven as build tool
- Spring Boot
- Spring boot Security
- Junit
- REST Api
- Hibernate
- My SQL
- H2 in memeory database

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes. See deployment for notes on how to deploy the project.

### Prerequisites

For building and running the application you need:

- JDK 1.8
- Maven
- My SQL

### Installing

1. Create SQL Schema 
```
create database booking_boss_test;
```

2. Install java project  

There are several ways to run this bookingBoss Spring Boot application on your local machine. 

- One way is to execute the main method in the com.helix.leisure.BookingBossAppRunner class from your IDE.

- Alternatively you can use the Spring Boot Maven plugin like so:
```
    $ mvn clean install  
    $ mvn package spring-boot:repackage  
    $ java -jar target/bookingBoss-1.0.0-SNAPSHOT.jar 
```    

Additional Notes :   
If the application is not properly starting because of a port binding issue, you can simply use following commands to kill it.

netstat -ano | findstr :8080  
taskkill /PID << port number >> /F

## Running the tests

After Successfull installation and deployment of project, you can use following steps to test application.

You can use Rest client such as 'Postman' or 'Chrome rest client' to test api calls.

Notes :- Use following credentilas to access Rest API when the popup appears.

```
username - admin1  
password - secret1
```

1. http://localhost:8080/putproducts/

Request method :- POST
Request Type :- Application / Json
Authentication :- Basic Authentication
Request Sample Body :-

```
{

  "timestamp": "2018-10-21T01:51:02.000+0000",
  "products": [
    {
      "name": "Sample Product1 ",
      "quantity": 2,
      "sale_amount": 3.5
    }
  ]
}
```

2. http://localhost:8080/rest/getProducts/

Request method :- GET
This will give list of products from the database.

Sample Response

```
[{
		"id": 1,
		"timestamp": "2018-10-21T01:51:02.000+0000",
		"products": [{
			"id": 1,
			"name": "Sample Product 1",
			"quantity": 2,
			"sale_amount": 3500.00
		}]
	},
	{
		"id": 2,
		"timestamp": "2018-10-21T01:57:02.000+0000",
		"products": [{
			"id": 2,
			"name": "Sample Product 2",
			"quantity": 2,
			"sale_amount": 2000.00
		}]
	}
]
```
(No need to specifically mention ids since application automatically populating ids in the database.)

### End to end unit test coverage

You can run test cases under src/test/java. Unit tests will use in-memory database to save and retrieve records.


