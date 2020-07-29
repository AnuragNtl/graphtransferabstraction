package com.clay.graphstorage.loader;
import java.util.Map;

/**
 * Specifies the class path of {@link com.clay.graphstorage.converter.GraphOutput}
 * and also configures it with parameters and properties depending on the implementation of {@link com.clay.graphstorage.converter.GraphOutput}.
 * */
public class GraphOutputConfig {

	


	private String graphOutputClassPath;

	private Map<String, String> properties;


    /**
     * @return class path of {@link com.clay.graphstorage.converter.GraphOutput} 
     * */
	public String getGraphOutputClassPath() {
		return graphOutputClassPath;
	}

    /**
     * @param graphOutputClassPath class path of {@link com.clay.graphstorage.converter.GraphOutput}
     * */
	public void setGraphOutputClassPath(String graphOutputClassPath) {
		this.graphOutputClassPath = graphOutputClassPath;
	}

    /**
     * @return Configuration properties used by {@link com.clay.graphstorage.converter.GraphOutput} depending upon implementation
     * */
	public Map<String,String> getProperties() {
		return properties;
	}

    /** 
     * @param properties Configuration properties used by {@link com.clay.graphstorage.converter.GraphOutput} depending upon implementation
     * */
	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
	}
};

