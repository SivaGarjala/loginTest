# loginTest

I used java 8 and selenium web driver v3.0.1 in this project, for which you can find artifact in pom.xml . I tried to implement Test driven framework and automate the test cases to check the login functionality of https://my.backbase.com.

Instructions and Pre-requisites to Download and run the project - "loginTests":

Pre-requisites:

1.	The Project has been created in a machine of 64-bit version. All the jars and other dependencies used in the project are of 64-bit version.
2.	Maven is necessary to import and run this project.
3.  Please make sure you have java 8 to run this project.
4. You should have selenium webdriver v3.0.1 jar in external dependancies in your project after importing in IDE.

Instructions:

1.	Download the Project and Import in to eclipse or Intellij IDEA or any IDE.
2.	The project is mavenised. Please build the project with command “mvn clean install” - to download all the dependancies required to run the project. 
3.	The Zip file also contains "chromedriver" - Please place it in a location in your machine and get the path of driver to provide in HomePage.java class in "/src/test/java/backBase"
4.	After you import, build the project; There is "HomePage.java" class in "/src/test/java/backBase" path.
5.	The Input file is in location inside the Resources folder in loginTests project “src/test/resources“.
6.	The Output file is also generated in same path - “src/test/resources“.
