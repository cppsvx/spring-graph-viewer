package com.craneos.sgv.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;

public class FXMLExampleController {

    @FXML private Button btHello;
    @FXML private SplitPane mainVBoxSplitPane;
    @FXML private AnchorPane mainVBoxPaneRight;

    @FXML
    private void handleButtonPress() {
        //Node newNode = ... ;
        //mainSplitPane.getItems().set(1, newNode);
    }

    @FXML
    protected void onHelloButtonClick() {
        Graph graph = getGraph();
        FxViewer fxviewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxviewer.enableAutoLayout();
        FxViewPanel panel = (FxViewPanel) fxviewer.addDefaultView(false, new FxGraphRenderer());

        mainVBoxPaneRight.getChildren().addAll(panel);
        // Node node = splitPane.getItems().get(1);
    }

    public static Graph getGraph(){
        Graph graph = new SingleGraph("Tutorial 1");
        try {
            graph.addNode("A" );
            graph.addNode("B" );
            graph.addNode("C" );
            graph.addEdge("AB", "A", "B");
            graph.addEdge("BC", "B", "C");
            graph.addEdge("CA", "C", "A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}