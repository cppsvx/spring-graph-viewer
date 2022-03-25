package com.craneos.sgv.fx;

import com.craneos.sgv.fx.callbacks.PanelCallback;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.graphstream.ui.fx_viewer.util.FxMouseManager;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.view.util.InteractiveElement;

import java.util.EnumSet;

public class SgvFxMouseManager extends FxMouseManager {

    private PanelCallback panelCallback;

    public SgvFxMouseManager(EnumSet<InteractiveElement> types, PanelCallback panelCallback) {
        super(types);
        this.panelCallback = panelCallback;
    }

    @Override
    protected void mouseButtonPressOnElement(GraphicElement element, MouseEvent event) {
        view.freezeElement(element, true);
        if (event.getButton() == MouseButton.SECONDARY) {
            element.setAttribute("ui.selected");
        } else {
            element.setAttribute("ui.clicked");
            panelCallback.refreshPanel(element.getId());
        }
    }

}
