package com.clay.graphstorage.converter;

import java.util.List;
import java.util.Map;

import com.clay.graphstorage.entities.Graph;

/**
 * All implementing classes specify how to load the graph. 
 * */
public interface GraphParser {

    /**
     * Loads the graph.
     * @param queryParams implementation specific parameters for output.
     * @return graph The loaded {@link Graph}.
     * */
  public Graph loadGraph(Map<String, String> queryParams);

  /**
   * @param inputParams Implementation based initialization properties.
   * */
  public void setGraphParserProperties(Map<String, String> inputParams);

  /**
   * @return {@link List} of all graph ids.
   * */
  public List<String> getAllGraphIds();

};

