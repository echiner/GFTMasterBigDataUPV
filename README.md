# UPV: Big Data Architectures for Investment Banking

Welcome to the "**Big Data Architectures for Investment Banking**" exercise.

In this exercise you will build a complete end-to-end Big Data Streaming Architecture, processing & analyzing cryptocurrencies data in real-time.
The below diagram provides a **high level view of the architecture**:

![Exercise architecture](img/architecture.png)

In this other diagram we can see the architecture with some **more detail**:

![Detailed architecture](img/architecture_detailed.png)

By the end of the classes we should have the whole architecture up & running, then the trainee will have to find a new data source (ideally in real-time) and build its own flow.

## Course outline

Follow the links below to access each exercise's details, code, etc.

* [**Exercise 0**: Setup](exercise0_setup)
* [**Exercise 1**: Data Ingestion](exercise1_data_ingestion)
* [**Exercise 2**: Data Processing](exercise2_data_processing)
* [**Exercise 3**: Data Storage](exercise3_data_storage)
* [**Exercise 4**: Data Visualization](exercise4_data_visualization)
* [**Exercise 5**: Machine Learning](exercise5_machine_learning)
* [**Post work**: Build you own flow](post_work)

## Infrastructure reference

| Component | Service | Description | URL/port |
| ------------- | ------------- | ------------- | ------------- |
| **Apache NiFi**  | nifi-upv | Data Ingestion Tool  | https://localhost:8443/nifi  |
| **Confluent Kafka**  | broker | Message Broker  | localhost:9092  |
| **Kafka UI (Redpanda Console)**  | kafka-ui | Kafka Manager & Monitor  |  http://localhost:8080  |
| **Spark UI** | N/A | Spark UI (jobs, environment, etc.) | http://localhost:4040 |
| **Elasticsearch**  | elasticsearch | Data storage and search engine  | http://localhost:9200  |
| **Kibana**  | kibana | Dashboarding and Elasticsearch dev/admin tool  | http://localhost:5601  |
| **Jupyter**  | jupyter | Notebooks analytics  | http://localhost:8888  |
