# Mytheresa_Amir_QA_Task

## `Prerequisites:`

Download and install [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).<br />

Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows).<br />

Download and install [Apache Maven](https://maven.apache.org/download.cgi).<br />

============================<br />

## `Run Configuration:`

> For running testcases:

Just open feature files and run through it.
 
> To run Login Test Case on Saucelab:

Edit following variables in **BaseTest.java** file and **setupSauceLabs** method with your SauceLab credentials.

SAUCE_USER = YOUR SAUCE USER NAME,
SAUCE_KEY = YOUR SAUCE KEY NAME

> To run against different environments(local, test, staging):

The URLs are stored in the feature file commented, "webBrowserName" indicate to the name of browser (Chrome, Firefox) and "environment"
indicate on which environment the testCase will run on it, "Docker" (run on docker), "SauceLabs" (run on sauce-lab) and any environment else (run on local machine)

> To run on Docker

Run docker-compose.yml, but the environment should be "Docker" in the feature file

============================<br />
