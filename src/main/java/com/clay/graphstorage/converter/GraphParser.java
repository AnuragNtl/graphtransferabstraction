package com.clay.graphstorage.converter;

import java.util.List;
import java.util.Map;

import com.clay.graphstorage.entities.Graph;

public interface GraphParser {

  public Graph loadGraph(Map<String, String> queryParams);

  public void setGraphParserProperties(Map<String, String> inputParams);

  public List<String> getAllGraphIds();

};

