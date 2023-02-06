package com.craneos.spiflow.spring.types;

public class SubscribeChannel extends BaseItem {

    private String id;

    public SubscribeChannel(){
        super();
        this.id = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
