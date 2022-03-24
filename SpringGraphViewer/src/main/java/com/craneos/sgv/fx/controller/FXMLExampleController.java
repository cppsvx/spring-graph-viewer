package com.craneos.sgv.fx.controller;

import com.craneos.sgv.fx.SgvFxMouseManager;
import com.craneos.sgv.integration.graph.SpringGraphViewer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxDefaultView;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;
import org.graphstream.ui.view.util.InteractiveElement;

import java.util.EnumSet;

public class FXMLExampleController {

    @FXML private Button btHello;
    @FXML private SplitPane mainVBoxSplitPane;
    @FXML private AnchorPane mainVBoxPaneRight;

    private EnumSet<InteractiveElement> VIEW_ELEMENTS = EnumSet.of(InteractiveElement.EDGE, InteractiveElement.NODE, InteractiveElement.SPRITE);

    @FXML
    private void handleButtonPress() {
        //Node newNode = ... ;
        //mainSplitPane.getItems().set(1, newNode);
    }

    @FXML
    protected void onHelloButtonClick() {
        SpringGraphViewer sgv = new SpringGraphViewer();
        Graph graph = sgv.execute();
        FxDefaultView fxDefaultView = (FxDefaultView) graph.display().getDefaultView();
        //Viewer viewer = fxDefaultView.getViewer();
        SgvFxMouseManager mouseManager = new SgvFxMouseManager(VIEW_ELEMENTS, sgv);
        fxDefaultView.setMouseManager(mouseManager);

        FxViewer fxviewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxviewer.enableAutoLayout();
        FxGraphRenderer fxGraphRenderer = new FxGraphRenderer();
        FxViewPanel panel = (FxViewPanel) fxviewer.addDefaultView(false, fxGraphRenderer);
        //panel.addListener(EventType.ROOT, new MyViewerListener(graph, fxDefaultView, viewer));

        // -----------------
        //Viewer viewer = new SwingViewer( graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD );
        /*ViewerPipe pipeIn = fxviewer.newViewerPipe();

        View view  = fxviewer.addView( "view1", fxGraphRenderer);
        view.addListener(EventType.ROOT, new MyViewerListener());
*/
        //Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //ViewPanel view = viewer.addDefaultView(false);

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