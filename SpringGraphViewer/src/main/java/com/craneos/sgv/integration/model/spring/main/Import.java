package com.craneos.sgv.integration.model.spring.main;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class Import extends SpringBaseItem {

    private String resource;
    private String pathResource;

    public Import(String id, Path file){
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
