package com.example.laboration3_3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    /* DETTA HAR JAG GJORT EFTER DEMON
    - Dela på Shape och Model - Shape innehåller bara formen, Model innehåller Listan. Du ritar fortfarande i
    Shape.
    - Gör en SVG klass separat.
    - Skriv in några shapes att ta bort i test 2.
    */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        Controller controller = fxmlLoader.getController();
        controller.stage = stage;
        stage.setTitle("Drawer");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}