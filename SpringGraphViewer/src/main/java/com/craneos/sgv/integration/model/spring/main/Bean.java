package com.craneos.sgv.integration.model.spring.main;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class Bean extends SpringBaseItem {

    private String parent;
    private String fullClass;
    private String className;

    public Bean(String id, Path file){
        super(id, file, IntegrationType.BEAN);
        this.parent = null;
        this.fullClass = null;
        this.className = null;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getFullClass() {
        return fullClass;
    }

    public void setFullClass(String fullClass) {
        this.fullClass = fullClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
