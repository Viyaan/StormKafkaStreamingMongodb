# StormKafkaStreamingMongoDBConnector

Application recieves live streaming data from kafka and stores in MongoDB.

## Getting Started

KafkaSpoutTopology has the below components

storm.kafka.KafkaSpout - > Storm API to recieve messages from Kafka and emits to WordSpitBolt

HdfsBolt -->  Writes the stream into HDFS



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

## HDFSProperties


bolt.hdfs.field.delimiter=   Will write pipe("|")-delimited files to the HDFS.

bolt.hdfs.batch.size = This size denotes After it reaches the specified tuples it will sync filesystem.

bolt.hdfs.file.rotation.size.in.mb  = It will rotate files when they reach the megabytes in size.

bolt.hdfs.file.rotation.time.min= It will rotate files when they reach the time.

bolt.hdfs.wip.file.path= Destination path in hdfs.

bolt.hdfs.finished.file.path = Destination where files are moved after rotation size or time limit is reached.

bolt.hdfs.file.system.url= Hadoop File System URL.

## Running the tests





## Dependencies


	   <dependencies>
      <dependency>
         <groupId>org.apache.hadoop</groupId>
         <artifactId>hadoop-client</artifactId>
         <version>2.2.0</version>
         <exclusions>
            <exclusion>
               <groupId>org.slf4j</groupId>
               <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
         </exclusions>
      </dependency>
      <dependency>
         <groupId>org.apache.hadoop</groupId>
         <artifactId>hadoop-hdfs</artifactId>
         <version>2.2.0</version>
         <exclusions>
            <exclusion>
               <groupId>org.slf4j</groupId>
               <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
         </exclusions>
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
* [Hadoop](http://hadoop.apache.org/) - Hadoop Distributed File System (HDFS™): A distributed file system that provides high-throughput access to application data. 

## Contributing


## Versioning



## Authors

* **Viyaan Jhiingade** - *Initial work* - [Viyaan](https://github.com/Viyaan)



## License



## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc



