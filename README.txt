
------------------------------------------------------------
| System Requirements
------------------------------------------------------------
To build or run the project, you MUST have a copy of Java JDK 8 on your machine. 
After installing or unzipping the Java JDK, one of the following environment variables must be configured:
  (1) set JAVA_HOME to point to your JDK installation directory
  (2) JDK bin directory is added on your PATH


------------------------------------------------------------
| Build & Run Project (In Windows Command Prompt)
------------------------------------------------------------
cd account-service
gradlew build
gradlew bootRun


------------------------------------------------------------
| Build & Run Project (In Windows Git Bash, On Mac)
------------------------------------------------------------
cd account-service
./gradlew build
./gradlew bootRun

------------------------------------------------------------
| Accessing the Services through Swagger-UI (i.e., after the app is started in previous step)
------------------------------------------------------------
	http://localhost:8081/swagger-ui.html

	Services accessible from the above link. There are three services added.
    	1) Get All Accounts : it lists out the available Accounts
    	2) Create Account : Creates the Account based on input request
    	3) Delete Account : Deletes the account if it is present

------------------------------------------------------------
| Build & Run Project (In Eclipse)
------------------------------------------------------------
Your Eclipse must support both Gradle and Lombok.

We recommend that you download and use the latest version of Eclipse; i.e. Eclipse Oxygen.
The following link points to a lightweight distribution of Eclipse Oxygen that already comes bundled with Gradle Buildship:
https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/2/eclipse-java-oxygen-2-win32-x86_64.zip

The following are the steps to configure your Eclipse to support Lombok:
 1. Download the Lombok jar from http://www.nexus.ford.com/repository/external-proxy-group/org/projectlombok/lombok/1.16.20/lombok-1.16.20.jar
 2. Double-click the downloaded Lombok jar. Lombok Installer should pop-up. (If not, then try running the command: java -jar lombok.jar)
 3. In the Installer, select or browse your Eclipse location. Click "Install / Update" button.
 4. Restart Eclipse.


Load Project: File > Import... > Existing Gradle Project


------------------------------------------------------------
| Build & Run Project (In IntelliJ)
------------------------------------------------------------
We recommend that you first download and upgrade to the latest IntelliJ IDEA available from the JetBrains site: https://www.jetbrains.com/idea/download/

The following are the steps to configure and enable Lombok processing in IntelliJ:
 1. File > Settings. (Mac Users: IntelliJ IDEA > Preferences...)
 2. Select 'Plugins' item. Click 'Browse repositories...' button on the bottom. Search for 'Lombok Plugin'. Select 'Lombok Plugin' search result and click 'Install'.
 3. Click 'Restart IntelliJ' button.
 4. File > Other Settings > Default Settings. Search for 'annotation'.
 5. Check the 'Enable annotation processing' option. Click OK.

Load Project: File > New > Module from Existing Sources...