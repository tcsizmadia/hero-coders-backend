# Hero Coders Backend - Entity Auditing Demo

A demonstration project showcasing Entity Auditing capabilities using Hibernate Envers in a Helidon MP application.

## Introduction to Entity Auditing

Entity Auditing is a critical aspect of enterprise applications that need to track changes to data over time. It provides:

- Historical record of all changes made to entities
- Ability to restore previous versions of data
- Audit trails for compliance and security purposes
- Transparency in multi-user systems

This project demonstrates how to implement entity auditing in a Java application using Hibernate Envers.

## About Hibernate Envers

[Hibernate Envers](https://hibernate.org/orm/envers/) is an auditing module for Hibernate ORM that automatically tracks historical changes to your persistent entities. Key features include:

- Automatic versioning of entity data
- Minimal configuration through annotations
- Seamless integration with existing Hibernate applications
- Support for querying historical data
- Ability to retrieve and restore previous entity versions

Envers works by creating audit tables that mirror your entity tables, storing a copy of the entity's state whenever it changes.

## About Helidon MP

[Helidon MP](https://helidon.io/docs/v4/mp/introduction) is a lightweight MicroProfile framework for building Java microservices. It provides built-in support for REST APIs, configuration, health checks, and metrics with minimal overhead and fast startup times. Helidon's declarative programming model complements Hibernate Envers well, allowing for clean implementation of audit functionality in microservices.

## Project Requirements

- Helidon MP 4.2.0
- JDK 21 or higher
- Maven 3.8+
- Docker (optional, for containerized deployment)
- Kubernetes (optional, for container orchestration)

## Build and Run

### Local Development

With JDK21:

```bash
mvn package
java -jar target/hero-coders-backend.jar
```

### Testing the Application

The project includes a `sample.http` file that contains sample REST calls to demonstrate the functionality of the API.

The `sample.http` file provides example REST calls to demonstrate the API's functionality. The recommended way to use this file is with the [REST Client VS Code extension](https://marketplace.visualstudio.com/items?itemName=humao.rest-client). This extension allows you to send HTTP requests and view responses directly within VS Code.

The sample requests demonstrate:

- Creating a new hero
- Retrieving all heroes
- Getting the audit history of a specific hero
- Restoring a hero to a previous version

### Docker Image

To build a Docker image for the application, run the following command:

```bash
docker build -t hero-coders-backend .
```

To run the Docker container, use:

```bash
docker run -p 8080:8080 hero-coders-backend
```