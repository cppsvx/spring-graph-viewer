package com.craneos.sgv.integration.model.spring.defs;

import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class  BaseItem {

    protected String id;
    protected XmlFile xmlFile;
    protected IntegrationType item;

    public BaseItem() {
        this.id = null;
        this.item = null;
        this.xmlFile = null;
    }

    public BaseItem(String id, XmlFile xmlFile, IntegrationType type) {
        this.id = id;
        this.item = type;
        this.xmlFile = xmlFile;
    }

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

    public XmlFile getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    public String getFilename(){
        if (xmlFile!=null) {
            return xmlFile.getFilename();
        }
        return null;
    }
    public void setFilename(String filename){
        xmlFile.setFilename(filename);
    }
}
