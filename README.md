# Transaction timeout

This is a minimal Spring project to experiment with JPA transaction timeout.

## Prerequisites

1. A PostgreSQL database
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

## Run the application

Run the application:

```
$ mvn tomcat7:run
```

- Execute the first curl to insert a record
- Execute the second curl to update the record, adding a delay in the database
