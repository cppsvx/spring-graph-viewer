package com.craneos.sgv.integration.model.spring.subs;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class Bridge extends SpringBaseItem {

    private boolean autoStartup;
    private String inputChannel;
    private String outputChannel;

    public Bridge(String id, Path file){
        super(id, file, IntegrationType.BRIDGE);
        this.autoStartup = false;
        this.inputChannel = null;
        this.outputChannel = null;
    }

    public boolean isAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
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
