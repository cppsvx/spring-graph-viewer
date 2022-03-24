package com.craneos.sgv.integration.graph.graph;

import org.graphstream.graph.Graph;

public interface IBaseGraph {
    Graph generateGraph();
    void drawFlow();
    void writeUsedFiles();
}
