Core Banking Application (CBA) for third parties (FinTechs, Aggregators etc.) to connect.

## Micronaut 2.3.0 Documentation

- [User Guide](https://docs.micronaut.io/2.3.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

- [Etranzact Core Banking Application](#etranzact-core-banking-application)
  - [Technologies](#technologies)
  - [Setting up Development Environment](#setting-up-development-environment)
    - [Install Java](#install-java)
    - [Install Docker and Docker-Compose](#install-docker-and-docker-compose)
    - [Install Ngrok](#install-ngrok)
    - [Database](#database)
  - [Environment Variables](#environment-variables)
  - [Building the Application](#building-the-application)
    - [Run Tests, Build executable jar, Build Docker image](#run-tests-build-executable-jar-build-docker-image)
    - [Build without running tests](#build-without-running-tests)
    - [Running your local database](#running-your-local-database)
    - [Running the application locally](#running-the-application-locally)
  - [IDE](#ide)
    - [Setup Lombok](#setup-lombok)
    - [Annotation Processor](#annotation-processor)


## Technologies

1. **Java 8**
    - This should work with OpenJDK or Oracle JDK. If you are using [SDK man](https://sdkman.io/install), you can call install this using `sdk install java 8.0.222-zulu`. We are using Java 8 because of the possibility to create native image using graalvm. This is currently not supported, but it is possible for Java 8 applications.
2. **Micronaut**
    - Micronaut is a new Java framework. It feels very similar to Spring Framework with two major differences: (a.) it does not use reflection. Instead, it uses Ahead of Time (AOT) compilation. This allows really fast startup time and really low footprint without sacrificing the clean code you can get from a Spring-like application. And (b.), it is cloud-native. It is created to be deployed to the cloud from the very start allowing clean integration with cloud technologies like GCP (Google Cloud Platform), Kafka, etc
3. **Micronaut Inject**
    - The dependency injection used by this application is a Micronaut library as well. This allows injection using the typical annotations like `@Singleton`, `@Inject`, etc, without using reflection. This allows for really fast startup time and low memory footprint.
4. **Micronaut Hibernate** and **Micronaut Data**
    - Micronaut Hibernate is the Micronaut's integration with Hibernate. Hibernate will still use reflection (because that is how it is designed to work), but we will be able to integrate Hibernate cleanly with the rest of Micronaut application. On the other hand, Micronaut Data is like Spring Data. It allows for creation of "repositories" by creating interfaces only without method implementation.
5. **Unirest**
    - This is the library used to do REST calls. THis can potentially be replaced by **Micronaut HTTP** but we have currently not migrated to that yet. No reason yet to do so (but should do it)
6. **Micronaut Security**
    - This is Micronaut's counterpart of Spring Security. This allows for usage of `@Security` and other related functionalities like JWT.
7. **Micronaut OpenAPI**
    - This is Micronaut's library to create [OpenAPI](https://swagger.io/docs/specification/about/) specification.

## Setting up Development Environment

### Install Java

It is recommended to use [SDK man](https://sdkman.io/install).

```
$ curl -s "https://get.sdkman.io" | bash
```

Once you have SDK Man, you can install Java 8

```
sdk install java 8.0.222-zulu
```

### Install Docker and Docker-Compose

Docker and Docker-Compose are used in development to build the docker image deployed to production, and at the same time, running third party applications that Etranzact Core Banking Application depends on like PostgreSQL.

https://docs.docker.com/install/

https://docs.docker.com/compose/install/

### Install Ngrok

Ngrok allows you to expose your local application to a public domain. For example, if your local application is accessible via http://localhost:8080, you can make this publicly accessible by using ngrok, and then it would suddenly be accessible via https://701a7e13.ngrok.io. This is needed for local development because certain third party APIs we are using sends their responses back via a callback URL. Thus, during development, we present our local application via ngrok so that the 3rd party API can send their callback response via that ngrok public URL.

Follow the instruction in Ngrok's website to install it in your local machine - https://ngrok.com/download

Once you have it installed, set an environment variable named `NGROK_PATH` to point to your ngrok's executable.

i.e.

```
export NGROK_PATH="/var/lib/snapd/snap/bin/ngrok"
```

### Database

You can install postgresql 11.2 manually, but that is not needed.

When running the tests, the tests downloads a docker image of postgresql and run it locally.

And when running Etranzact Core Banking Application locally, you can just `cd ./infra` and then `docker-compose up` to start postgresql.

## Environment Variables

The following environment variables are being used by the application.

```
## Get the details from Del
export FIXER_IO_API_KEY=
export AT_API_KEY=
export AT_API_USERNAME=
export AT_PRODUCT_NAME=
export FLOPAY_CLIENT_ID=
export FLOPAY_CLIENT_SECRET==
export TELLERNET_API_USERNAME=
export TELLERNET_API_KEY=
export TELLERNET_API_PASSCODE=
export TELLERNET_MERCHANT_ID=
export ZEEPAY_CLIENT_ID=
export ZEEPAY_CLIENT_SECRET=
export ZEEPAY_USERNAME=
export ZEEPAY_PASSWORD=
export HMAC_SECRET_KEY=

## If TellerNet's mobile money fund transfer is not working, set this to false.
export TELLERNET_MOBILE_MONEY_FUND_TRANSFER_WORKING=

## Path to your ngrok executable.
export NGROK_PATH=
```

## Building the Application

### Run Tests, Build executable jar, Build Docker image

```
./mvnw clean install
```

### Build without running tests

_This is not advisable._ The great thing about micronaut is that it is lightweight and has fast startup time. This allows us to create a lot of integration tests and run them really fast locally.

```
./mvnw clean install -DskipTests
```

### Running your local database

A Docker Compose file is in the source code as well. This allows for consistent setup of database (and potentially, any other 3rd party applications that would be used in the futrue).

```
cd ./infra
docker-compose up
```

To stop this, just do Ctrl+C.

### Running the application locally

A maven profile was created to run the application locally. In production, the main class used is `com.waya.system.database.MainApplication`. In development, `com.waya.system.database.test.MainApplicationWithNgrok` is used instead. That is because `MainApplicationWithNgrok` takes care of running your ngrok locally before running `MainApplication`. In production, ngrok is not needed, that is why `MainApplication` is what is used in production and not `MainApplicationWithNgrok`   

```
./mvnw -Prun
```

## IDE

You can use both IntelliJ Community Edition or Eclipse to work on Etranzact Core Banking Application. It is recommended to use IntelliJ though. IntelliJ's community edition is enough.

### Setup Lombok

You need to install your IDE's lombok plugin. For IntelliJ, this is the recommended plugin - https://plugins.jetbrains.com/plugin/6317-lombok/

### Annotation Processor

You also need to turn on Java compiler's annotation processor. This tells the IDE Java compiler to read the annotations for Ahead of Time (AOT) compilation. This is needed both for Lombok and Micronaut

In IntelliJ, you go to Settings (or Preferences), then `Build, Execution, Deployment` > `Compiler` > `Annotation Processors`, then click `Enable Annotation Processor`

Also, add `-parameters` to the IDE"s compiler parameters. In IntelliJ, that is `Build, Execution, Deployment` > `Compiler` > `Java Compiler`, then in the textfield `Additional Compiler Parameters`, add `-parameters`
