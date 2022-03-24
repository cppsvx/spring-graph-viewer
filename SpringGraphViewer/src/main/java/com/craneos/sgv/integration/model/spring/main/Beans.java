package com.craneos.sgv.integration.model.spring.main;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.model.spring.subs.Bridge;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Beans extends SpringBaseItem {

    private String profile;
    private String defaultAutowire;
    private String defaultAutowireCandidates;
    private List<Bridge> bridges;

    public Beans(String id, Path file){
        super(id, file, IntegrationType.BEANS);
        this.profile = null;
        this.defaultAutowire = null;
        this.defaultAutowireCandidates = null;
        this.bridges = null;
    }

    public void addBridges(Bridge bridge){
        if (bridges==null){
            bridges = new ArrayList<>();
        }
        bridges.add(bridge);
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDefaultAutowire() {
        return defaultAutowire;
    }

    public void setDefaultAutowire(String defaultAutowire) {
        this.defaultAutowire = defaultAutowire;
    }

    public String getDefaultAutowireCandidates() {
        return defaultAutowireCandidates;
    }

    public void setDefaultAutowireCandidates(String defaultAutowireCandidates) {
        this.defaultAutowireCandidates = defaultAutowireCandidates;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }

}
