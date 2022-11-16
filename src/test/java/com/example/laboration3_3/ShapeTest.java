package com.example.laboration3_3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShapeTest {

    @Test
    void addDrawingToArray() {
        Model model = new Model();
        int lengthOfArray = model.returnArray().size();
        model.addDrawingToArray(new Shape());
        Assertions.assertEquals(lengthOfArray + 1, model.returnArray().size());
    }

    @Test
    void clearArray() {
        Model list1 = new Model();

        //Stoppar in lite att ta bort
        Shape model = new Shape(10.0, 10.0, Color.BEIGE, 20.0, 20.0, 1);
        Shape model2 = new Shape(10.0, 10.0, Color.BEIGE, 20.0, 20.0, 1);
        Shape model3 = new Shape(10.0, 10.0, Color.BEIGE, 20.0, 20.0, 1);
        list1.addDrawingToArray(model);
        list1.addDrawingToArray(model2);
        list1.addDrawingToArray(model3);

        list1.clearArray();
        Assertions.assertEquals(0, list1.returnArray().size());
    }
}