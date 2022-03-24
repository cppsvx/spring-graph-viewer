package com.craneos.sgv.integration.model.app;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;

import java.util.ArrayList;
import java.util.List;

public class Step extends SpringBaseItem {

    private String id;
    private String filename;
    private String inputChannel;
    private String outputChannel;
    private String discardChannel;
    private List<String> nextChannels;

    public Step(){
        super();
        this.id = null;
        this.filename = null;
        this.inputChannel = null;
        this.outputChannel = null;
        this.discardChannel = null;
    }

    public Step(String id, String inputChannel, List<String> nextChannels) {
        this.id = id;
        this.filename = null;
        this.inputChannel = inputChannel;
        this.nextChannels = nextChannels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public List<String> getNextChannels() {
        return nextChannels;
    }

    public void setNextChannels(List<String> nextChannels) {
        this.nextChannels = nextChannels;
    }

    public void addNextChannel(String channel){
        if (nextChannels==null){
            nextChannels = new ArrayList<>();
        }
        nextChannels.add(channel);
    }

    @Override
    public String toString(){
        return inputChannel.toString();
    }
}
