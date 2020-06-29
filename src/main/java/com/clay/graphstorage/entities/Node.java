package com.clay.graphstorage.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.function.UnaryOperator;
import lombok.ToString;


@ToString
public class Node {

  public static final Map<String, Class<? extends SpecialProperty<?>>> specialProperties = new HashMap<>();


  public Map<String, NodeProperty<?> > properties = new HashMap<>();
  
  public Long id;

  @ToString.Exclude
  private Node comesFrom;

  private List<Node> connectsTo = new ArrayList<>();

  @ToString.Exclude
  private Graph graph;

  static {
    Node.specialProperties.put("id", IdSpecialProperty.class);
  }

  public Node(Graph graph) {
	  this(graph, null, null);
  }

  public Node(Graph graph, Long id) {
	  this(graph, null, id);
  }

  public List<Node> getConnectsToList() {
    return connectsTo;
  }

  public void setConnectsToNodes(List<Node> connectsToList) {
	  this.connectsTo = connectsToList;
  }

  public void setConnectsToNodesAndUpdateComesFromInEach(List<Node> connectsToList, Graph graph) {
      this.connectsTo = connectsToList;
      this.connectsTo.stream().filter(k -> k.comesFrom ==  null).forEach(connectsToNode -> {
          if(connectsToNode.id == 0L) {
              connectsToNode.addProperty("id", graph.getUniqueNodeId(), Long.class);
          }
          connectsToNode.comesFrom = this;
      });
  }
  
  public Node(Graph graph, Node comesFrom, Long id) {
    this.comesFrom = comesFrom;
    if(id == null && comesFrom != null && comesFrom.getId() != null) {
	    id = graph.getUniqueNodeId();
    } else if(id == null && comesFrom == null) {
	    id = 0L;
    }
    addProperty("id", id, Long.class);
    assert this.id != null;
    this.graph = graph;
    graph.addNode(this);
  }

  public Node comesFromWhere() {
    return comesFrom;
  }

  public Long getId() {
    return id;
  }

  public <S> void addProperty(String name, S value, Class<S> valueType) {

    NodeProperty<?> nodeProperty = null;
    if(specialProperties.containsKey(name)) {
      try {
        nodeProperty = specialProperties.get(name).getDeclaredConstructor(Node.class, valueType).newInstance(this, value);
	SpecialProperty<?> specialProperty = (SpecialProperty<?>) nodeProperty;
	specialProperty.executeChanges(graph);
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      nodeProperty = new NodeProperty<S>(this, value);
    }
    properties.put(name, nodeProperty);
  }
  
  public Map<String, NodeProperty<?> > getProperties() {
	  return properties;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Node)) {
      return false;
    } else {
      return ((Node) obj).getId().equals(this.getId());
    }
  }

  @Override
  public int hashCode() {
    return id.intValue();
  }

  public <T> T convert(Function<Node, T> nodeConverter, BiConsumer<T, List<T> > connectsToCombiner,
          BiConsumer<T, Map.Entry<String, Object> > propertiesCombiner) {

      T convertedNode = nodeConverter.apply(this);

      Map<String, Object> propertyPairs = properties.keySet().stream().collect(Collectors.toMap(key -> key, key -> properties.get(key).getValue()));

      List<T> connectsToConverted = connectsTo.stream().map(node -> node.convert(nodeConverter,  connectsToCombiner, propertiesCombiner)).collect(Collectors.toList());
      connectsToCombiner.accept(convertedNode, connectsToConverted);
      propertyPairs.entrySet().forEach(pair -> {
          propertiesCombiner.accept(convertedNode, pair);
      });
      return convertedNode;
  }

  public static <T> Node createFrom(Function<T, Node> nodeConverter, Function<T, List<T>> connectsToProvider, Function<T, Map<String, Object> > propertiesProvider, T t) {

     Node node = nodeConverter.apply(t); 
     List<T> connectsToEntityList = connectsToProvider.apply(t);
     propertiesProvider.apply(t).entrySet().forEach(entry -> node.addProperty(entry.getKey(), entry.getValue(), Object.class));
     node.setConnectsToNodesAndUpdateComesFromInEach(connectsToEntityList.stream().map(connectsToEntity -> createFrom(nodeConverter, connectsToProvider, propertiesProvider, connectsToEntity))
         .collect(Collectors.toList()), node.graph);
     return node;
  }
};

