package com.craneos.example;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashMap;

public class FlowAppExample {

    private HashMap<String, String> data;
    private HashMap<String, String> properties;

    public static void main(String[] args){;
        try {
            System.setProperty("org.graphstream.ui", "javafx");
            System.setProperty("org.graphstream.debug", "true");
            Graph graph = new SingleGraph("Tutorial 1");
            graph.addNode("A" );
            graph.addNode("B" );
            graph.addNode("C" );
            graph.addEdge("AB", "A", "B");
            graph.addEdge("BC", "B", "C");
            graph.addEdge("CA", "C", "A");
            graph.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute(String[] args) {

    }


}
