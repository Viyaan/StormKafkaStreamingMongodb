package com.kafka.streaming.storm.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Viyaan
 *
 */
public class PropertiesLoader {

	   Properties prop;

		public PropertiesLoader() throws Exception{
			prop = new Properties();
	        InputStream is = PropertiesLoader.class.getClassLoader().getResourceAsStream("mongo-kafka.properties");
	        prop.load(is);
		}

		public String getString(String key){
			return prop.getProperty(key);
		}


}
