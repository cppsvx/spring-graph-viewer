package com.craneos.sgv.integration.model.spring;

import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class SpringBaseItem {

    protected String id;
    protected IntegrationType item;
    protected String absolutePath;
    protected String filename;

    public SpringBaseItem() {
        this.id = null;
        this.item = null;
        this.absolutePath = null;
        this.filename = null;
    }

    public SpringBaseItem(String id, Path file, IntegrationType type) {
        this.id = id;
        this.item = type;
        this.absolutePath = file.toString();
        this.filename = file.getFileName().toString();
    }
/*
    public SpringBaseItem(String id, IntegrationType type) {
        this.id = id;
        this.item = type;
        this.absolutePath = null;
        this.filename = null;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
