package com.craneos.sgv.integration.model.spring.defs;

import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class Import extends BaseItem {

    private String resource;
    private String pathResource;

    public Import(String id, XmlFile file){
        super(id, file, IntegrationType.IMPORT);
        this.resource = null;
        this.pathResource = null;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


    public String getPathResource() {
        return pathResource;
    }

    public void setPathResource(String pathResource) {
        this.pathResource = pathResource;
    }

}
