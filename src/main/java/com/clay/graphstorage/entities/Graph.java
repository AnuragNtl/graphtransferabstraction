package com.clay.graphstorage.entities;

import java.util.HashMap;
import java.util.Map;
import lombok.ToString;

@ToString
public class Graph {

  private Node rootNode;

  private Map<Long, Node> idWiseMap = new HashMap<Long, Node>();

  public void setRootNode(Node rootNode) {
    this.rootNode = rootNode;
  }

  public Node getRootNode() {
	  return rootNode;
  }

  public Map<Long,Node> getIdWiseMap() {
	  return idWiseMap;
  }

  public void setIdWiseMap(Map<Long,Node> idWiseMap) {
	  this.idWiseMap = idWiseMap;
  }

  public void addNode(Node node) {
	  idWiseMap.put(node.getId(), node);
  }

  private Long uniqueId = 0L;

  public Long getUniqueNodeId() {
      return ++uniqueId;
  }
};

