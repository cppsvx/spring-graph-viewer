package com.craneos.sgv.integration.model.spring.main;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.parser.IntegrationType;

import java.nio.file.Path;

public class SubscribeChannel extends SpringBaseItem {

    private boolean applySequence;

    public SubscribeChannel(String id, Path file){
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
