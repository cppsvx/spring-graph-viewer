package com.craneos.sgv.integration.model.spring.tags.defs;

import com.craneos.sgv.integration.model.spring.tags.defs.BaseItem;
import com.craneos.sgv.integration.model.spring.subs.Interceptor;

public class Channel extends BaseItem {

    private String scope;
    //private Dispatcher dispatcher;
    private Interceptor interceptor;

    public Channel(){
        super();
        this.scope = null;
        //this.dispatcher = null;
        this.interceptor = null;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Interceptor getInterceptors() {
        return interceptor;
    }

    public void setInterceptors(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
