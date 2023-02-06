package com.craneos.spiflow.spring.types;

public class Import extends BaseItem {

    private String resource;
    private String pathResource;

    public Import(){
        super();
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
