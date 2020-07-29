package com.clay.graphstorage.entities;

/**
 * This {@link SpecialProperty} can be used to auto assign id to nodes.
 * */
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

