package com.craneos.sgv.integration.graph.graph;

import org.graphstream.ui.view.ViewerListener;

public class MyMouseManager implements ViewerListener {

    protected boolean loop = true;

    public MyMouseManager() {
    }

    public void viewClosed(String id) {
        loop = false;
    }

    public void buttonPushed(String id) {
        System.out.println("Button pushed on node "+id);
    }

    public void buttonReleased(String id) {
        System.out.println("Button released on node "+id);
    }

    @Override
    public void mouseOver(String s) {
        System.out.println("Button released2222 on node "+s);
    }

    @Override
    public void mouseLeft(String s) {
        System.out.println("Button released2222 on nodeaaaa "+s);
    }
}