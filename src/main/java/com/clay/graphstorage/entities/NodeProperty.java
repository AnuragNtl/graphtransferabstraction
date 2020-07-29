package com.clay.graphstorage.entities;

import lombok.ToString;

/**
 * Each graph has certain nodes, and each node can have properties holding values of their own.
 * */
@ToString
public class NodeProperty<T> {
  
  protected T value;

  @ToString.Exclude
  protected Node node;

  public NodeProperty(Node node, T value) {
    this.node = node;
    this.value = value;
  }

  public T getValue()  {
    return value;
  }

  public void setValue(T value) {
   this.value = value; 
  } 
  
};

