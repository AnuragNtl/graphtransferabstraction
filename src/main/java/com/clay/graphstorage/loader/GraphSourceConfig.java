package com.clay.graphstorage.loader;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class GraphSourceConfig {

	


	private String graphParserClassPath;

	private Map<String, String> properties;

	private Map<String, String> specialProperties;

	private String SPECIAL_PROPERTIES_KEY = "specialProperties";

	public String getGraphParserClassPath() {
		return graphParserClassPath;
	}

	public void setGraphParserClassPath(String graphParserClassPath) {
		this.graphParserClassPath = graphParserClassPath;
	}

	public Map<String,String> getProperties() {
		return properties;
	}

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
	public Map<String, String> getSpecialProperties() {
		return specialProperties;
	}
};

