package com.craneos.spiflow.spring.types;

public class Bean extends BaseItem {

    private String id;
    private String parentId;
    private String fullClass;
    private String className;

    public Bean(){
        super();
        this.id = null;
        this.parentId = null;
        this.fullClass = null;
        this.className = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
