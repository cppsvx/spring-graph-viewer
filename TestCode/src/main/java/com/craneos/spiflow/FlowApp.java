package com.craneos.spiflow;

import com.craneos.spiflow.spring.types.Import;
import com.craneos.spiflow.spring.IntegrationDocument;
import com.craneos.spiflow.graph.IBaseGraph;
import com.craneos.spiflow.graph.GraphFactory;
import com.craneos.spiflow.graph.GraphType;
import com.craneos.spiflow.spring.IntegrationParser;
import com.craneos.spiflow.spring.Step;
import com.craneos.spiflow.spring.types.XmlFile;

import java.util.*;
import java.util.stream.Collectors;

public class FlowApp {

    private static final String PATH_PLATFORM = "/Users/sanchezc/Development/git/platform/";
    //private static final String STARTING_CHANEL = "ebcdicQARerunTriggerIngestChannel";
    private static final String STARTING_CHANEL = "invoice-test-processing-deletion-filter-channel";

    private HashMap<String, String> data;
    private HashMap<String, String> properties;

    public void execute(String[] args) {
        try {
            this.data = new HashMap<String, String>();
            IntegrationDocument integrationDocument = IntegrationParser.parse(PATH_PLATFORM);
            IBaseGraph graph = GraphFactory.getGraphDrawer(GraphType.GRAPH_STREAM, integrationDocument.getFlow(), STARTING_CHANEL);
            graph.drawFlow();
            graph.writeUsedFiles();
            //
            generateDataReportXML(integrationDocument, STARTING_CHANEL);
            printReportXML(integrationDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDataReportXML(IntegrationDocument integrationDocument, String startingChannel){
        Step step = integrationDocument.getFlow().get(startingChannel);
        if (step!=null){
            lookForImports(integrationDocument, step.getFilename());
            if (step.getNextChannels()!=null && step.getNextChannels().size()>0){
                for (int i=0; i<step.getNextChannels().size();i++){
                    generateDataReportXML(integrationDocument, step.getNextChannels().get(i));
                }
            } else {
                if (step.getOutputChannel()!=null){
                    generateDataReportXML(integrationDocument, step.getOutputChannel());
                } else if (step.getDiscardChannel()!=null){
                    generateDataReportXML(integrationDocument, step.getDiscardChannel());
                }
            }
        }
    }

    private void lookForImports(IntegrationDocument integrationDocument, String filename){
        if (data.get(filename)==null){
            data.put(filename, filename);
            XmlFile xmlFile = integrationDocument.getFiles().get(filename);
            if (xmlFile.getImports()!=null){
                xmlFile.getImports() .forEach(anImport -> {
                    XmlFile file = integrationDocument.getFiles().get(anImport.getResource());
                    if (file!=null){
                        lookForImports(integrationDocument, file.getFilename());
                    } else {
                        data.put(filename, filename);
                        data.put(anImport.getResource(), anImport.getResource());
                    }
                });
            }
/*
            data.put(filename, filename);
            integrationDocument.getImports().get(filename).stream().forEach(item -> {
                List<Import> simport = integrationDocument.getImports().get(item.getResource());
                if (simport!=null){
                    lookForImports(integrationDocument, item.getResource());
                } else {
                    data.put(filename, filename);
                    data.put(item.getResource(), item.getResource());
                }
            });*/
        }
    }

    private void printReportXML(IntegrationDocument integrationDocument){
        // IMPORTS
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("IMPORTS USED");
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        data.values().stream().forEach(item ->{
            System.out.println(item);
        });

        // PROPERTIES
        System.out.println(" ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("PROPERTIES USED");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        data.values().stream().forEach(item ->{
            List<String> properties = integrationDocument.getProperties().get(item);
            if (properties!=null && properties.size()>0){
                System.out.println(" ");
                System.out.println("PROPERTIES FROM FILE --------> "+item);
                properties.stream().forEach(property->{
                    System.out.println(property);
                });
            }
        });

        // PROPERTIES
        System.out.println(" ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ALL PROPERTIES");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        List<String> reportProperties = integrationDocument.getProperties().entrySet().stream()
                .filter(key->data.get(key.getKey())!=null)
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        reportProperties.stream().forEach(property->{
            System.out.println(property);
        });

        // PROPERTIES
        System.out.println(" ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("PROPERTIES USED IN OTHER FILES");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        reportProperties.stream().forEach(property->{
            System.out.println("PROPERTY --------> "+property);
            integrationDocument.getProperties().entrySet().stream().forEach(file -> {
                List<String> list = integrationDocument.getProperties().get(file.getKey());
                if (list!=null && list.size()>0){
                    Optional<String> valueprop = list.stream().filter(value -> value.equals(property)).findAny();
                    if (valueprop.isPresent()){
                        System.out.println("          Used in file --------> "+file.getKey());
                    }
                }
            });
            System.out.println(" ");
        });
    }

}
