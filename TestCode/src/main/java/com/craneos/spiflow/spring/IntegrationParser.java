package com.craneos.spiflow.spring;

import com.craneos.spiflow.spring.types.*;
import com.craneos.spiflow.util.Util;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IntegrationParser {

    private static final String AOP_CONFIG = "aop:config";
    private static final String AOP_ASPECTJ_AUTOPROXY = "aop:aspectj-autoproxy";
    private static final String CONTEXT_MBEAN = "context:mbean-export";
    private static final String CONTEXT_COMPONENT_SCAN = "context:component-scan";
    private static final String CONTEXT_ANNOTATION_CONFIG = "context:annotation-config";
    private static final String CONTEXT_PROPERTY_PLACEHOLDER = "context:property-placeholder";
    private static final String RABBIT_CONNECTION_FACTORY = "rabbit:connection-factory";
    private static final String INT_ANNOTATION_CONFIG = "int:annotation-config";
    private static final String NODE_CHAIN = "int:chain";
    private static final String NODE_SERVICE_ACTIVATOR = "int:service-activator";
    private static final String NODE_ROUTER = "int:router";
    private static final String NODE_HEADER_ROUTER = "int:header-value-router";
    private static final String NODE_HEADER = "int:header-enricher";
    private static final String NODE_HEADER_FILTER = "int:header-filter";
    private static final String NODE_BRIDGE = "int:bridge";
    private static final String NODE_SPLITTER = "int:splitter";
    private static final String NODE_TRANSFORMER = "int:transformer";
    private static final String NODE_AGGREGATOR = "int:aggregator";
    private static final String NODE_BEAN = "bean";
    private static final String NODE_BEANS = "beans";
    private static final String NODE_CHANNEL = "int:channel";
    private static final String NODE_INTERCEPTORS = "int:interceptors";
    private static final String NODE_WIRE_TAP = "int:wire-tap";
    private static final String NODE_SUBSCRIBE_CHANNEL = "int:publish-subscribe-channel";
    private static final String NODE_PAYLOAD_ROUTER = "int:payload-type-router";
    private static final String NODE_FILTER= "int:filter";
    private static final String NODE_INBOUND_CHANNEL_ADAPTER= "int:inbound-channel-adapter";
    private static final String NODE_IMPORT = "import";
    private static final String ATT_ID = "id";
    private static final String ATT_PARENT_ID = "parent";
    private static final String ATT_CLASS = "class";
    private static final String ATT_RESOURCE = "resource";
    private static final String ATT_INPUT_CHANNEL = "input-channel";
    private static final String ATT_OUTPUT_CHANNEL = "output-channel";
    private static final String ATT_DISCARD_CHANNEL = "discard-channel";
    private static final String ATT_MAPPING = "int:mapping";
    private static final String ATT_CHANNEL = "channel";
    //
    private static final String EXTENSION_XML = "xml";
    private static final String EXCLUSION_TARGET = "/target/";
    private static final String INCLUSION_WORKFLOW = "workflow";

    public static IntegrationDocument parse(String path) throws Exception {
        IntegrationDocument integrationDocument = new IntegrationDocument();
        //
        List<Path> filesList = lookForWorkflowFiles(path);
        filesList.stream().forEach(file -> {
            String content = Util.readInputFileToString(file.toString());
            XmlFile xml = new XmlFile();
            xml.setItem(IntegrationType.XMLFILE);
            xml.setAbsolutePath(file.toString());
            xml.setFilename(file.getFileName().toString());
            xml.setContent(Util.readInputFileToString(file.toString()));
            //
            integrationDocument.addXmlFile(xml);
            Document document = Util.convertStringToXMLDocument(content);
            parseDocument(integrationDocument, file, document);
        });
        integrationDocument.getChannel().values().stream()
                .filter(channel -> channel.getInterceptors()!=null && channel.getInterceptors().getWireTapType()!=null)
                .forEach(channel -> {
                    List<WireTap> wireTapList = channel.getInterceptors().getWireTapType();
                    for(WireTap wireTap: wireTapList){
                        Step step = integrationDocument.getFlow().get(channel.getId());
                        if (step!=null){
                            step.addNextChannel(wireTap.getChannel());
                        }
                    }
                });
        return integrationDocument;
    }

    private static List<Path> lookForWorkflowFiles(String path) throws IOException {
        List<Path> list = Files.walk(Paths.get(path))
            .filter(file -> FilenameUtils.getExtension(file.getFileName().toString()).equalsIgnoreCase(EXTENSION_XML))
            .filter(file -> !file.toString().contains(EXCLUSION_TARGET))
            .filter(file -> file.toString().contains(INCLUSION_WORKFLOW))
            .collect(Collectors.toList());
        return list;
    }

    /*private static void parseDocument(IntegrationDocument integrationDocument, Path file, Document document){
        try {
            Element mainElement = document.getDocumentElement();
            NodeList nl = mainElement.getChildNodes();
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nl.item(i);
                parseFlow(integrationDocument, file, node);
                parseProperties(integrationDocument, file, node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ending...");
    }*/

    private static void parseDocument(IntegrationDocument integrationDocument, Path file, Document document){
        try {
            Element mainElement = document.getDocumentElement();
            parseChildNodes(integrationDocument, file, mainElement.getChildNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ending...");
    }

    private static void parseChildNodes(IntegrationDocument integrationDocument, Path file, NodeList nodeList){
        try {
            int length = nodeList.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nodeList.item(i);
                parseChildNodes(integrationDocument, file, node.getChildNodes());
                parseFlow(integrationDocument, file, node);
                parseProperties(integrationDocument, file, node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseProperties(IntegrationDocument integrationDocument, Path file, Node parentNode) throws Exception {
        NodeList nodeList = parentNode.getChildNodes();
        // Check node
        parseAttributes(integrationDocument, file, parentNode);
        // Check subnodes
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            parseProperties(integrationDocument, file, node);
        }
    }

    private static void parseAttributes(IntegrationDocument integrationDocument, Path file, Node parentNode) throws Exception {
        NamedNodeMap attributesMap = parentNode.getAttributes();
        if (attributesMap!=null) {
            for (int i = 0; i < attributesMap.getLength(); i++) {
                Node node = attributesMap.item(i);
                NodeList nodeList = node.getChildNodes();
                if (nodeList != null && nodeList.getLength() > 0) {
                    parseAttributes(integrationDocument, file, node);
                } else {
                    throw new Exception("Error parsing properties. Behaviour not expected.");
                }
            }
        } else {
            String value = parentNode.getNodeValue();
            Pattern pattern = Pattern.compile("\\$\\{.*\\}");
            Matcher matcher = pattern.matcher("${");
            if (value.contains("${")) {
                integrationDocument.addProperty(file.getFileName().toString(), value);
            } else if (matcher.find()) {
                System.out.println("dafaaa");
            }
        }
    }

    private static void parseFlow(IntegrationDocument integrationDocument, Path file, Node node){
        BaseItem item = addStep(file, node);
        if (item !=null){
            if (item instanceof Step) {
                integrationDocument.add(item);
                //
            } else if (item instanceof Import){
                integrationDocument.add(item);
                //
            } else if (item instanceof Bean){
                integrationDocument.add(item);
                //
            } else if (item instanceof Channel){
                integrationDocument.add(item);
            }
        }
    }

    private static BaseItem addStep(Path file, Node node){
        /* --------------
            IGNORED
          -------------- */
        if (node.getNodeName().contains(AOP_CONFIG)) {
            // Ignore
        } else if (node.getNodeName().contains(AOP_ASPECTJ_AUTOPROXY)) {
            // Ignore
        } else if (node.getNodeName().contains(CONTEXT_MBEAN)) {
            // Ignore
        } else if (node.getNodeName().contains(CONTEXT_COMPONENT_SCAN)) {
            // Ignore
        } else if (node.getNodeName().contains(CONTEXT_PROPERTY_PLACEHOLDER)) {
            // Ignore
        } else if (node.getNodeName().contains(INT_ANNOTATION_CONFIG)) {
            // Ignore
        } else if (node.getNodeName().contains(CONTEXT_ANNOTATION_CONFIG)) {
            // Ignore
        } else if (node.getNodeName().contains(RABBIT_CONNECTION_FACTORY)) {
            // Ignore
        } else if (node.getNodeName().contains("#comment")){
            // Ignore
        } else if (node.getNodeName().contains("#text")){
            // Ignore
        /* --------------
            RELATED WITH FLOW
          -------------- */
        } else if (node.getNodeName().contains(NODE_SERVICE_ACTIVATOR)){
            return buildStep(file, IntegrationType.SERVICE_ACTIVATOR, node);
            //
        } else if (node.getNodeName().contains(NODE_ROUTER)){
            return buildRouterStep(file, IntegrationType.ROUTER, node);
            //
        } else if (node.getNodeName().contains(NODE_HEADER_ROUTER)){
            return buildRouterStep(file, IntegrationType.HEADER_ROUTER, node);
            //
        } else if (node.getNodeName().contains(NODE_HEADER)){
            return buildHeader(file, IntegrationType.HEADER, node);
            //
        } else if (node.getNodeName().contains(NODE_CHAIN)){
            return buildChainStep(file, IntegrationType.CHAIN, node);
            //
        } else if (node.getNodeName().contains(NODE_BRIDGE)){
            return buildStep(file, IntegrationType.BRIDGE, node);
            //
        } else if (node.getNodeName().contains(NODE_BEANS)){
            return buildBeansStep(file, IntegrationType.BEANS, node);
            //
        } else if (node.getNodeName().contains(NODE_BEAN)){
            return buildBean(file, IntegrationType.BEAN, node);
            //
        } else if (node.getNodeName().contains(NODE_SPLITTER)){
            return buildStep(file, IntegrationType.SPLITTER, node);
            //
        } else if (node.getNodeName().contains(NODE_TRANSFORMER)){
            return buildStep(file, IntegrationType.TRANSFORMER, node);
            //
        } else if (node.getNodeName().contains(NODE_AGGREGATOR)){
            return buildStep(file, IntegrationType.AGGREGATOR, node);
            //
        } else if (node.getNodeName().contains(NODE_SUBSCRIBE_CHANNEL)){
            return buildSubscribeChannel(file, IntegrationType.SUBSCRIBE_CHANNEL, node);
            //
        } else if (node.getNodeName().contains(NODE_CHANNEL)){
            return buildChannel(file, IntegrationType.CHANNEL, node);
            //
        } else if (node.getNodeName().contains(NODE_PAYLOAD_ROUTER)){
            return buildRouterStep(file, IntegrationType.PAYLOAD_ROUTER, node);
            //
        } else if (node.getNodeName().contains(NODE_FILTER)){
            return buildFilter(file, IntegrationType.FILTER, node);
            //
        } else if (node.getNodeName().contains(NODE_HEADER_FILTER)){
            return buildFilter(file, IntegrationType.FILTER, node);
            //
        } else if (node.getNodeName().contains(NODE_IMPORT)){
            return buildImport(file, IntegrationType.IMPORT, node);
            //
            //
        } else if (node.getNodeName().contains(NODE_INBOUND_CHANNEL_ADAPTER)){
            // TODO URGENTISIMO DE LA MUERTE, DEATH URGENCY --> return buildImport(file, IntegrationType.INBOUND_CHANNEL_ADAPTER, node);
            //
        } else {
            System.out.println("not implememented yet --> "+node.getNodeName());
            return null;
        }
        return null;
    }

    private static Step buildHeader(Path file, IntegrationType springItem, Node parentNode) {
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
        if (namedItemInputChannel!=null) {
            step.setInputChannel(namedItemInputChannel.getNodeValue());
        }
        if (namedItemOutputChannel!=null) {
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        //
        return step;
    }

    private static Step buildChainStep(Path file, IntegrationType springItem, Node parentNode) {
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

    private static Step buildStep(Path file, IntegrationType springItem, Node parentNode) {
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
        if (namedItemInputChannel!=null) {
            step.setInputChannel(namedItemInputChannel.getNodeValue());
        }
        if (namedItemOutputChannel!=null) {
            step.setOutputChannel(namedItemOutputChannel.getNodeValue());
        }
        //
        return step;
    }

    private static Step buildFilter(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemDiscardChannel = nodeMap.getNamedItem(ATT_DISCARD_CHANNEL);
        Node namedItemInputChannel = nodeMap.getNamedItem(ATT_INPUT_CHANNEL);
        Node namedItemOutputChannel = nodeMap.getNamedItem(ATT_OUTPUT_CHANNEL);
        //
        Step step = new Step();
        step.setItem(springItem);
        step.setFilename(file.getFileName().toString());
        step.setAbsolutePath(file.toString());
        if (namedItemInputChannel!=null){
            step.setInputChannel(namedItemInputChannel.getNodeValue());
        }
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

    private static Import buildImport(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemResource = nodeMap.getNamedItem(ATT_RESOURCE);
        //
        Import simport = new Import();
        simport.setItem(springItem);
        simport.setFilename(file.getFileName().toString());
        simport.setAbsolutePath(file.toString());
        if (namedItemResource.getNodeValue().contains("classpath:")){
            String nameandpath = namedItemResource.getNodeValue().replace("classpath:","");
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        } else if (namedItemResource.getNodeValue().contains("classpath*:")){
            String nameandpath = namedItemResource.getNodeValue().replace("classpath*:","");
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        } else {
            String nameandpath = namedItemResource.getNodeValue();
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        }
        return simport;
    }

    private static BaseItem buildBean(Path file, IntegrationType springItem, Node parentNode){
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        Node namedItemParentId = nodeMap.getNamedItem(ATT_PARENT_ID);
        Node namedItemClass = nodeMap.getNamedItem(ATT_CLASS);
        //
        Bean bean = new Bean();
        bean.setId(namedItemId==null?"":namedItemId.getNodeValue());
        bean.setItem(springItem);
        bean.setFilename(file.getFileName().toString());
        bean.setAbsolutePath(file.toString());
        if (namedItemParentId!=null){
            bean.setParentId(namedItemParentId.getNodeValue());
        }
        if (namedItemClass!=null){
            bean.setFullClass(namedItemClass.getNodeValue());
            bean.setClassName(FilenameUtils.getExtension(namedItemClass.getNodeValue()));
        }
        return bean;
    }

    private static BaseItem buildBeansStep(Path file, IntegrationType springItem, Node parentNode){
        BaseItem beans = null;
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_BRIDGE)){
                beans = addStep(file, node);
                break;
            }
        }
        return beans;
    }

    private static Channel buildSubscribeChannel(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        //
        Channel channel = new Channel();
        channel.setItem(springItem);
        channel.setFilename(file.getFileName().toString());
        channel.setAbsolutePath(file.toString());
        channel.setId(namedItemId.getNodeValue());
        return channel;
    }

    private static Channel buildChannel(Path file, IntegrationType springItem, Node parentNode){
        Channel channel = new Channel();
        //
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_INTERCEPTORS)){
                Interceptors interceptors = buildInterceptors(file, IntegrationType.INTERCEPTOR, node);
                channel.setInterceptors(interceptors);
            }
        }
        //
        channel.setItem(springItem);
        channel.setFilename(file.getFileName().toString());
        channel.setAbsolutePath(file.toString());
        channel.setId(namedItemId.getNodeValue());
        return channel;
    }

    private static Interceptors buildInterceptors(Path file, IntegrationType springItem, Node parentNode){
        List<WireTap> wireTapList = new ArrayList<>();
        //
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_WIRE_TAP)){
                WireTap wireTap = buildWireTap(node);
                wireTapList.add(wireTap);
            }
        }
        //
        Interceptors interceptors = new Interceptors();
        interceptors.setWireTapType(wireTapList);
        return interceptors;
    }

    private static WireTap buildWireTap(Node parentNode){
        Node channel = parentNode.getAttributes().getNamedItem(ATT_CHANNEL);
        //
        WireTap wireTap = new WireTap();
        wireTap.setChannel(channel.getNodeValue());
        return wireTap;
    }

    private static Step buildRouterStep(Path file, IntegrationType springItem, Node parentNode){
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

    /*
    private boolean hasAttribute(Node parentNode, String attributeName){
        NamedNodeMap nodeMap = parentNode.getAttributes();
        if (nodeMap!=null){
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node node = nodeMap.item(i);
                if (node.getNodeName().equals(attributeName)){
                    return true;
                }
            }
        }
        return false;
    }*/

}
