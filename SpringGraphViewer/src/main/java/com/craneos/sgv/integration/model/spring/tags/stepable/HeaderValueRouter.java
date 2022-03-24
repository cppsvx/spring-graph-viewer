package com.craneos.sgv.integration.model.spring.tags.stepable;

import com.craneos.sgv.integration.model.spring.tags.stepable.IStep;

public class HeaderValueRouter implements IStep {
    private String id;
    private String headerName;
    private String inputChannel;
    private String defaultOutputChannel;
    private boolean applySequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
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

    public boolean isApplySequence() {
        return applySequence;
    }

    public void setApplySequence(boolean applySequence) {
        this.applySequence = applySequence;
    }
}
