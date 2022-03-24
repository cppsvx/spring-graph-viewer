package com.craneos.sgv.integration.model.spring.tags.commons;

import java.util.List;

public class IOChannels {
    protected String inputChannel;
    protected String outputChannel;
    protected String discardChannel;
    protected List<String> nextChannels;

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

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }

    public List<String> getNextChannels() {
        return nextChannels;
    }

    public void setNextChannels(List<String> nextChannels) {
        this.nextChannels = nextChannels;
    }
}
