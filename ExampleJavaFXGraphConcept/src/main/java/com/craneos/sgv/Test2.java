package com.craneos.sgv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxDefaultView;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;
import org.graphstream.ui.view.GraphRenderer;
import org.graphstream.ui.view.Viewer;

public class Test2 extends Application {
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();

        // These 3 lines show correctly a graph, ON A SEPARATE WINDOW
        //FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        //viewer = (FxViewer) graph.display(true);
        //viewer.enableAutoLayout();

        // Those below both do not work. I tried many vestions of gs-ui-javafx but ...
        //https://github.com/graphstream/gs-ui-javafx/blob/master/src-test/org/graphstream/ui/viewer_fx/test/AllFxTest.java
        //FxViewer fxviewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //FxGraphRenderer renderer = new FxGraphRenderer();
        //FxDefaultView view = (FxDefaultView) fxviewer.addView(FxViewer.DEFAULT_VIEW_ID, renderer);

        // https://github.com/graphstream/gs-ui-javafx/blob/master/src-test/org/graphstream/ui/viewer_fx/test/AllFxTest.java
        Graph graph = getGraph();
        FxViewer fxviewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        fxviewer.enableAutoLayout();
        FxViewPanel panel = (FxViewPanel) fxviewer.addDefaultView(false, new FxGraphRenderer());
        Scene scene2 = new Scene(panel, 800, 600);
        primaryStage.setScene(scene2);
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/sample2.fxml"));
        Scene scene = new Scene(root, 600, 575);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "javafx");
        //System.setProperty("org.graphstream.debug", "true");
        launch(args);
    }

}
