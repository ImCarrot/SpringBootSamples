# Spring Boot CrudRepository Sample

Hi, in this sample we'll be looking up how to create and use `SpringBoot CrudRepository`.

SpringBoot CrudRepository provides sophisticated CRUD functionality for the type of entity you want to be managed.
This interface acts primarily as a marker interface to capture the types to work with and to help you to discover 
interfaces that extend this one. It takes the the domain class to manage as well as the id type of the domain class 
as type arguments.  

Generally to use up a `CrudRepository` you'll need a `database` and SpringFramework supports wide number of them 
including the most common ones like `SQL`, `MySQL`, `MongoDB`, `H2 database` and more. Let's get on with it then.

**For this sample you'll need:**
- Java 8+
- Maven 3+
- Spring Boot (the version of your choice we'll be using v2.2.2.RELEASE but the concepts are valid for 2.x SpringBoot)
- IDE of your choice (we'll be using Intellij Idea)

**Project Structure:**

