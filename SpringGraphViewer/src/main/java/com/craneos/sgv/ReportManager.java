package com.craneos.sgv;

import com.craneos.sgv.integration.model.IntegrationDocument;
import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.model.app.XmlFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportManager {

    private HashMap<String, String> data;

    public ReportManager(){
        this.data = new HashMap<>();
    }

    public void generateDataReportXML(IntegrationDocument integrationDocument, String startingChannel){
        Step step = integrationDocument.getFlow().get(startingChannel);
        if (step!=null){
            lookForImports(integrationDocument, step.getFilename());
            if (step.getChannels()!=null && step.getChannels().size()>0){
                for (int i=0; i<step.getChannels().size();i++){
                    generateDataReportXML(integrationDocument, step.getChannels().get(i));
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

    public void printReportXML(IntegrationDocument integrationDocument){
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
