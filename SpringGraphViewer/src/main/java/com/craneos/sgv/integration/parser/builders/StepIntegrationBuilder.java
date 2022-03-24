package com.craneos.sgv.integration.parser.builders;

import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.parser.IntegrationType;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StepIntegrationBuilder extends BaseIntegrationBuilder {


    protected static final String NODE_AGGREGATOR = "aggregator";
    protected static final String NODE_ANNOTATION_CONFIG = "annotation-config";
    protected static final String NODE_BRIDGE = "bridge";
    protected static final String NODE_CHAIN = "chain";
    protected static final String NODE_FILTER= "filter";
    protected static final String NODE_HEADER = "header-enricher";
    protected static final String NODE_HEADER_ROUTER = "header-value-router";
    protected static final String NODE_ROUTER = "router";
    protected static final String NODE_SERVICE_ACTIVATOR = "service-activator";
    protected static final String NODE_SPLITTER = "plitter";
    protected static final String NODE_PAYLOAD_ROUTER = "payload-type-router";
    protected static final String NODE_TRANSFORMER = "transformer";

    public static StepIntegrationBuilder instantiate(Document document){
        StepIntegrationBuilder stepBuilder = new StepIntegrationBuilder();
        stepBuilder.init(document);
        return stepBuilder;
    }

    public Step buildStep(Path file, Node node){



        if (ignore(node)){
            return null;
        } else if (nodeContains(node, NODE_SERVICE_ACTIVATOR)){
            return buildStep(file, IntegrationType.SERVICE_ACTIVATOR, node);
        } else if (nodeContains(node, NODE_ROUTER)){
            return buildRouterStep(file, IntegrationType.ROUTER, node);
        } else if (nodeContains(node, NODE_HEADER_ROUTER)){
            return buildRouterStep(file, IntegrationType.HEADER_ROUTER, node);
        } else if (nodeContains(node, NODE_HEADER)){
            return buildHeader(file, IntegrationType.HEADER, node);
        } else if (nodeContains(node, NODE_CHAIN)){
            return buildChainStep(file, IntegrationType.CHAIN, node);
        } else if (nodeContains(node, NODE_BRIDGE)) {
            return buildStep(file, IntegrationType.BRIDGE, node);
        } else if (nodeContains(node, NODE_SPLITTER)) {
            return buildStep(file, IntegrationType.SPLITTER, node);
        } else if (nodeContains(node, NODE_TRANSFORMER)){
            return buildStep(file, IntegrationType.TRANSFORMER, node);
        } else if (nodeContains(node, NODE_AGGREGATOR)){
            return buildStep(file, IntegrationType.AGGREGATOR, node);
        } else if (nodeContains(node, NODE_PAYLOAD_ROUTER)){
            return buildRouterStep(file, IntegrationType.PAYLOAD_ROUTER, node);
        } else if (nodeContains(node, NODE_FILTER)){
            return buildFilter(file, IntegrationType.FILTER, node);
        }
        return null;
    }

    private Step buildHeader(Path file, IntegrationType springItem, Node parentNode) {
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        Node namedItemOutputChannel = nodeMap.getNamedItem(ATT_OUTPUT_CHANNEL);
        //
        Step step = new Step();
        step.setId(namedItemId==null?"":namedItemId.getNodeValue());
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        step.setInputChannel(namedItemInputChannel.getNodeValue());
        if (namedItemOutputChannel!=null) {
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        //
        return step;
    }

    private Step buildChainStep(Path file, IntegrationType springItem, Node parentNode) {
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        Node namedItemOutputChannel = nodeMap.getNamedItem(ATT_OUTPUT_CHANNEL);
        Node namedItemDiscardChannel = null;
        //
        Step step = new Step();
        step.setId(namedItemId==null?"":namedItemId.getNodeValue());
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        step.setInputChannel(namedItemInputChannel.getNodeValue());
        //
        // OUTPUT
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_FILTER)) {
                namedItemDiscardChannel = node.getAttributes().getNamedItem(ATT_DISCARD_CHANNEL);
            }
        }
        if (namedItemOutputChannel != null && namedItemDiscardChannel != null) {
            List<String> nextChannels = new ArrayList<>();
            nextChannels.add(namedItemOutputChannel.getNodeValue());
            nextChannels.add(namedItemDiscardChannel.getNodeValue());
            step.setNextChannels(nextChannels);
        } else if (namedItemDiscardChannel!=null){
            step.setDiscardChannel(namedItemDiscardChannel.getNodeValue());
        } else if (namedItemOutputChannel!=null){
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        return step;
    }

    private Step buildStep(Path file, IntegrationType springItem, Node parentNode) {
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        Node namedItemOutputChannel = nodeMap.getNamedItem(ATT_OUTPUT_CHANNEL);
        //
        Step step = new Step();
        step.setId(namedItemId==null?"":namedItemId.getNodeValue());
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        step.setInputChannel(namedItemInputChannel.getNodeValue());
        if (namedItemOutputChannel!=null) {
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        //
        return step;
    }

    private Step buildFilter(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemDiscardChannel = nodeMap.getNamedItem(ATT_DISCARD_CHANNEL);
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        Node namedItemOutputChannel = nodeMap.getNamedItem(ATT_OUTPUT_CHANNEL);
        //
        Step step = new Step();
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        step.setInputChannel(namedItemInputChannel.getNodeValue());
        if (namedItemOutputChannel!=null && namedItemDiscardChannel!=null){
            List<String> nextChannels = new ArrayList<>();
            nextChannels.add(namedItemOutputChannel.getNodeValue());
            nextChannels.add(namedItemDiscardChannel.getNodeValue());
            step.setNextChannels(nextChannels);
        } else if (namedItemDiscardChannel!=null){
            step.setDiscardChannel(namedItemDiscardChannel.getNodeValue());
        } else if (namedItemOutputChannel!=null){
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        return step;
    }

    private Step buildRouterStep(Path file, IntegrationType springItem, Node parentNode){
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        List<String> nextChannels = new ArrayList<>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(ATT_MAPPING)){
                Node item = node.getAttributes().getNamedItem(ATT_CHANNEL);
                String nextChannel = item.getNodeValue();
                nextChannels.add(nextChannel);
            }
        }
        //
        Step step = new Step();
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        step.setInputChannel(namedItemInputChannel.getNodeValue());
        step.setNextChannels(nextChannels);
        //
        return step;
    }
}
