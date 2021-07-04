# Exercise 1: Data Ingestion

In this exercise we will learn to ingest data in real-time into the architecture so, at the end, we will have cryptocurrency prices flowing in real-time through NiFi to Kafka.

![Exercise architecture](../img/architecture_exercise1.png)

## Data source

The data source we will be using for this exercise is [**Coinbase**](https://www.coinbase.com/), where they have a full API, including the delivery of real-time data via WebSockets.

For a reference of the API follow the below link:

* https://docs.pro.coinbase.com/

## Development

### Setup

Start the NiFi service:

```
docker-compose start nifi
```

Once it is running, go to http://localhost:8090/nifi


### Part 1: Basic NiFi workflow

This will be explained during the class.

### Part 2: Getting cryptocurrencies prices from Coinbase

In this part we will be getting data in real-time from Coinbase's websockets, and storing locally for testing purposes.

Steps:

* Stop (or even remove) previous processors
* Load template
  * Upload Template `nifi/Coinbase.xml` 
  * Add template
  * Configure services (secrets)
* Run

If you want to **check the results** (files saved), they will be available in the NiFi Docker container (if using Docker). Do the following to check it:

```
docker ps
docker exec -it <nifi_container_id> /bin/bash
ls -l <folder_configured_in_PutFile_Processor>
```

### Part 3: Send to Kafka

Start the Kafka services:

```
docker-compose start zookeeper broker control-center
```

Once it is running, go to Control Center (http://localhost:9021/) and navigate to the topics section (click on the cluster and then on "Topics").

Now change the NiFi workflow to send messages to Kafka instead of saving to file.

**TIP**: Replace the **PutFile** processor for **PublishKafka_2_6**.

# Reference

* [Apache NiFi documentation](https://nifi.apache.org/docs.html)