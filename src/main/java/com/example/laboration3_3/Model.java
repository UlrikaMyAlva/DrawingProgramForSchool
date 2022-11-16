package com.example.laboration3_3;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Shape> allDrawings = new ArrayList<>();

    public void addDrawingToArray (Shape shape) {
        allDrawings.add(shape);
        System.out.println(shape);
        System.out.println(allDrawings.size());
    }

    public void removeLastShapeFromArray() {
        allDrawings.remove(allDrawings.size() - 1);
    }
    public void clearArray() {
        allDrawings.clear();
    }
    public List<Shape> returnArray () {
        return allDrawings;
    }

    public void changeOneShape(int i, double size, Color color) {
        allDrawings.get(i).setWidth(size);
        allDrawings.get(i).setHeight(size);
        allDrawings.get(i).setColor(color);
    }

}

