package com.craneos.spiflow.spring;

import com.craneos.spiflow.spring.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntegrationDocument {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationDocument.class);
    //
    private HashMap<String, XmlFile> files;
    private HashMap<String, Step> flow;
    private HashMap<String, List<String>> properties;
    private HashMap<String, Bean> beans;
    private HashMap<String, Channel> channel;

    public IntegrationDocument(){
        this.flow = new HashMap<>();
        this.files = new HashMap<>();
        this.properties = new HashMap<>();
        this.beans = new HashMap<>();
        this.channel = new HashMap<>();
    }

    public void addXmlFile(XmlFile file){
        files.put(file.getFilename(), file);
    }

    public void add(BaseItem item){
        String process = null;
        try{
            if (item instanceof Step) {
                process = IntegrationType.STEP.name();
                flow.put(((Step)item).getInputChannel(), (Step)item);
            } else if (item instanceof Channel){
                process = IntegrationType.CHANNEL.name();
                channel.put(((Channel)item).getId(), ((Channel)item));
                //
            } else if (item instanceof Bean){
                process = IntegrationType.BEANS.name();
                beans.put(((Bean)item).getId(), ((Bean)item));
                //
            } else if (item instanceof Import){
                process = IntegrationType.IMPORT.name();
                addImport((Import)item);
            }
        } catch (Exception e){
            LOGGER.error("------------------ {} ID "+((Bean)item).getId()+" not unique ------------------", process);
        }
    }

    private void addImport(Import item){
        XmlFile file = files.get(item.getFilename());
        file.addImport(item);
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

    public HashMap<String, Bean> getBeans() {
        return beans;
    }

    public void setBeans(HashMap<String, Bean> beans) {
        this.beans = beans;
    }

    public HashMap<String, Channel> getChannel() {
        return channel;
    }

    public void setChannel(HashMap<String, Channel> channel) {
        this.channel = channel;
    }
}
