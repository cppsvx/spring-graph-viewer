package com.craneos.sgv.fx;

import com.craneos.sgv.GraphManager;
import com.craneos.sgv.fx.callbacks.PanelCallback;
import com.craneos.sgv.fx.controller.FXMLExampleController;
import com.craneos.sgv.integration.model.app.Step;
import com.craneos.sgv.integration.parser.IntegrationType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;

import java.util.Optional;

public class DetailsPanel extends AnchorPane implements PanelCallback {

    @Override
    public void refreshPanel(String id) {
        Optional<Node> node = this.getChildren().stream().filter(e->e.getId().equals("mprLabel1")).findFirst();
        Step step = GraphManager.getInstance().getNodes(id);
        IntegrationType type = step.getItem();
        String input = step.getInputChannel();
        String output = step.getOutputChannel();
        String discard = step.getOutputChannel();

        Label label = (Label)(node.get());
        label.setText(type.name());
    }

}
