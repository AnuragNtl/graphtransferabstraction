package com.clay.graphstorage.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class GraphOutputConfigReader {

	public static GraphOutputConfig readFromConfigFile(String fileName) throws IOException {
		GraphOutputConfig graphOutputConfig = new GraphOutputConfig();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));	
		Map<String, String> config = new HashMap<String, String>();
		String line = null;
		String  graphOutputClassPath = reader.readLine();
		graphOutputConfig.setGraphOutputClassPath(graphOutputClassPath);
		while((line = reader.readLine()) != null) {
			String spec[] = line.split("=");
			if(spec.length < 2) {
				continue;
			} else {
				config.put(spec[0].trim(), spec[1].trim());
			}
		}
		graphOutputConfig.setProperties(config);
		reader.close();
		return graphOutputConfig;
	}
};

