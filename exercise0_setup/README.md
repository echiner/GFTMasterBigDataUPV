# Exercise 0: Setup

In this exercise you will understand the software requirements, and how to do an initial setup.

The whole architecture is based on [Docker containers](https://www.docker.com/) in order to make it easier and quicker to setup. So there is no need to install each one of the components.

So, to start with, make sure you have **Docker** installed in your PC.

* Docker Desktop: https://www.docker.com/products/docker-desktop

**OPTIONAL**: If you want to learn more about the different components of the architecture, feel free to install them individually instead of using Docker. This will give you a better understanding of the components, how to run and configure them. This is also an option in case you have limited resources in your PC or problems running Docker.

## Infrastructure Setup

In order to **launch** the required infrastructure you just have to go to the "docker" folder and run the following:

```
docker-compose up -d
```

This will launch the following components, which will be used during the course:

| Component | Service | Description | URL/port |
| ------------- | ------------- | ------------- | ------------- |
| **Apache NiFi**  | nifi | Data Ingestion Tool  | http://localhost:8090/nifi  |
| **Zookeeper**  | zookeeper | Software for distributed coordination  | N/A  |
| **Confluent Kafka**  | broker | Message Broker  | localhost:9092  |
| **Kafka Connect**  | kafka-connect | Kafka Connect node  | N/A  |
| **Confluent Control Center**  | control-center | Kafka Manager & Monitor  |  http://localhost:9021  |
| **Elasticsearch**  | elasticsearch | Data storage and search engine  | http://localhost:9200  |
| **Kibana**  | kibana | Dashboarding and Elasticsearch dev/admin tool  | http://localhost:5601  |
| **Jupyter**  | jupyter | Notebooks analytics  | http://localhost:8888  |

You can **check everything is running** by checking the Docker dashboard or by clicking on the URLs above.

Once checked, and given that we won't need everything from the beginning, feel free to **stop all services** for the time being to reduce resource usage:

```
docker-compose stop
```

If you wanted to start the whole infrastructure again (don't need to do it now) you can run the following:

```
docker-compose start
```

or, whenever you need so stat a specifi service:

```
docker-compose start <service>
```

## Software requirements

You will also need **Java** and an **IDE** installed:

* IDE for Java development (your choice)
 * https://www.jetbrains.com/idea/download/ (Choose InteliJ Community)  (**RECOMMENDED**)
 * https://www.eclipse.org/downloads/packages/ 

* Java Virtual Machine (choose JDK 8 or 11)
  * https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html

* (Optional but recommended) GIT
  * https://git-scm.com/downloads

## Development Environment Setup

* Install IDE
* Download the code with any of these two options:

Clone the project (if you have GIT installed):
 
 ```
 git clone https://github.com/echiner/GFTMasterBigDataUPV
 ```

Or, download the course exercise directly from the repository: https://github.com/echiner/GFTMasterBigDataUPV (click on "Clone or download")

# Admin & Troubleshooting

The easiest way to do many of these things is to use, either the Docker Desktop Dashboard or a tool such as **[Lazydocker](https://github.com/jesseduffield/lazydocker)**:

1. Download the binary: https://github.com/jesseduffield/lazydocker/releases
2. Uncompress
3. Run

In any case, you can just do it using command line as explained in the following subsections, or use the **Docker Dashboard**.

### Starting and stopping things

In order to stop/start single service, use the following commands:

```
docker-compose start <Service>
```

Where the **<Service>** is listed in the table above ("Component list" section).

### Reading the logs

List the running containers first:

```
docker ps
```

Then use the following command to

```
docker logs <CONTAINER ID or NAME>
```

### Teardown (destroy everything)

```
docker-compose down -d
```