package com.example.laboration3_3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Slider sliderSize;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Canvas canvas;
    @FXML
    private final double canvasWidth = 300;
    @FXML
    private final double canvasHeight = 300;
    @FXML
    private Label information;
    public Stage stage; //This needs to be public so HelloApplication can reach it
    Shape shape;
    Model model;

    SVG svg;
    GraphicsContext gc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shape = new Shape();
        model = new Model();
        svg = new SVG();
        gc = canvas.getGraphicsContext2D();
        whiteCanvas();
    }

    @FXML
    protected void drawRectangle() {
        canvas.setOnMouseClicked(e -> {
            shape.createRectangle(gc, e.getX(), e.getY(), sliderSize.getValue(),
                    colorPicker.getValue());
        });
    }

    @FXML
    protected void drawCircle() {
        canvas.setOnMouseClicked(e -> {
            shape.createCircle(gc, e.getX(), e.getY(), sliderSize.getValue(),
                    colorPicker.getValue());
        });
    }

    public void whiteCanvas () {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    public void unDo() {
        whiteCanvas();
        shape.removeLastAndDrawAllShapesInArray(gc);
    }

    public void clearCanvasAndArray() {
        whiteCanvas();
        model.clearArray();
    }

    public void changeOneShape(int i) {
        shape.changeOneShape(i, sliderSize.getValue(), colorPicker.getValue());
        whiteCanvas();
        shape.drawAllShapesInArray(gc);
    }

    public void detectOneShape() {

        /*
        Math formulas to determine if the mouseclick is on a shape.
        Rectangle - first formula. Circle - second formula.
        If a shape is clicked, the index of the shape is sent to another method where the color and size are changed
         */

        information.setText("Choose color and size, then press the shape you want to change.");

        List<Shape> temp = shape.returnArray();

        canvas.setOnMouseClicked(e ->{

            double x = e.getX();//
            double y = e.getY();//

            for (int i = 0; i < temp.size(); i++) {

                if (temp.get(i).getType() == 1) { //RECTANGLE
                    //These points draw out the rectangle
                    double xUpperRightCorner = temp.get(i).getX() + (temp.get(i).getWidth() / 2);
                    double xUpperLeftCorner = temp.get(i).getX() - (temp.get(i).getWidth() / 2);
                    double yLowerRightCorner = temp.get(i).getY() + (temp.get(i).getWidth() / 2);
                    double yLowerLeftCorner = temp.get(i).getY() - (temp.get(i).getWidth() / 2);

                    //If x is between UpperLeftCorner and UpperRightCorner
                    if (xUpperLeftCorner <= x && x <= xUpperRightCorner
                            //And if y between LowerLeftCorner and LowerRightCorner
                            && yLowerLeftCorner <= y && y <= yLowerRightCorner) {

                        System.out.println("You have clicked inside a shape.");
                        //The index is sent to this method, where the color and size can be changed
                        changeOneShape(i);
                    }
                }

                else if (temp.get(i).getType() == 2) { //CIRCLE

                    //Formula deconstructed: Inside a circle (https://study.com/skill/learn/determining-if-a-point-lies-inside-outside-or-on-a-circle-given-the-center-point-a-radius-explanation.html)
                    double xDiff = x - temp.get(i).getX();
                    double yDiff = y - temp.get(i).getY();
                    double sqrt = Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));

                    if (sqrt <= (temp.get(i).getWidth() / 2)) {
                        System.out.println("You have pressed on the circle.");
                        changeOneShape(i);
                    }
                }
            }
        });
    }

    public void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG","*.svg"));

        File file = fileChooser.showSaveDialog(stage);

        shape.translateDrawingToSvgString(file);

    }
}