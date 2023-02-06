package com.craneos.spiflow.spring.types;

public class Channel extends BaseItem {

    private String id;
    private Interceptors interceptors;

    public Channel(){
        super();
        this.id = null;
        this.interceptors = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Interceptors getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(Interceptors interceptors) {
        this.interceptors = interceptors;
    }
}
