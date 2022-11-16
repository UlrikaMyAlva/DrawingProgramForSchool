package com.example.laboration3_3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Shape {

    private double x;
    private double y;
    private Color color;
    private double height;
    private double width;
    private int type; //Make enum?

    Model model = new Model(); //Can I Initialize here?

    //private static List<ShapeModel> allDrawings = new ArrayList<>();

    public Shape() {
        //Default constructor
        //Can I use this as a Initialize?
    }
    public Shape(double x, double y, Color color, double height, double width, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.height = height;
        this.width = width;
        this.type = type;
    }

    //Drawing methods
    public void drawRectangle(GraphicsContext gc, double x, double y, double size, Color color ) {
        gc.setFill(color);
        gc.fillRect(x - (size/2), y - (size/2), size, size); //(size/2) to make the cursor in the middle of the shape where you press the canvas
    }

    public void drawCircle(GraphicsContext gc, double x, double y, double size, Color color) {
        gc.setFill(color);
        gc.fillOval(x - (size / 2), y - (size / 2), size, size);
    }

    //Creates and draws the shapes
    public void createRectangle(GraphicsContext gc, double x, double y, double size, Color color ) {
        drawRectangle(gc, x, y, size, color);
        Shape temp = new Shape(x, y, color, size, size, 1);
        model.addDrawingToArray(temp);
    }

    public void createCircle(GraphicsContext gc, double x, double y, double size, Color color) {
        drawCircle(gc, x, y, size, color);
        Shape temp = new Shape(x, y, color, size, size, 2);
        model.addDrawingToArray(temp);
    }

    public void removeLastAndDrawAllShapesInArray(GraphicsContext gc) {
        model.removeLastShapeFromArray();
        drawAllShapesInArray(gc);
    }
    public void drawAllShapesInArray(GraphicsContext gc) {
        for (Shape shape : model.returnArray()) {
            if (shape.type == 1) {
                drawRectangle(gc, shape.x, shape.y, shape.height, shape.color);
            }
            else if (shape.type == 2) {
                drawCircle(gc, shape.x, shape.y, shape.height, shape.color);
            }
        }
    }

    public void clearArray() {
        model.clearArray();
    }
    public List<Shape> returnArray () {
        return model.returnArray();
    }

    public void changeOneShape(int i, double size, Color color) {
        model.changeOneShape(i, size, color);
    }

    public String toString() {
        return x + " " + y + " " + color + " " + height + " " + width + " " + type;
    }

    //SVG Method
    public void translateDrawingToSvgString(File file) {
        SVG svg = new SVG();
        svg.translateDrawingToSvgString(file, model.returnArray());
    }


    //THESE TWO METHODS CONVERTS THE COLOR, Cred https://stackoverflow.com/questions/17925318/how-to-get-hex-web-string-from-javafx-colorpicker-color/56733608
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                    .toUpperCase();
    }

    //GETTERS AND SETTERS
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}


