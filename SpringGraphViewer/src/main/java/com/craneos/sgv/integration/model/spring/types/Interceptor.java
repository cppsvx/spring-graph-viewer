package com.craneos.sgv.integration.model.spring.types;

import com.craneos.sgv.integration.model.annotations.MaxOccurs;
import com.craneos.sgv.integration.model.annotations.MinOccurs;
import com.craneos.sgv.integration.model.spring.defs.BaseItem;

import java.util.List;

@MinOccurs(value = 0)
@MaxOccurs(value = 1)
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
