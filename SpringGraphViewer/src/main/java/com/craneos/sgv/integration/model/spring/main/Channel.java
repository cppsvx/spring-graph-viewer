package com.craneos.sgv.integration.model.spring.main;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.model.spring.subs.Interceptor;

public class Channel extends SpringBaseItem {

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
