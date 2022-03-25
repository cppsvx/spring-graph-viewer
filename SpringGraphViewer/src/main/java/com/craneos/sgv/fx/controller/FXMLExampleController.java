package com.craneos.sgv.fx.controller;

import com.craneos.sgv.GraphManager;
import com.craneos.sgv.fx.DetailsPanel;
import com.craneos.sgv.fx.SgvFxMouseManager;
import com.craneos.sgv.integration.SpringGraphViewer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.graphstream.graph.Graph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;
import org.graphstream.ui.view.util.InteractiveElement;

import java.util.EnumSet;

public class FXMLExampleController {

    @FXML private MenuBar mainMenuBar;
    @FXML private Button btHello;
    @FXML private SplitPane mainSplitPane;
    @FXML private AnchorPane mainPaneLeft;
    @FXML private AnchorPane mainPaneCenter;
    @FXML private DetailsPanel mainPaneRight;
    @FXML protected Label pcLabel1;


    private EnumSet<InteractiveElement> VIEW_ELEMENTS = EnumSet.of(InteractiveElement.EDGE, InteractiveElement.NODE, InteractiveElement.SPRITE);

    private GraphManager graphManager;

    public FXMLExampleController(){
    }

    @FXML
    private void handleButtonPress() {
        //Node newNode = ... ;
        //mainSplitPane.getItems().set(1, newNode);
    }

    @FXML
    protected void onLoadGraph() {
        Graph graph = GraphManager.getInstance().generateGraph();
        //SpringGraphViewer sgv = new SpringGraphViewer();
        //Graph graph = sgv.execute();
        FxViewer fxviewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxviewer.enableAutoLayout();
        FxGraphRenderer fxGraphRenderer = new FxGraphRenderer();
        FxViewPanel panel = (FxViewPanel) fxviewer.addDefaultView(false, fxGraphRenderer);

        SgvFxMouseManager mouseManager = new SgvFxMouseManager(VIEW_ELEMENTS, mainPaneRight);
        panel.setMouseManager(mouseManager);

        mainPaneCenter.getChildren().addAll(panel);
    }

    @FXML
    protected void onGenerateReport() {
        GraphManager.getInstance().generateReport();
    }
}