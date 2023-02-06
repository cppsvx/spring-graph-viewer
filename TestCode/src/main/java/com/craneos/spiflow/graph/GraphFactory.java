package com.craneos.spiflow.graph;

import com.craneos.spiflow.spring.Step;

import java.util.HashMap;

public class GraphFactory {

    public static IBaseGraph getGraphDrawer(GraphType choice, HashMap<String, Step> nodes, String startingChannel) throws Exception {
        if(choice.equals(GraphType.GRAPH_STREAM)){
            System.out.println("Using GRAPH_STREAM library");
            return new GraphStream(nodes, startingChannel);
        }
        else if(choice.equals(GraphType.JGRAPHT)){
            throw new Exception("JGRAPHT not implemented.");
        }
        return null;
    }
}