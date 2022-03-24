package com.craneos.sgv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowApplication extends Application {

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "javafx");
        //System.setProperty("org.graphstream.debug", "true");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/sample2.fxml"));
        Scene scene = new Scene(root, 600, 575);
        //
        primaryStage.setTitle("Spring Graph Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
