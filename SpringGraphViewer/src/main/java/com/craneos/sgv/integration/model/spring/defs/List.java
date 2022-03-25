package com.craneos.sgv.integration.model.spring.defs;

import java.util.ArrayList;

public class List extends BaseItem{

    public ArrayList<BaseItem> list;

    public ArrayList<BaseItem> getList() {
        return list;
    }

    public void setList(ArrayList<BaseItem> list) {
        this.list = list;
    }
}
