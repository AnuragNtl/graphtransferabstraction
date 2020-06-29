package com.clay.graphstorage.entities;

public class IdSpecialProperty extends SpecialProperty<Long> {


	public IdSpecialProperty(Node node, Object value) {
		super(node, Long.valueOf(value.toString()));
	}

  public IdSpecialProperty(Node node, Long value) {
    super(node, value);
  }

  public void executeChanges(Graph graph) {
    node.id = value;
  }
};

