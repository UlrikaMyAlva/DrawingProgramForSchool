package com.example.laboration3_3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShapeModel {

    private double x;
    private double y;
    private Color color;
    private double height;
    private double width;
    private int type; //Make enum?

    private static List<ShapeModel> allDrawings = new ArrayList<>();

    public ShapeModel () {
        //Default constructor
    }
    public ShapeModel(double x, double y, Color color, double height, double width, int type) {
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
        ShapeModel temp = new ShapeModel(x, y, color, size, size, 1);
        addDrawingToArray(temp);
    }

    public void createCircle(GraphicsContext gc, double x, double y, double size, Color color) {
        drawCircle(gc, x, y, size, color);
        ShapeModel temp = new ShapeModel(x, y, color, size, size, 2);
        addDrawingToArray(temp);
    }

    public void drawAllShapesInArray(GraphicsContext gc) {
        for (ShapeModel shape : allDrawings) {
            if (shape.type == 1) {
                drawRectangle(gc, shape.x, shape.y, shape.height, shape.color);
            }
            else if (shape.type == 2) {
                drawCircle(gc, shape.x, shape.y, shape.height, shape.color);
            }
        }
    }

    //Methods that manipulate the array
    public void addDrawingToArray (ShapeModel shape) {
        allDrawings.add(shape);
    }
    public void removeLastShapeFromArray() {
        allDrawings.remove(allDrawings.size() - 1);
    }
    public void clearArray() {
        allDrawings.clear();
    }
    public List<ShapeModel> returnArray () {
        return allDrawings;
    }

    public void changeOneShape(int i, double size, Color color) {
        allDrawings.get(i).setWidth(size);
        allDrawings.get(i).setHeight(size);
        allDrawings.get(i).setColor(color);
    }

    public String toString() {
        return x + " " + y + " " + color + " " + height + " " + width + " " + type;
    }

    //SVG Method
    public void translateDrawingToSvgString(File file) {

        StringBuilder sb = new StringBuilder();

        sb.append("<svg version=\"1.1\" width=\"300\" height=\"300\" xmlns=\"http://www.w3.org/2000/svg\">\n");

        for (int i = 0; i < allDrawings.size(); i++) {
            if (allDrawings.get(i).type == 1) { //Rectangle
                sb.append("<rect x=\"").append(allDrawings.get(i).x).append("\" y=\"").append(allDrawings.get(i).y)
                        .append("\" width=\"").append(allDrawings.get(i).getWidth())
                        .append("\" height=\"").append(allDrawings.get(i).getHeight())
                        .append("\" stroke=\"").append(toHexString(allDrawings.get(i).color))
                        .append("\" fill=\"").append(toHexString(allDrawings.get(i).color))
                        .append("\" stroke-width=\"1\"")
                        .append("/>\n");
            }
            else if (allDrawings.get(i).type == 2) { //Circle
                double radie = allDrawings.get(i).height / 2;
                sb.append("<circle cx=\"").append(allDrawings.get(i).x).append("\" cy=\"").append(allDrawings.get(i).y)
                        .append("\" r=\"").append(radie)
                        .append("\" stroke=\"").append(toHexString(allDrawings.get(i).color))
                        .append("\" fill=\"").append(toHexString(allDrawings.get(i).color))
                        .append("\" stroke-width=\"1\"")
                        .append("/>\n");
            }
        }

        sb.append("</svg>");
        System.out.println(sb);

        System.out.println(sb);

        try {
            FileWriter writeFile = new FileWriter(file);

            writeFile.write(sb.toString());

            writeFile.close();
            System.out.println("Successfully wrote to file!");

        } catch (IOException e) {
                System.out.println("Error.");
                e.printStackTrace();
        }
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


