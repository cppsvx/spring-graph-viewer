package com.craneos.spiflow.spring.types;

import com.craneos.spiflow.annotations.Required;

public class WireTap {

    private String id;
    @Required private String channel;
    private String selector;
    private String selectorExpression;
    private String timeout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getSelectorExpression() {
        return selectorExpression;
    }

    public void setSelectorExpression(String selectorExpression) {
        this.selectorExpression = selectorExpression;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
