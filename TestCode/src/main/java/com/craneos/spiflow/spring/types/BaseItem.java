package com.craneos.spiflow.spring.types;

import com.craneos.spiflow.spring.IntegrationType;

public class BaseItem {

    protected IntegrationType item;
    protected String absolutePath;
    protected String filename;

    public BaseItem() {
        this.item = IntegrationType.NONE;
        this.absolutePath = null;
        this.filename = null;
    }

    public IntegrationType getItem() {
        return item;
    }

    public void setItem(IntegrationType item) {
        this.item = item;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
