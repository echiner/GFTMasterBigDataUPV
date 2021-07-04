# Exercise 2: Data Processing

In this exercise you will start reading and processing data in real-time using Spark Streaming.

![Exercise architecture](../img/architecture_exercise2.png)

## Development

### Setup

First of all, make sure the following services are up and running:

* nifi
* zookeeper
* broker
* control-center

If they are not running, check the previous exercises to understand how to run them.

Start your IDE (ideally IntelliJ) and import the code under `spark`.

### Part 1: Read data from Kafka, process and show in console

The code should be ready to run, as it is. So just run the main class (`com.gft.upv.spark.TickerProcessor`).

The code is doing the following:

* Connect to Kafka and retrieve messages (it will start from the beginning of the topic)
* Convert the incoming JSON into a Dataframe
* Create a SQL on that dataframe
* Show the results in the console

Now, understand the code and feel free to play around with it (specially with the SQL query). The SQL query is the logic we will be applying to the stream.

### Part 2: Send transformed data back to Kafka

Now that we have tested that we are processing data in real-time coming from Kafka and decided the logic (SQL query), let's send the data back to another Kafka topic. For doing so, comment the "streaming to console" part and uncomment the "sending to kafka" part.

If the code is working fine, all the newly generated data will be sent to a new topic (**tickers_transformed**). You should be able to see the messages in Control Center (under the "Topcis" section).

## Troubleshooting

https://sparkbyexamples.com/spark/spark-hadoop-exception-in-thread-main-java-lang-unsatisfiedlinkerror-org-apache-hadoop-io-nativeio-nativeiowindows-access0ljava-lang-stringiz/
https://github.com/cdarlint/winutils

# Reference

* [Spark 3.0.0 - Documentation](https://spark.apache.org/docs/3.0.0/)
* [Spark 3.0.0 - Structured Streaming](https://spark.apache.org/docs/3.0.0/structured-streaming-programming-guide.html)