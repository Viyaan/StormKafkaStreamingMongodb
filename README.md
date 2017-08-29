# StormKafkaStreamingMongoDBConnector

Application recieves live streaming data from kafka and stores in MongoDB.

## Getting Started

KafkaSpoutTopology has the below components

storm.kafka.KafkaSpout - > Storm API to recieve messages from Kafka and emits to WordSpitBolt

MongoInsertBolt -->  Writes the stream into Mongo



### Prerequisites

Install and Run Zookeeper and Kafka
Create Topic

### Installing


Start Zookeeper:
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

Start Kafka:
.\bin\windows\kafka-server-start.bat .\config\server.properties


Create topic
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic twitter-topic


End with an example of getting some data out of the system or using it for a little demo

## mongo db Properties


mongo.url=mongodb://localhost:27017/<databasename>.

collection.name=<collection_name>.

## Running the tests





## Dependencies


	   <dependencies>
      
		<dependency>
		    <groupId>org.apache.storm</groupId>
		    <artifactId>storm-mongodb</artifactId>
		    <version>1.1.0</version>
		</dependency>
     
      <dependency>
         <groupId>org.apache.storm</groupId>
         <artifactId>storm-core</artifactId>
         <version>1.1.1</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.apache.storm/storm-hdfs -->
      <dependency>
         <groupId>org.apache.storm</groupId>
         <artifactId>storm-hdfs</artifactId>
         <version>1.1.1</version>
         <scope>test</scope>
      </dependency>
      <dependency>
         <groupId>org.apache.storm</groupId>
         <artifactId>storm-kafka</artifactId>
         <version>1.1.1</version>
      </dependency>
      <dependency>
         <groupId>org.apache.storm</groupId>
         <artifactId>storm-kafka-client</artifactId>
         <version>1.1.1</version>
      </dependency>

      <dependency>
         <groupId>org.apache.kafka</groupId>
         <artifactId>kafka_2.9.2</artifactId>
         <version>0.8.1.1</version>
         <exclusions>
            <exclusion>
               <groupId>org.apache.zookeeper</groupId>
               <artifactId>zookeeper</artifactId>
            </exclusion>
            <exclusion>
               <groupId>log4j</groupId>
               <artifactId>log4j</artifactId>
            </exclusion>
         </exclusions>
      </dependency>

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Storm](http://storm.apache.org/) - Apache Storm is a free and open source distributed realtime computation system.
* [Mongo](https://www.mongodb.com/) - MongoDB is a document database with the scalability and flexibility that you want with the querying and indexing that you need.

## Contributing


## Versioning



## Authors

* **Viyaan Jhiingade** - *Initial work* - [Viyaan](https://github.com/Viyaan)



## License



## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc



