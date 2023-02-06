package com.craneos.spiflow.spring.types;

import java.util.List;

public class Interceptors extends BaseItem{

    private List<WireTap> wireTap;

    public Interceptors(){
        this.wireTap = null;
    }

    public List<WireTap> getWireTapType() {
        return wireTap;
    }

    public void setWireTapType(List<WireTap> wireTap) {
        this.wireTap = wireTap;
    }
}
