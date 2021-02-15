# embl_ebi_practical_test
The repo is to have the practical assessment of EMBL_EBI

## Building, Testing and Running embl_ebi_practical_test

You will need:
* [embl_ebi_practical_test source code](https://github.com/selvapuram/embl_ebi_practical_test)
* [Java JDK](http://java.sun.com/javase/downloads/index.jsp)
* Apache Maven
    * [https://maven.apache.org](http://maven.apache.org)
    * [Maven on Windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)
* A Unix/Linux shell environment OR the Windows command line


If you are working from the Windows command line you must also install a Java JDK, and [set the JAVA_HOME environment variable](http:confluence.atlassian.com/display/DOC/Setting+the+JAVA\_HOME+Variable+in+Windows) (please ensure it points to the JDK, and not the JRE) and the MVN\_HOME environment variable. You may need to reboot your machine after setting these environment variables. 

Ensure that you set your MAVEN_HOME environment variable, for example:

```MAVEN_HOME=E:\Downloads\apache-maven-3.5.4-bin\apache-maven-3.5.4\```

NOTE: 
1.You can use Maven directly
2.application.properties has the db details and port number

and for any permission related item, Please use 
chmod command to give the permission in linux or mac machines in the terminal.


### Building
To build the application from source clone or unzip the application and from navigate to the root directory.
```
mvn clean install -DskipTests
```

### Testing
To run all tests, use:
```
mvn clean install
```



### Running
To run embl_ebi_practical_test from the command line (assuming you have been able to build from the source code successfully)
```
run.bat or java -jar  target\restfulmicroservice-0.0.1-SNAPSHOT.jar
```

### URL To Execute Application
Application:  http://localhost:8080/swagger-ui.html

H2: http://localhost:8080/h2-console/

Use Auth controller to login with pre defined user and password

Jwt token is integrated. Use Authcontroller. Refer Swagger

username: user
password: password

username: admin
password: password

enter the token in the auth header 'Authorization: Bearer {{token}}' to proceed with api

## Building, Testing and Running embl_ebi_practical_test from STS(Spring Tool Suite)
embl_ebi_practical_test' source comes with Maven configuration files which are recognized by [Eclipse](http://www.eclipse.org/) if the Eclipse Maven plugin (m2e) is installed.

At the command line, go to a directory **not** under your Eclipse workspace directory and check out the source:

```
git clone https://github.com/selvapuram/embl_ebi_practical_test.git
```
As embl_ebi_practical_test comes with some jars that need to be installed in Maven, run `mvn clean install` to install these first. (This only needs to be done once on your system.)
Then in Eclipse, invoke the `Import...` command and select `Existing Maven Projects`. 

![Import a Maven project](images/Eclipse/eclipse-1.png)

Choose the root directory of your clone of the repository.

![Select maven projects to import](images/Eclipse/eclipse-2.png)

To run and debug recruiment from STS(Spring Tool Suite), spring boot dashboard has everything to run and debug.



### Testing in Eclipse

You can run the server tests directly from Eclipse. right click on the source folder ![](src/test/java), select `Run As` -> `Junit Test`. This should open a new tab with the junit launcher running the embl_ebi_practical_test service tests.


