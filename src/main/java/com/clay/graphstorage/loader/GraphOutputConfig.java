package com.clay.graphstorage.loader;
import java.util.Map;

public class GraphOutputConfig {

	


	private String graphOutputClassPath;

	private Map<String, String> properties;


	public String getGraphOutputClassPath() {
		return graphOutputClassPath;
	}

	public void setGraphOutputClassPath(String graphOutputClassPath) {
		this.graphOutputClassPath = graphOutputClassPath;
	}

	public Map<String,String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
	}
};

