package com.craneos.sgv.integration.model.spring.tags.stepable;

public class PayloadTypeRouter {
    private String id;
    private String inputChannel;
    private String defaultOutputChannel;

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
}
