package com.kafka.streaming.storm.utils;

/**
 * Created by viyaan
 */
public enum ConsumerEnum {





    BROKER_LIST("metadata.broker.list"),
    SERIALIZER("serializer.class"),
    KAFKA_TOPIC("kafka.topic"),
    ZOOKEEPER("zookeeper.host"),
    CONSUMER_GROUP("consumer.groupid"),
	ZK_ROOT("zk.root"),

	MONGO_URL("mongo.url"),
	COLLECTION_NAME("collection.name");

    private String value;

    ConsumerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
