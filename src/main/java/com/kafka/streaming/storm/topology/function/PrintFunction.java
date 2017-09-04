package com.kafka.streaming.storm.topology.function;

import java.util.Map;

import org.apache.storm.trident.operation.Function;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PrintFunction extends BaseFunction {

    private static final Logger LOG = LoggerFactory.getLogger(PrintFunction.class);

    private static final Random RANDOM = new Random();

    
    public void execute(TridentTuple tuple, TridentCollector tridentCollector) {
       // if(RANDOM.nextInt(1000) > 995) {
            LOG.info(tuple.toString());
       // }
    }
}