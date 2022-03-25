package com.craneos.sgv.integration.model.spring.stepable;

import com.craneos.sgv.integration.model.app.Step;

public class PayloadTypeRouter extends Step {

    private String inputChannel;
    private String defaultOutputChannel;

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
