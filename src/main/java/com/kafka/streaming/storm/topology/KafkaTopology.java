package com.kafka.streaming.storm.topology;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.KeyValueSchemeAsMultiScheme;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringKeyValueScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.mongodb.bolt.MongoInsertBolt;
import org.apache.storm.mongodb.common.mapper.MongoMapper;
import org.apache.storm.mongodb.common.mapper.SimpleMongoMapper;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kafka.streaming.storm.utils.ConsumerEnum;
import com.kafka.streaming.storm.utils.PropertiesLoader;

/**
 * @author Viyaan
 */
public class KafkaTopology {

    private static final int PARALLELISM = 1;
    

    public static final Logger LOG = LoggerFactory.getLogger(KafkaTopology.class);

    public static StormTopology buildTopology() throws Exception {
    	PropertiesLoader loader = new PropertiesLoader();
        TopologyBuilder builder=new TopologyBuilder();
        builder.setSpout(KafkaSpout.class.getName(), new KafkaSpout(configureKafkaSpout(loader)), PARALLELISM);
        MongoInsertBolt mongoInsertBolt =configureMongoDBBolt(loader);
        builder.setBolt(MongoInsertBolt.class.getName(), mongoInsertBolt).globalGrouping(KafkaSpout.class.getName());
        return builder.createTopology();
    }
    
    private static MongoInsertBolt configureMongoDBBolt(PropertiesLoader loader) {
		// TODO Auto-generated method stub
    	MongoMapper mapper = new SimpleMongoMapper().withFields("str");
		return new MongoInsertBolt(loader.getString(ConsumerEnum.MONGO_URL.getValue()),loader.getString(ConsumerEnum.COLLECTION_NAME.getValue()),mapper);
	}

	public static Config configureStorm() {
    	Config config = new Config();
        config.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS, 2000);
        return config;
    }
    
    public static SpoutConfig configureKafkaSpout(PropertiesLoader loader) {
    	ZkHosts zkHosts=new ZkHosts(loader.getString(ConsumerEnum.ZOOKEEPER.getValue()));
        SpoutConfig kafkaConfig=new SpoutConfig(zkHosts, loader.getString(ConsumerEnum.KAFKA_TOPIC.getValue()), loader.getString(ConsumerEnum.ZK_ROOT.getValue()), loader.getString(ConsumerEnum.CONSUMER_GROUP.getValue()));
        kafkaConfig.scheme = new KeyValueSchemeAsMultiScheme(new StringKeyValueScheme());
        return kafkaConfig;
    }


    public static void main(String[] args) throws Exception {

        StormTopology stormTopology = buildTopology();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Kafka-Storm-Mongodb-Topology", configureStorm(), stormTopology);
    }
}
