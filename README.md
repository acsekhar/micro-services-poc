Proof of Concept Java-Microservice
==================================

Status: Alpha - Work In Progress

Introduction
------------

This is a proof of concept but fully functional implementation of a REST web service. The microservice is based on Spring Boot. It can be run locally using the integration test case. But the proof of concept is to deploy it to docker container. The 

Running it 
----------

**Build, Test and Run**

* Ensure Java 8 installed
* Clone the repo
* Build (Run in customer folder): `gradlew build buildDocker` This will build the java app, run the container test with embedded cassandra and then build the docker container.
* The app can be launched from command line as well. `java -jar [-Dcassandra.contactpoints=<Cassandra host DNS/IP>] [-Dcassandra.port=<Cassandra port e.g. 9042>] build\libs\customer-0.0.1-SNAPSHOT.jar`

**Deploy to docker container**

* Install Docker - follow getting started instructions at [an example](http://www.example.com/) inline link.
* Create Cassandra 2.2 container. [Reference](https://hub.docker.com/_/cassandra/) 
* Follow steps to connect to Cassandra using cslsh and run following cql statements. 
```
CREATE KEYSPACE services WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

USE "services";

CREATE TABLE customer (
name text,
contactfirstname text, 
contactlastname text,  
email text, 
PRIMARY KEY (name));
```
* The application assumes cassandra listens on port 9042. If you have it configured differently, update application.properties and run the build again.
* Deploy the microservice to a container (note that the cassandra cluster below is assummed to be named as cassandra-one. Please update the name as applicable).
```
docker run -p 8080:8080 -d --name service-customer --link cassandra-one service/customer:latest
```
* The application runs on port 8080. The port is exposed on the IP address of the docker host. You can use your favorite REST client and test it out:
```
// Create customer record
// Send POST to URL : http://<Docker host IP>:8080/customer/
// With Header      : Content-Type:application/json 
// With Body        :
Body
{
    "name":"Acme", 
    "contactFirstName":"John", 
    "contactLastName":"Doe", 
    "email":"John.Doe@acme.com" 
}
```
```
// Retrieve customer record 
// Send GET to URL: http://<Docker host IP:8080/customer/ 
```

Technology Stack
----------------
* Java
* Spring Boot
* Spring Data Cassandra
* Embedded Tomcat
* Spring Test with Embeddes Cassandra using [cassandra-unit](https://github.com/jsevellec/cassandra-unit/wiki/Spring-for-Cassandra-unit) 
* Gradle

TODOes
------
* More junit tests
* REST input validations and error hanndling
* logging
* Monitoring
* Sample REST Client
* Clustered deployment using Kubernetes

