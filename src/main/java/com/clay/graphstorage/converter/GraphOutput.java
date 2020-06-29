package com.clay.graphstorage.converter;

import java.util.Map;
import com.clay.graphstorage.entities.Graph;

public interface GraphOutput {

  public void outputGraph(Graph graph, Map<String, String> outputParams);

  public void setGraphOutputProperties(Map<String, String> outputParams);
};

