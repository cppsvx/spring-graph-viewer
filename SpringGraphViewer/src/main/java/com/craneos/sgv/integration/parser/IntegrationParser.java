package com.craneos.sgv.integration.parser;

import com.craneos.sgv.integration.model.IntegrationDocument;
import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.model.spring.subs.Bridge;
import com.craneos.sgv.integration.model.spring.subs.WireTap;
import com.craneos.sgv.integration.parser.builders.SpringItemIntegrationBuilder;
import com.craneos.sgv.integration.parser.builders.StepIntegrationBuilder;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IntegrationParser {

    private static final String EXTENSION_XML = "xml";
    private static final String EXCLUSION_TARGET = "/target/";
    private static final String INCLUSION_WORKFLOW = "workflow";
    //
    private static IntegrationParser instance;
    //
    private IntegrationDocument integrationDocument;

    public static IntegrationParser getInstance(){
        if (instance==null){
            instance = new IntegrationParser();
        }
        return instance;
    }

    public IntegrationParser(){
        this.integrationDocument = new IntegrationDocument();
    }

    private List<Path> lookForWorkflowFiles(String path) throws IOException {
        List<Path> list = Files.walk(Paths.get(path))
                .filter(file -> FilenameUtils.getExtension(file.getFileName().toString()).equalsIgnoreCase(EXTENSION_XML))
                .filter(file -> !file.toString().contains(EXCLUSION_TARGET))
                .filter(file -> file.toString().contains(INCLUSION_WORKFLOW)) // TODO USE OTHER FILTER
                .collect(Collectors.toList());
        return list;
    }

    public IntegrationDocument parse(String path) throws Exception {
        List<Path> filesList = lookForWorkflowFiles(path);
        filesList.stream().map(file-> buildXmlFile(file)).forEach(xmlFile -> {
            integrationDocument.addXmlFile(xmlFile);
            Document document = IntegrationUtil.convertStringToXMLDocument(xmlFile.getContent());
            parseDocument(xmlFile.getPath(), document);
        });
        // Create STEPS from CHANNELS
        integrationDocument.getChannels().values().stream()
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
        // Create STEPS from BEANS
        integrationDocument.getBeanss().values().stream()
                .filter(beanss -> beanss.getBridges()!=null && beanss.getBridges().size()>0)
                .forEach(beanss -> {
                    List<Bridge> bridges = beanss.getBridges();
                    for(Bridge bridge: bridges){
                        Step step = integrationDocument.getFlow().get(bridge.getId());
                        if (step!=null){
                            step.addNextChannel(bridge.getInputChannel());
                        } else {
                            Step newStep = new Step();
                            newStep.setId(bridge.getId());
                            newStep.setFilename(bridge.getFilename());
                            newStep.setInputChannel(bridge.getInputChannel());
                            newStep.setOutputChannel(bridge.getOutputChannel());
                            integrationDocument.addStep(newStep);
                        }
                    }
                });
        return integrationDocument;
    }

    private void parseDocument(Path file, Document document){
        try {
            StepIntegrationBuilder stepBuilder = StepIntegrationBuilder.instantiate(document);
            SpringItemIntegrationBuilder springBuilder = SpringItemIntegrationBuilder.instantiate(document);
            Element mainElement = document.getDocumentElement();
            NodeList nl = mainElement.getChildNodes();
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nl.item(i);
                Step step = stepBuilder.buildStep(file, node);
                if (step!=null){
                    integrationDocument.addStep(step);
                }
                SpringBaseItem item = springBuilder.buildItem(file, node);
                if (item !=null){
                    integrationDocument.addSpringItem(item);
                }
                parseProperties(file, node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseProperties(Path file, Node parentNode) throws Exception {
        NodeList nodeList = parentNode.getChildNodes();
        // Check node
        parseAttributes(file, parentNode);
        // Check subnodes
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            parseProperties(file, node);
        }
    }

    private void parseAttributes(Path file, Node parentNode) throws Exception {
        NamedNodeMap attributesMap = parentNode.getAttributes();
        if (attributesMap!=null) {
            for (int i = 0; i < attributesMap.getLength(); i++) {
                Node node = attributesMap.item(i);
                NodeList nodeList = node.getChildNodes();
                if (nodeList != null && nodeList.getLength() > 0) {
                    parseAttributes(file, node);
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

    private XmlFile buildXmlFile(Path file){
        XmlFile xml = new XmlFile();
        xml.setAbsolutePath(file.toString());
        xml.setFilename(file.getFileName().toString());
        xml.setContent(IntegrationUtil.readInputFileToString(file.toString()));
        xml.setPath(file);
        return xml;
    }

}
