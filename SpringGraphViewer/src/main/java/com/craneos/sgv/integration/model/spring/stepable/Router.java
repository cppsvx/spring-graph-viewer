package com.craneos.sgv.integration.model.spring.stepable;

import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.model.spring.types.Mapping;

import java.util.LinkedList;

public class Router extends Step {

	private String inputChannel;
	private String defaultOutputChannel;
	private String expression;
	private LinkedList<Mapping> mappings;

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
