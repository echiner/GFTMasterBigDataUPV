# Post Work: Build your own flow

This is the last exercise, and would typically be done as homework, although we will try to progress as much as possible during the class. The trainee will use the already built streaming architecture to build its own real-time data flow.

## Part 1: Identify the data source

Fist of all, we need to identify the use case you will be implementing and, more importantly the data source we will be using (ideally in real-time).

Given that in some cases it is difficult to find a real-time data source, you have several options:

* **Real-time** (via WebSockets, faucet, stream...): This is the **preferred option** since we will receive data as it is being generated
* **API** (REST, WebServices...): If data is updated frequently this is a good option as well since we can do polling and retrieve recent data
* **Dataset** (JSON, CSV...): This is the last resort, but valid as well since you can easly convert a file into a stream of data by splitting the records

## Part 2: Build the flow

It is up to you to decide what you want to do with the data, but you will certainly have to apply changes in the following parts of the architecture:

* **NiFi**: You will have to create a new workflow for your new data source. You can reuse some of the current components (e.g. sending to Kafka)
* **Spark Streaming**: Data will be different, so you will need to adapt a little bit the logic (schema, query, etc.) and probably the topics it is pointing to
* **Kibana**: Create your own dashboards and visualizations
* **Jupyter** (optional): Apply machine learning to the data stored

# Data sources

## Real-time

Here is a list of real-time data sources which you could use:

* [Twitter](https://developer.twitter.com/en/docs): Twitter API provides programmatic access to Twitter in unique and advanced ways
* [Aviation Stack](https://aviationstack.com/documentation): Global aviation data for real-time and historical flights as well as allow customers to tap into an extensive data set of airline routes and other up-to-date aviation-related information
* [PubNub](https://www.pubnub.com/developers/realtime-data-streams/): Provides several sources, including simulated IoT data
* [Binance Websockets](https://github.com/binance/binance-spot-api-docs/blob/master/web-socket-streams.md): Real-time data from Binance (crypto exchange)
* [Crypto Compare](https://min-api.cryptocompare.com/): The world's leading digital asset data company, providing services that cater to corporate, government and retail clients
* []():

Here are also some curate lists of real-time data sources:

* https://www.quora.com/Where-can-I-find-public-or-free-real-time-or-streaming-data-sources
* https://blog.k2datascience.com/real-time-data-sources-for-data-engineering-projects-9bcff65c8468
* https://github.com/ColinEberhardt/awesome-public-streaming-datasets

## Others

* [Open Weather Map](https://openweathermap.org/api): Fast and easy-to-work weather APIs for free.
* [Meetup API](https://www.meetup.com/es-ES/meetup_api/): The Meetup API provides simple RESTful HTTP and streaming interfaces for exploring and interacting Meetup platform from your own apps.
* [IEX Cloud](https://iexcloud.io/docs/api/): Platform that makes financial data and services accessible to everyone.
* Open Data:
  * [Ajuntament de Valencia](https://www.valencia.es/dadesobertes/va/data/)
  * [Gobierno de España](https://datos.gob.es/)
  * [European Union](https://data.europa.eu/en)