Pre-Requisites

1. Java 1.8
2. Maven 3 or higher


Steps to run the application

1. The zip file includes a self-contained Maven Project that can run standalone. The application bootstraps a jetty server,so no external
	web container is needed to run the application. 
2. The easiest way to run the project is to run 
		mvn spring-boot:run 
   from command after you unzip the folder. Make sure your run the command from the folder containing pom.xml (the login-app folder)
3. Instead if you want to run it as a Java application , build the project first by running 
   mvn clean:package 
   and then run the application using java -jar target/login-app-1.0.0-SNAPSHOT.jar
   
4. If you want to run the application from an IDE, import the folder into Eclipse or IntelliJ as a maven project. Once imported
   run the class Application (which contains the main method) in com.architech.login package.


