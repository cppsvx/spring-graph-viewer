package com.craneos.sgv.integration.model.spring.subs;

import com.craneos.sgv.integration.model.annotations.Required;
import com.craneos.sgv.integration.model.spring.SpringBaseItem;

public class WireTap extends SpringBaseItem {

    @Required private String channel;
    private String selector;
    private String selectorExpression;
    private String timeout;

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
