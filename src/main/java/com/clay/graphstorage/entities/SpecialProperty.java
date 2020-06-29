package com.clay.graphstorage.entities;

public abstract class SpecialProperty <T> extends NodeProperty<T> {


	public SpecialProperty(Node node, T value) {
		super(node, value);
	} 

	public abstract void executeChanges(Graph graph); 

	public boolean isAutoGenerated() {
		return false;
	}

};

