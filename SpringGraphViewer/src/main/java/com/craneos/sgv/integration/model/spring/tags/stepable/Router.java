package com.craneos.sgv.integration.model.spring.tags.stepable;

import com.craneos.sgv.integration.model.spring.tags.commons.Mapping;
import com.craneos.sgv.integration.model.spring.tags.stepable.IStep;

import java.util.LinkedList;

public class Router implements IStep {
    private String id;
	private String inputChannel;
	private String defaultOutputChannel;
	private String expression;
	private LinkedList<Mapping> mappings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInputChannel() {
		return inputChannel;
	}

	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}

	public String getDefaultOutputChannel() {
		return defaultOutputChannel;
	}

	public void setDefaultOutputChannel(String defaultOutputChannel) {
		this.defaultOutputChannel = defaultOutputChannel;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public LinkedList<Mapping> getMappings() {
		return mappings;
	}

	public void setMappings(LinkedList<Mapping> mappings) {
		this.mappings = mappings;
	}
}
