# Profile Builder

Profile Builder is a containerized [Cloud Run](https://cloud.google.com/run/docs) application that builds user profiles for the prototype for Leadourship.com.
Code has been redacted/changed in order to protect Intellectual Property


### Table of Contents
* Contributing to this code base
* Acronyms
* **Highly** recommended reading
* Infrastructure
* Development

---
## Contributing to this code base
This ain't cowboy country! We have rules in these here parts. If you contribute to this code you are responsible for this README.

Below are the non-negotiables about maintaining the file:

* This file must be both helpful and the information correct
* Any acronyms used must be defined in the Acronyms section
* If it's out of date, make it up to date or create a task to update it
---

## Acronyms 
* API - Application Programming Interface
* IDE - Integrated Development Environment
* LLC - Limited Liability Corporation

---

## Highly recommended reading
* [Cloud Code documentation for your IDE](https://cloud.google.com/code/docs)
* Any documentation for libraries you aren't familiar with
---

## Infrastructure 

### Container: Docker
https://www.docker.com/
### Compute Engine: CloudRun
https://cloud.google.com/run/docs
### Framework: Spring
The application framework is Spring Boot. 
https://spring.io/projects/spring-boot#overview

Model-View-Controller: The Spring web framework is built around the MVC (Model-View-Controller) pattern

Spring Dependency Injection: https://www.baeldung.com/spring-dependency-injection

### Build: Maven
Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
https://maven.apache.org/

### HTTP client: OkHttp
https://www.baeldung.com/guide-to-okhttp
https://square.github.io/okhttp/

### Template engine: Thymeleaf
Thymeleaf is a Java template engine which can process HTML, XML, text, JavaScript or CSS files. Unlike other template engines, Thymeleaf allows using templates as prototypes, meaning they can be viewed as static files.
https://www.thymeleaf.org/
https://www.baeldung.com/thymeleaf-in-spring-mvc

Here is a resource if you want to read about other template engines compatible with spring: 
https://www.baeldung.com/spring-template-engines

### Testing: JUnit
https://www.baeldung.com/junit-5

## Libraries
### Google Cloud Libraries: Spring Cloud GCP
https://spring.io/projects/spring-cloud-gcp
### Google Fonts: Material Icon Library
https://developers.google.com/fonts/docs/material_icons#icon_font_for_the_web

---

## Development: Getting started
### Prerequisites
*  Choose Your preferred Integrated Development Environment (IDE)
    * [Spring Tools Suite](https://spring.io/tools) (STS)
    * [IntelliJ IDEA](https://www.jetbrains.com/idea/)- I recommend this one. Let me know if you want it.
    * [VS Code](https://code.visualstudio.com)

### Steps:
1) Install Java 8 on your machine. The Google Cloud plugins are not yet compatible with newer versions of Java.
2) Install Docker: https://docs.docker.com/get-docker/
   2) Run installation exe 
   3) Turn on Kubernetes: Docker Desktop > Settings button > Kubernetes tab > checkmark Enable Kubernetes > Apply & restart.
      4) Note: I restarted mfy PC after install. Not sure if it was necessary, but all went well
2) Install IDE
   3) Install [Cloud Code Plugins](https://cloud.google.com/code)
4) Clone repo 
   5) (this needs more detailed instructions)
6) Set local environment variables
10) Add "mysql-connector-java-5.1.25-bin.jar" to your path
    11) [Instructions on modifying Path environment variable on your computer](https://javarevisited.blogspot.com/2013/02/windows-8-set-path-and-classpath-java-windows-7.html#axzz7p5evT9UM)
11) mysql-connector-java-5.1.25-bin.jar
5) Get run to build locally 
   6) If you haven't read [Cloud Code documentation for your IDE](https://cloud.google.com/code/docs) I recommend you do that before trying to run locally. Cloud Code is very integrated with Google Cloud
7) Navigate to the version of the service that is being hosted on your computer. 
   8) Visit [http://localhost:8080](http://localhost:8080) in your browser.



