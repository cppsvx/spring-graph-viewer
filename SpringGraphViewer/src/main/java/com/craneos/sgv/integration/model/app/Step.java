package com.craneos.sgv.integration.model.app;

import com.craneos.sgv.integration.model.spring.defs.BaseItem;
import com.craneos.sgv.integration.model.spring.stepable.IStep;

import java.util.ArrayList;
import java.util.List;

public class Step extends BaseItem implements IStep {

    private String inputChannel;
    private String outputChannel;
    private String discardChannel;
    private List<String> channels;

    public Step(){
        super();
        this.inputChannel = null;
        this.outputChannel = null;
        this.discardChannel = null;
        this.channels = null;
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

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    public void addChannel(String channel) {
        if (channels == null) {
            channels = new ArrayList<>();
        }
        this.channels.add(channel);
    }

    public static class Builder{
        private String id;
        private XmlFile xmlFile;
        private String inputChannel;
        private String outputChannel;
        private String discardChannel;
        private List<String> channels;

        public Builder(){
            this.id = null;
            this.xmlFile = null;
            this.inputChannel = null;
            this.outputChannel = null;
            this.discardChannel = null;
            this.channels = null;
        }

        public static Step.Builder create(){
            return new Step.Builder();
        }

        public Step.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Step.Builder setXmlFile(XmlFile xmlFile) {
            this.xmlFile = xmlFile;
            return this;
        }

        public Step.Builder setInputChannel(String inputChannel) {
            this.inputChannel = inputChannel;
            return this;
        }

        public Step.Builder setOutputChannel(String outputChannel) {
            this.outputChannel = outputChannel;
            return this;
        }

        public Step.Builder setDiscardChannel(String discardChannel) {
            this.discardChannel = discardChannel;
            return this;
        }

        public Step.Builder setChannels(List<String> channels) {
            this.channels = channels;
            return this;
        }

        public Step build() {
            Step step = new Step();
            step.setId(id);
            step.setXmlFile(xmlFile);
            step.setInputChannel(inputChannel);
            step.setOutputChannel(outputChannel);
            step.setDiscardChannel(discardChannel);
            //step.setChannels(channels);
            return step;
        }
    }

}
