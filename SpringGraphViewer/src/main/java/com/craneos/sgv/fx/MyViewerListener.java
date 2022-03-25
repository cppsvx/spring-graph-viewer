package com.craneos.sgv.fx;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.ProxyPipe;
import org.graphstream.ui.fx_viewer.FxDefaultView;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;

import javafx.event.EventHandler;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.ui.view.util.InteractiveElement;

import java.util.EnumSet;
import java.util.Optional;
/*
public class MyViewerListener implements ViewerListener, EventHandler {

    private Graph graph;
    private FxDefaultView fxDefaultView;
    private Viewer viewer;

    public MyViewerListener(Graph graph, FxDefaultView defaultView, Viewer viewer){
        this.graph = graph;
        this.fxDefaultView = defaultView;
        this.viewer = viewer;
    }

    @Override
    public void handle(Event event) {
        EventType type = event.getEventType();
        if (event instanceof MouseEvent){
            if (type==MouseEvent.MOUSE_ENTERED_TARGET){
                //System.out.println("MOUSE_ENTERED_TARGET");
            } else if (type==MouseEvent.MOUSE_MOVED){
                //System.out.println("MOUSE_MOVED");
            } else if (type==MouseEvent.MOUSE_CLICKED){
                System.out.println("MOUSE_CLICKED");
            } else if (type==MouseEvent.MOUSE_PRESSED){
                System.out.println("MOUSE_PRESSED");
                double x = ((MouseEvent) event).getScreenX();
                double y = ((MouseEvent) event).getScreenY();
                double xx = ((MouseEvent) event).getSceneX();
                double yy = ((MouseEvent) event).getSceneY();

                graph.nodes().filter(n -> n.hasAttribute("ui.selected")).forEach(n -> n.removeAttribute("ui.selected"));
/**********************
                View view = fxDefaultView.getViewer().addDefaultView(false);
                //GraphicElement ge = view.findGraphicElementAt(EnumSet.of(InteractiveElement.NODE), xx, yy);
                //GraphicElement ge = view.getCamera().findGraphicElementAt(viewer.getGraphicGraph(), EnumSet.of(InteractiveElement.NODE), xx, yy);
                Viewer viewer = graph.display();
// Create a pipe coming from the viewer ...
                ProxyPipe pipe = viewer.newViewerPipe();
// ... and connect it to the graph
                pipe.addAttributeSink(graph);
                pipe.pump();

                // in the development version the previous two instructions can be replaced by
                // pipe.blockingPump();
                graph.nodes().forEach(n -> { // Only in GraphStream 2.0, otherwise use the traditional loop in lower versions
                    double[] relative = Toolkit.nodePosition(n);
                    double[] xyz = Toolkit.nodePosition(graph, n.getId());
                    Point3 relative2 = GraphPosLengthUtils.nodePointPosition(n);
                    System.out.println("Relative = "+relative[0]+" "+relative[1]);
                    System.out.println("Relative2 = "+relative2.toString()+" "+relative2.x);
                    //Point3 p2 = fxDefaultView.getCamera().getViewCenter();
                    //defaultView.getCamera()
                    Optional<Node> node = fxDefaultView.getViewer().getGraphicGraph().nodes().findFirst();


                    double[] relative3 = Toolkit.nodePosition(node.get());
                    double[] xyz2 = Toolkit.nodePosition(graph, node.get().getId());
                    System.out.println("relative3 = "+relative3);
                    System.out.println("xyz2 = "+xyz2);
                });
                // now "xyz" attributes of the nodes are updated and we can use them, for example
//

                System.out.println("MOUSE_PRESSED_2");
            } else if (type==MouseEvent.MOUSE_RELEASED){
                System.out.println("MOUSE_RELEASED");
            } else if (type==MouseEvent.MOUSE_ENTERED) {
                System.out.println("MOUSE_ENTERED");
            } else if (type==MouseEvent.MOUSE_DRAGGED) {
                System.out.println("MOUSE_DRAGGED");
            } else if (type==MouseEvent.MOUSE_EXITED) {
                //System.out.println("MOUSE_EXITED");
            } else if (type==MouseEvent.MOUSE_EXITED_TARGET) {
                //System.out.println("MOUSE_EXITED_TARGET");
            } else {
                System.out.println("aaaaa");
            }
        } else if (event instanceof KeyEvent){
            if (type==KeyEvent.KEY_TYPED){
                System.out.println("32");
            } else if (type==KeyEvent.KEY_RELEASED){
                System.out.println("444");
            } else {
                System.out.println("aaaa");
            }
        }
    }

    @Override
    public void viewClosed(String s) {
        System.out.println("viewClosed");
    }

    @Override
    public void buttonPushed(String s) {
        System.out.println("buttonPushed");
    }

    @Override
    public void buttonReleased(String s) {
        System.out.println("buttonReleased");
    }

    @Override
    public void mouseOver(String s) {
        System.out.println("mouseOver");
    }

    @Override
    public void mouseLeft(String s) {
        System.out.println("mouseLeft");
    }


}***/
