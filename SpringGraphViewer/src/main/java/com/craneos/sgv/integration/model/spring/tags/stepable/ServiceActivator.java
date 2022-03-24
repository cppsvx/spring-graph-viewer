package com.craneos.sgv.integration.model.spring.tags.stepable;

import com.craneos.sgv.integration.model.spring.tags.commons.IOChannels;

public class ServiceActivator extends IOChannels {
    private String id;
    private String ref;
    private String method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
