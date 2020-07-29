package com.clay.graphstorage.entities;

import java.util.HashMap;
import java.util.Map;
import lombok.ToString;

/**
 * Graph entity class that maintains all nodes and edges, along with special properties, root nodes, and auto assigns id to each node.
 * */
@ToString
public class Graph {

  private Node rootNode;

  private Map<Long, Node> idWiseMap = new HashMap<Long, Node>();

  /**
   * @param rootNode The first node of the graph from which traversal and id assignment should start
   * */
  public void setRootNode(Node rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * @return rootNode The first node of the graph from which traversal and id assignment should start
   * */
  public Node getRootNode() {
	  return rootNode;
  }

  /**
   *  @return a {@link Map} representating all nodes with their ids as keys
   * */
  public Map<Long,Node> getIdWiseMap() {
	  return idWiseMap;
  }

  /**
   *  @param idWiseMap  {@link Map} representating all nodes with their ids as keys
   * */
  public void setIdWiseMap(Map<Long,Node> idWiseMap) {
	  this.idWiseMap = idWiseMap;
  }

  /**
   * Add a node to graph.
   * @param node the node to be added in graph
   * */
  public void addNode(Node node) {
	  idWiseMap.put(node.getId(), node);
  }

  private Long uniqueId = 0L;

  /**
   * Gets a unique id for assigning to a new node in graph.
   * */
  public Long getUniqueNodeId() {
      return ++uniqueId;
  }
};

