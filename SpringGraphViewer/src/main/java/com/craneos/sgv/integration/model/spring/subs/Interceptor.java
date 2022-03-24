package com.craneos.sgv.integration.model.spring.subs;

import com.craneos.sgv.integration.model.spring.tags.defs.BaseItem;

import java.util.List;

public class Interceptor extends BaseItem {

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
