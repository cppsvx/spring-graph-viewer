package com.craneos.sgv.integration.model.spring.tags.stepable;

public class HeaderEnricher implements IStep {
    private String id;
	private String inputChannel;
	private String outputChannel;

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

	public String getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(String outputChannel) {
		this.outputChannel = outputChannel;
	}
}
