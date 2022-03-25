package com.craneos.sgv.integration.model.spring.stepable;

import com.craneos.sgv.integration.model.app.Step;

public class Chain extends Step {

    private String inputChannel;
    private String outputChannel;
    private boolean autoStartup;

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

    public boolean isAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

}
