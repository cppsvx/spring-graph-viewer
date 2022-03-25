package com.craneos.sgv.integration.model.spring.stepable;

import java.util.List;

public interface IStep {

    String getInputChannel();
    void setInputChannel(String inputChannel);
    String getOutputChannel();
    void setOutputChannel(String outputChannel);
    String getDiscardChannel();
    void setDiscardChannel(String discardChannel);
    List<String> getChannels();

}
