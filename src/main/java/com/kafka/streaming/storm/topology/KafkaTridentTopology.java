package com.kafka.streaming.storm.topology;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.spout.KafkaSpoutConfig.FirstPollOffsetStrategy;
import org.apache.storm.kafka.spout.trident.KafkaTridentSpoutOpaque;
import org.apache.storm.mongodb.common.mapper.MongoMapper;
import org.apache.storm.mongodb.common.mapper.SimpleMongoMapper;
import org.apache.storm.mongodb.trident.state.MongoState;
import org.apache.storm.mongodb.trident.state.MongoState.Options;
import org.apache.storm.mongodb.trident.state.MongoStateFactory;
import org.apache.storm.mongodb.trident.state.MongoStateUpdater;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentState;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.state.StateFactory;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kafka.streaming.storm.topology.function.PrintFunction;
import com.kafka.streaming.storm.utils.ConsumerEnum;
import com.kafka.streaming.storm.utils.PropertiesLoader;


/**
 * @author Viyaan
 */
public class KafkaTridentTopology {

    private static final int PARALLELISM = 1;
    

    public static final Logger LOG = LoggerFactory.getLogger(KafkaTridentTopology.class);

    public static StormTopology buildTopology() throws Exception {
    	PropertiesLoader loader = new PropertiesLoader();
        TridentTopology topology=new TridentTopology();
        Stream stream =topology.newStream(KafkaTridentTopology.class.getName(),
        	    new KafkaTridentSpoutOpaque(configureKafkaSpout(loader)))
        	      .parallelismHint(PARALLELISM);
        	      
        StateFactory factory = new MongoStateFactory(configureMongo(loader));
        Fields fields = new Fields("value");
        stream.partitionPersist(factory, fields,  new MongoStateUpdater(), new Fields());
        TridentState state = topology.newStaticState(factory);
        

        stream.each(new Fields("value"), new PrintFunction(), new Fields());
        return topology.build();
    }
    
    private static Options configureMongo(PropertiesLoader loader) {
		// TODO Auto-generated method stub
    	MongoMapper mapper = new SimpleMongoMapper().withFields("value");
    	MongoState.Options options = new MongoState.Options()
                .withUrl(loader.getString(ConsumerEnum.MONGO_URL.getValue()))
                .withCollectionName(loader.getString(ConsumerEnum.COLLECTION_NAME.getValue()))
                .withMapper(mapper);
    	return options;
	}

	public static Config configureStorm() {
    	Config config = new Config();
        config.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS, 2000);
        return config;
    }
    
    public static KafkaSpoutConfig<String, String> configureKafkaSpout(PropertiesLoader loader) {
        
        KafkaSpoutConfig<String, String> kafkaConf = KafkaSpoutConfig
        		.builder(loader.getString(ConsumerEnum.BROKER_LIST.getValue()),loader.getString(ConsumerEnum.KAFKA_TOPIC.getValue()))
        		.setProp(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
        		.setGroupId(loader.getString(ConsumerEnum.CONSUMER_GROUP.getValue()))
        		.setFirstPollOffsetStrategy(FirstPollOffsetStrategy.UNCOMMITTED_EARLIEST)
        		.setEmitNullTuples(false)
        		.build();
        
        return kafkaConf;
    }


    public static void main(String[] args) throws Exception {

        StormTopology stormTopology = buildTopology();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Kafka-Storm-Mongodb-Topology", configureStorm(), stormTopology);
    }
}
