package com.craneos.sgv.integration.model.spring.defs;

import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class PublishSubscribeChannel extends BaseItem {

    private boolean applySequence;

    public PublishSubscribeChannel(String id, XmlFile file){
        super(id, file, IntegrationType.SUBSCRIBE_CHANNEL);
        this.applySequence = false;
    }

    public boolean isApplySequence() {
        return applySequence;
    }

    public void setApplySequence(boolean applySequence) {
        this.applySequence = applySequence;
    }
}
