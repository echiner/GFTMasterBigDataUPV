package com.gft.upv.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

import java.util.concurrent.TimeoutException;

import static org.apache.spark.sql.functions.from_json;

public class TickerProcessor {

    public static final String KAFKA_BROKER = "localhost:9092";
    public static final String SOURCE_TOPIC = "tickers";
    public static final String DESTINATION_TOPIC = "tickers_transformed";

    public static void main(String args[]) throws TimeoutException, StreamingQueryException {
        // Show environment info
        System.out.println("HADOOP_HOME: " + System.getenv("HADOOP_HOME"));
        System.out.println("PATH: " + System.getenv("PATH"));

        // Ticker schema definition
        StructType tickerSchema = new StructType()
                .add("type", "string")
                .add("sequence", "long")
                .add("product_id", "string")
                .add("price", "string")
                .add("open_24h", "string")
                .add("volume_24h", "string")
                .add("low_24h", "string")
                .add("high_24h", "string")
                .add("volume_30d", "string")
                .add("best_bid", "string")
                .add("best_ask", "string")
                .add("side", "string")
                .add("time", "string")
                .add("trade_id", "long")
                .add("last_size", "string");

        // Setup Spark
        SparkSession spark = SparkSession
                .builder()
                .appName("Ticker Processor")
                .config("spark.master", "local")
                .config("spark.eventLog.enabled", "false")
                .getOrCreate();


        // Setup Kafka reader
        // TODO: remove "startingOffsets" if you only want to receive new messages
        Dataset<Row> rawDF = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", KAFKA_BROKER)
                .option("subscribe", SOURCE_TOPIC)
                .option("startingOffsets", "earliest")
                .load();

        // Get only the value of the Kafka messages (it also has key, partition, offset...)
        Dataset<Row>  tickerStringDF = rawDF.selectExpr("CAST(value AS STRING)");

        // Convert the JSON String to the schema defined above
        Dataset<Row> tickerSchemaDF = tickerStringDF
                .select(from_json(tickerStringDF.col("value"), tickerSchema).as("ticker"))
                .select("ticker.*");

        // Refine the schema (floats are currently strings)
        Dataset<Row> tickerFixedSchemaDF = tickerSchemaDF
                .withColumn("price", tickerSchemaDF.col("price").cast("float"))
                .withColumn("open_24h", tickerSchemaDF.col("open_24h").cast("float"))
                .withColumn("volume_24h", tickerSchemaDF.col("volume_24h").cast("float"))
                .withColumn("low_24h", tickerSchemaDF.col("low_24h").cast("float"))
                .withColumn("high_24h", tickerSchemaDF.col("high_24h").cast("float"))
                .withColumn("volume_30d", tickerSchemaDF.col("volume_30d").cast("float"))
                .withColumn("best_bid", tickerSchemaDF.col("best_bid").cast("float"))
                .withColumn("best_ask", tickerSchemaDF.col("best_ask").cast("float"))
                .withColumn("last_size", tickerSchemaDF.col("last_size").cast("float"));


        // Print the schema (for reference)
        tickerFixedSchemaDF.printSchema();

        // Set the dataframe as a table (for querying)
        tickerFixedSchemaDF.createOrReplaceTempView("tickers");

        // TODO: Implement your logic here (in SQL format)
        String sql = "SELECT * FROM tickers WHERE product_id='ETH-EUR'";
        Dataset<Row> tickerQuery = spark.sql(sql);

        // Print the results via console
        StreamingQuery query = tickerQuery.writeStream()
                .outputMode("append")
                .format("console")
                .start();
        query.awaitTermination();

        // Send the results to Kafka
        // TODO: Uncomment to send data back to Kafka
//        tickerQuery.selectExpr("CAST(tickers.sequence AS STRING) AS key", "to_json(struct(tickers.*)) AS value")
//                .writeStream()
//                .format("kafka")
//                .outputMode("append")
//                .option("kafka.bootstrap.servers", KAFKA_BROKER)
//                .option("topic", DESTINATION_TOPIC)
//                .option("checkpointLocation", "/tmp")
//                .start()
//                .awaitTermination();
    }
}
