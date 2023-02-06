package com.craneos.spiflow.graph;

import com.craneos.spiflow.spring.IntegrationDocument;
import com.craneos.spiflow.spring.Step;
import com.craneos.spiflow.util.UtilColor;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.*;

public class GraphStream implements IBaseGraph {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphStream.class);
    //
    private enum NodeType {
        NONE,
        FIRST,
        LAST
    }
    private HashMap<String, Step> flow;
    private String startingChannel;
    private UtilColor utilColor;
    private Set<String> groups;

    public GraphStream(HashMap<String, Step> flow, String startingChannel) {
        this.flow = flow;
        this.startingChannel = startingChannel;
        this.utilColor = new UtilColor();
        this.groups = new HashSet<>();
    }

    @Override
    public void writeUsedFiles(){
        // NOT COMPLETED
        System.out.println(" ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("FILES USED ON THIS FLOW");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        groups.stream().forEach(group -> {System.out.println(group);});
        System.out.println(" ");
        System.out.println(" ");
    }

    @Override
    public void drawFlow(){
        Graph graph = new SingleGraph("WORKFLOW "+startingChannel);
        addNode(graph, null, startingChannel, NodeType.FIRST);
        proccessFlow(flow, graph, startingChannel, null);
        graph.display();
    }

    private void proccessFlow(HashMap<String, Step> flow, Graph graph, String startingChannel, Step parentStep){
        Step step = flow.get(startingChannel);
        if (step!=null){
            if (step.getNextChannels()!=null && step.getNextChannels().size()>0){
                addNode(graph, step.getFilename(), step.getInputChannel());
                if (parentStep!=null){
                    addEdge(graph, parentStep.getInputChannel(), step.getInputChannel());
                }
                for (int i=0; i<step.getNextChannels().size();i++){
                    proccessFlow(flow, graph, step.getNextChannels().get(i), step);
                }
            } else {
                addNode(graph, step.getFilename(), step.getInputChannel());
                if (parentStep!=null){
                    addEdge(graph, parentStep.getInputChannel(), step.getInputChannel());
                }
            }
            addOutputDiscardChannels(step, graph);
        } else if (parentStep!=null){
            addNode(graph, null, startingChannel, NodeType.LAST);
            addEdge(graph, parentStep.getInputChannel(), startingChannel);
        } else {
            addNode(graph, null, "PARENT NODE NOT FOUND", NodeType.FIRST);
            addNode(graph, null, startingChannel, NodeType.LAST);
            addEdge(graph, "PARENT NODE NOT FOUND", startingChannel);
        }
    }

    private void addOutputDiscardChannels(Step step, Graph graph){
        if (step.getOutputChannel()!=null){
            proccessFlow(flow, graph, step.getOutputChannel(), step);
        } else if (step.getDiscardChannel()!=null){
            proccessFlow(flow, graph, step.getDiscardChannel(), step);
        } else {
            LOGGER.info("NO CHANNEL OUTPUT/DISCARD FOR ----> "+startingChannel);
            System.out.println("NO CHANNEL OUTPUT/DISCARD FOR ----> "+startingChannel);
        }
    }

    private void addNode(Graph graph, String group, String name){
        addNode(graph, group, name, NodeType.NONE);
    }

    private void addNode(Graph graph, String group, String name, NodeType type){
        addGroup(group);
        Node graphNode = graph.getNode(name);
        if (graphNode==null){
            Node node = graph.addNode(name);
            node.setAttribute("ui.label",name);
            if (type.equals(NodeType.FIRST)){
                node.setAttribute("ui.style", "shape:circle;fill-color:green;size:15px;");
            } else if (type.equals(NodeType.LAST)){
                node.setAttribute("ui.style", "shape:circle;fill-color:red;size:15px;");
            } else {
                if (group!=null) {
                    Color color = utilColor.getColor(group);
                    node.setAttribute("ui.style", "shape:circle;fill-color:rgb("+color.getRed()+","+color.getGreen()+","+color.getBlue()+");size:10px;");
                }
            }
        }
    }

    private void addGroup(String group){
        if (group!=null){
            boolean isExists = groups.contains(group);
            if (!isExists){
                groups.add(group);
            }
        }
    }

    private void addEdge(Graph graph, String name1, String name2){
        Edge graphEdge = graph.getEdge(name1 + " -> " + name2);
        if (graphEdge==null){
            graph.addEdge(name1 + " -> " + name2, name1, name2, true);
        }
    }

}
