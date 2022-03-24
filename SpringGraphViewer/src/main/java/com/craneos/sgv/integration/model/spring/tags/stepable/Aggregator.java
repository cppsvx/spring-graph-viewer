package com.craneos.sgv.integration.model.spring.tags.stepable;

public class Aggregator implements IStep{
    private String id;
    private String inputChannel;
    private String outputChannel;
    private String groupTimeout;
    private String scheduler;
    private boolean sendPartialResultOnExpiry;
    private String messageStore;
    private String expireGroupsUponCompletion;

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

    public String getGroupTimeout() {
        return groupTimeout;
    }

    public void setGroupTimeout(String groupTimeout) {
        this.groupTimeout = groupTimeout;
    }

    public String getScheduler() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public boolean isSendPartialResultOnExpiry() {
        return sendPartialResultOnExpiry;
    }

    public void setSendPartialResultOnExpiry(boolean sendPartialResultOnExpiry) {
        this.sendPartialResultOnExpiry = sendPartialResultOnExpiry;
    }

    public String getMessageStore() {
        return messageStore;
    }

    public void setMessageStore(String messageStore) {
        this.messageStore = messageStore;
    }

    public String getExpireGroupsUponCompletion() {
        return expireGroupsUponCompletion;
    }

    public void setExpireGroupsUponCompletion(String expireGroupsUponCompletion) {
        this.expireGroupsUponCompletion = expireGroupsUponCompletion;
    }
}
