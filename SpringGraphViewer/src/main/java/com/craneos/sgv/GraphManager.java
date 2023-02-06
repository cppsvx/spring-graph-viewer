package com.craneos.sgv;

import com.craneos.sgv.integration.graph.graph.GraphFactory;
import com.craneos.sgv.integration.graph.graph.GraphType;
import com.craneos.sgv.integration.graph.graph.IBaseGraph;
import com.craneos.sgv.integration.model.IntegrationDocument;
import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.parser.IntegrationParser;
import org.graphstream.graph.Graph;

public class GraphManager {

    // TODO load from file/input text
    private static final String PATH_PLATFORM = "/Users/sanchezc/Development/git/rogers";
    //private static final String STARTING_CHANEL = "ebcdicQARerunTriggerIngestChannel";
    private static final String STARTING_CHANEL = "bulk-preview-fragment-done-channel";
    //
    private static GraphManager instance;
    //
    private IntegrationDocument integrationDocument;
    private ReportManager reportManager;

    public static GraphManager getInstance(){
        if (instance==null){
            instance = new GraphManager();
        }
        return instance;
    }

    private GraphManager() {
        this.integrationDocument = null;
    }

    public Step getNodes(String id){
        Step step = integrationDocument.getFlow().get(id);
        return step;
    }

    public Graph generateGraph(){
        Graph graph = null;
        try {
            integrationDocument = IntegrationParser.getInstance().parse(PATH_PLATFORM);
            IBaseGraph graphBuilder = GraphFactory.getGraphDrawer(GraphType.GRAPH_STREAM, integrationDocument.getFlow(), STARTING_CHANEL);
            graph = graphBuilder.generateGraph();
            graphBuilder.writeUsedFiles();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    public Graph generateReport(){
        Graph graph = null;
        try {
            //
            ReportManager reportManager = new ReportManager();
            reportManager.generateDataReportXML(integrationDocument, STARTING_CHANEL);
            reportManager.printReportXML(integrationDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

}
