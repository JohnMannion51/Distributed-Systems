# 4th Year Distributed Systems Project
G00340314 
## Requirements
You are required to use the JAX-RS/Jersey, Java RMI and JAXB frameworks to develop a simple Car Hire Booking System. A Web Client page should provide users with the ability to Create/Modify/Update/Delete bookings for a specific vehicle for a given set of dates. The Web Client will interact with a RESTful JAX-RS Web Service for bookings which is deployed on Apache Tomcat Server. The RESTful Web Service will act as an RMI client to an RMI Database Server which will handle persistence.

## Running the project
To run the project open a command window and cd to your desired workspace i.e. desktop. Then clone the project using the command "git clone" followed by the URL of the project https://github.com/JohnMannion51/Distributed-Systems.git. Open eclipse oxygen EE (or whatever version you have on your machine but it must be enterprise edition) and switch workspace to the folder you have just cloned. The new workspace will contain five folders. I included a lib folder, that contains the necessary jar files to run the project, that may need to be imported into the project in order for it to run.

* Booking-Server (Maven Project)
* Client
* Database-Service
* Servers
* lib

1. Click on the server tab in eclipse and add new apache server v7.0.
2. Right click on Database-Service and choose run as java application after a second or two you will be prompted to choose RMISetup.java with the package ie.gmit.sw and then click ok. This will create and populate the H2 database to use.
3. Right click on the Client folder and choose run on server.

A webpage will then open in eclipse with a number of buttons i.e. make a booking, show bookings. Choose an option and it will open another webpage that will display the information. The program creates the database and can print the data to screen and allow the user to manipulate the data.
### Rest examples
[Rest Example 1](https://www.journaldev.com/9170/restful-web-services-tutorial-java)

[Rest Example 2](http://www.vogella.com/tutorials/REST/article.html)

[Rest Example 3](https://www.tutorialspoint.com/restful/restful_methods.htm)

### Jersey examples
[Jersey Example 1](https://howtodoinjava.com/jersey/jersey-restful-client-examples/)

[Jersey Example 2](http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/)

[Jersey Example 3](https://o7planning.org/en/11217/create-java-restful-client-with-jersey-client)

### H2 database
[H2 database](https://db-engines.com/en/system/H2%3BMySQL)

[H2 Embedded Example](https://stackoverflow.com/questions/40211265/java-embedded-database-h2)

[H2 Tutorial](http://zetcode.com/java/h2database/)

### H2 Cheat sheet
[H2 Cheat-sheet](http://www.h2database.com/html/cheatSheet.html)

