package com.craneos.sgv.integration.model.spring.subs;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;

import java.util.List;

public class Interceptor extends SpringBaseItem {

    private List<WireTap> wireTap;

    public Interceptor(){
        this.wireTap = null;
    }

    public List<WireTap> getWireTapType() {
        return wireTap;
    }

    public void setWireTapType(List<WireTap> wireTap) {
        this.wireTap = wireTap;
    }
}
