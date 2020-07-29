package com.clay.graphstorage.converter;

import java.util.Map;
import com.clay.graphstorage.entities.Graph;

/**
 * All implementing classes specify how to save the graph. 
 * */
public interface GraphOutput {

    /**
     * Saves the graph.
     * @param graph The {@link Graph} to save.
     * @param outputParams implementation specific parameters for output.
     * */
  public void outputGraph(Graph graph, Map<String, String> outputParams);

  /**
   * @param outputParams Implementation based initialization properties.
   * */
  public void setGraphOutputProperties(Map<String, String> outputParams);
};

