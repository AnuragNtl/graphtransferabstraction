package com.clay.graphstorage.loader;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Specifies the class path of {@link com.clay.graphstorage.converter.GraphParser}
 * and also configures it with parameters and properties depending on the implementation of {@link com.clay.graphstorage.converter.GraphParser}.
 * The configuration also specifies a "specialProperties" value in the {@link Map} representated by {@link #setProperties(Map)} and {@link #getProperties()}
 * */
public class GraphSourceConfig {

	


	private String graphParserClassPath;

	private Map<String, String> properties;

	private Map<String, String> specialProperties;

	private String SPECIAL_PROPERTIES_KEY = "specialProperties";

    /**
     * @return class path of {@link com.clay.graphstorage.converter.GraphParser} 
     * */
	public String getGraphParserClassPath() {
		return graphParserClassPath;
	}

    /**
     * @param graphParserClassPath class path of {@link com.clay.graphstorage.converter.GraphParser}
     * */
	public void setGraphParserClassPath(String graphParserClassPath) {
		this.graphParserClassPath = graphParserClassPath;
	}

    /**
     * @return Configuration properties used by {@link com.clay.graphstorage.converter.GraphParser} depending upon implementation
     * */
	public Map<String,String> getProperties() {
		return properties;
	}

    /** 
     * @param properties Configuration properties used by {@link com.clay.graphstorage.converter.GraphParser} depending upon implementation
     * */
	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
		initializeSpecialProperties();
	}

	private void initializeSpecialProperties() {
		if(properties.containsKey(SPECIAL_PROPERTIES_KEY)) {
			if(StringUtils.isBlank(properties.get(SPECIAL_PROPERTIES_KEY))) {
				return;
			}
			Arrays.asList(properties.get(SPECIAL_PROPERTIES_KEY).split(",")).stream().forEach(specialPropertySpec -> {
				specialPropertySpec = specialPropertySpec.trim();
				if(StringUtils.isBlank(specialPropertySpec)) {
					return;
				}
				String specialProperty[] = specialPropertySpec.split("=");
				if(specialProperty.length == 0) {
					return;
				}
				specialProperties.put(specialProperty[0], specialProperty[1]);
			});
		}
	}

    /**
     * @return all special property names specified by "specialProperties" key in format property1=value1,property2=value1
     * */
	public Map<String, String> getSpecialProperties() {
		return specialProperties;
	}
};

