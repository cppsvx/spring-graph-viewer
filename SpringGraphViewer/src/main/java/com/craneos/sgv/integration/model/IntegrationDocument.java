package com.craneos.sgv.integration.model;

import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.model.spring.tags.defs.BaseItem;
import com.craneos.sgv.integration.model.spring.tags.defs.Bean;
import com.craneos.sgv.integration.model.spring.tags.Beans;
import com.craneos.sgv.integration.model.spring.tags.defs.Channel;
import com.craneos.sgv.integration.model.spring.tags.defs.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntegrationDocument {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationDocument.class);
    //
    private HashMap<String, XmlFile> files;
    private HashMap<String, Step> flow;
    private HashMap<String, List<String>> properties;
    private HashMap<String, Bean> bean;
    private HashMap<String, Beans> beanss;
    private HashMap<String, Channel> channels;

    public IntegrationDocument(){
        this.files = new HashMap<>();
        this.flow = new HashMap<>();
        this.properties = new HashMap<>();
        this.bean = new HashMap<>();
        this.beanss = new HashMap<>();
        this.channels = new HashMap<>();
    }

    public void addXmlFile(XmlFile file){
        files.put(file.getFilename(), file);
    }

    public void addStep(Step step){
        flow.put(step.getInputChannel(), step);
    }

    public void addSpringItem(BaseItem item){
        try{
            if (item instanceof Channel){
                channels.put(item.getId(), ((Channel)item));
                //
            } else if (item instanceof Bean){
                bean.put(((Bean)item).getId(), ((Bean)item));
                //
            } else if (item instanceof Beans){
                beanss.put(((Beans)item).getId(), ((Beans)item));
                //
            } else if (item instanceof Import){
                XmlFile file = files.get(item.getFilename());
                file.addImport((Import)item);
            }
        } catch (Exception e){
            LOGGER.error("------------------ {} ID "+(item.getItem().name())+" not unique ------------------", item.getItem().name());
        }
    }

    public void addProperty(String name, String property){
        if (properties.get(name)!=null){
            properties.get(name).add(property);
        } else {
            List<String> list = new ArrayList<>();
            list.add(property);
            properties.put(name, list);
        }
    }

    public HashMap<String, XmlFile> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, XmlFile> files) {
        this.files = files;
    }

    public HashMap<String, Step> getFlow() {
        return flow;
    }

    public void setFlow(HashMap<String, Step> flow) {
        this.flow = flow;
    }

    public HashMap<String, List<String>> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, List<String>> properties) {
        this.properties = properties;
    }

    public HashMap<String, Bean> getBean() {
        return bean;
    }

    public void setBean(HashMap<String, Bean> bean) {
        this.bean = bean;
    }

    public HashMap<String, Beans> getBeanss() {
        return beanss;
    }

    public void setBeanss(HashMap<String, Beans> beanss) {
        this.beanss = beanss;
    }

    public HashMap<String, Channel> getChannels() {
        return channels;
    }

    public void setChannels(HashMap<String, Channel> channels) {
        this.channels = channels;
    }

}
