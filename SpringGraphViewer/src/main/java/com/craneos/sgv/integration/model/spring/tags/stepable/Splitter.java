package com.craneos.sgv.integration.model.spring.tags.stepable;

public class Splitter implements IStep {
    private String id;
    private String method;
    private String inputChannel;
    private String outputChannel;
    private boolean applySequence;
    private String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public boolean isApplySequence() {
        return applySequence;
    }

    public void setApplySequence(boolean applySequence) {
        this.applySequence = applySequence;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
