package com.craneos.sgv.integration;

import com.craneos.sgv.integration.graph.graph.GraphFactory;
import com.craneos.sgv.integration.graph.graph.GraphType;
import com.craneos.sgv.integration.graph.graph.IBaseGraph;
import com.craneos.sgv.integration.model.IntegrationDocument;
import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.model.app.XmlFile;
import com.craneos.sgv.integration.parser.IntegrationParser;
import org.graphstream.graph.Graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpringGraphViewer {



    private HashMap<String, String> data;
    private HashMap<String, String> properties;


    /*public IntegrationDocument getIntegrationDocument() {
        return integrationDocument;
    }*/
/*
    public Graph execute() {
        Graph graph = null;
        try {
            this.data = new HashMap<>();
            IntegrationDocument integrationDocument = IntegrationParser.getInstance().parse(PATH_PLATFORM);
            IBaseGraph graphBuilder = GraphFactory.getGraphDrawer(GraphType.GRAPH_STREAM, integrationDocument.getFlow(), STARTING_CHANEL);
            graph = graphBuilder.generateGraph();
            graphBuilder.writeUsedFiles();
            //
            //generateDataReportXML(integrationDocument, STARTING_CHANEL);
            //printReportXML(integrationDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
    */
/*
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
*/

}
