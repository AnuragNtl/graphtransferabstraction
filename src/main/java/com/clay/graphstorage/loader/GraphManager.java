package com.clay.graphstorage.loader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.clay.graphstorage.converter.GraphOutput;
import com.clay.graphstorage.converter.GraphParser;

public class GraphManager {

	private static GraphManager graphManager;
	
	private GraphOutput graphOutput;

	private GraphParser graphParser;

	private GraphManager(GraphOutput graphOutput, GraphParser graphParser) {
		this.graphOutput = graphOutput;
		this.graphParser = graphParser;
	}

	public static GraphManager getConfiguredLoader() {
		if(graphManager == null) {
			try {
				GraphOutput graphOutput = getConfiguredGraphOutput();
				GraphParser graphParser = getConfiguredGraphParser();
				graphManager = new GraphManager(graphOutput, graphParser);
			} catch(Throwable t) {
				throw new GraphManagerInitializationException("Cannot initialize graph manager", t);
			}
		}
		return graphManager;
	}
 

    public static GraphManager getConfiguredLoader(GraphSourceConfig graphSourceConfig, GraphOutputConfig graphOutputConfig) {
		if(graphManager == null) {
			try {
				GraphOutput graphOutput = getConfiguredGraphOutput(graphOutputConfig);
				GraphParser graphParser = getConfiguredGraphParser(graphSourceConfig);
				graphManager = new GraphManager(graphOutput, graphParser);
			} catch(Throwable t) {
				throw new GraphManagerInitializationException("Cannot initialize graph manager", t);
			}
		}
		return graphManager;
    }
    
	private static String getParseConfigPath() {
		String parseConfigFile = System.getProperty("clay.graphParseConfig");
		if(parseConfigFile == null) {
			parseConfigFile = "graphParseConfig.properties";
		}
		return parseConfigFile;
	}


	private static String getOutputConfigPath() {
		String outputConfigFile = System.getProperty("clay.graphOutputConfig");
		if(outputConfigFile == null) {
			outputConfigFile = "graphOutputConfig.properties";
		}
		return outputConfigFile;
	}

    private static GraphOutput getConfiguredGraphOutput(GraphOutputConfig graphOutputConfig) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class<? extends GraphOutput> graphOutputClazz = Class.forName(graphOutputConfig.getGraphOutputClassPath()).asSubclass(GraphOutput.class);
		GraphOutput graphOutput = graphOutputClazz.getDeclaredConstructor().newInstance();
		graphOutput.setGraphOutputProperties(graphOutputConfig.getProperties());
		return graphOutput;

    } 
	private static GraphOutput getConfiguredGraphOutput() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		GraphOutputConfig graphOutputConfig = GraphOutputConfigReader.readFromConfigFile(getOutputConfigPath());
        return getConfiguredGraphOutput(graphOutputConfig);
	}

	private static GraphParser getConfiguredGraphParser(GraphSourceConfig graphSourceConfig) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<? extends GraphParser> graphParserClazz = Class.forName(graphSourceConfig.getGraphParserClassPath()).asSubclass(GraphParser.class);
        GraphParser graphParser = graphParserClazz.getDeclaredConstructor().newInstance();
        graphParser.setGraphParserProperties(graphSourceConfig.getProperties());
        return graphParser;
    }

	private static GraphParser getConfiguredGraphParser() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		GraphSourceConfig graphSourceConfig = GraphSourceConfigReader.readFromConfigFile(getParseConfigPath());
        return getConfiguredGraphParser(graphSourceConfig);
	}

    public GraphOutput getGraphOutput() {
        return graphOutput;
    }

    public GraphParser getGraphParser() {
        return graphParser;
    }

};

