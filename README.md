# Transaction timeout

This is a minimal Spring project to experiment with JPA transaction timeout.

## Prerequisites

1. An oracle database
2. Java 8/Maven2

## Information on the code

The project is a Java configured Spring project, with dynamically registered the dispatcher servlet, so no web.xml.

The transaction manager is configured in `fraglab.application.ApplicationConfiguration#transactionManager` where
a default timeout is also set. Everything occurs in the rest resource, that is annotated with `@Transactional`. There is 
also a timeout attribute on that annotation.

There are 2 rest calls available. One that inserts a new record and one that updates an existing record. For your
convenience the two requests used are in `curl` syntax:

**Insert an author with name _one_**
```
curl -X POST \
  http://localhost:8080/playground/api/author \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d name=one
```

**Update the author with id _1_ to name _one+_**
```
curl -X POST \
  http://localhost:8080/playground/api/author/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d name=one%2B
```

## The problem

It seems that the transaction timeout is not enforced. Why is that and how can it be enforced?

### ANNEX 1: Oracle JDBC 

The Oracle JDBC driver has to be available in your local repository in order to satify the following dependency.

```xml
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>11.2.0.4</version>
</dependency>
``` 

I downloaded it from [Oracle](http://download.oracle.com/otn/utilities_drivers/jdbc/11204/ojdbc6.jar) 
(you need an Oracle account for that), and installed it using:

```
$ mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dpackaging=jar -Dversion=11.2.0.4 -Dfile=ojdbc6.jar -DgeneratePom=true 
```

### ANNEX 2: Delay script

In the `/external` folder there is a stored procedure to install in Oracle. Invoking this introduces a delay. You need
to install this before running the application.

### ANNEX 3: Run the application

Run the application:

```
$ mvn tomcat7:run
```

