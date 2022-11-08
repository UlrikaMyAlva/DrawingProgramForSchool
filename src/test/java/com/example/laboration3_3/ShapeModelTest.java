package com.example.laboration3_3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {

    @Test
    void addDrawingToArray() {
        ShapeModel model = new ShapeModel();
        int lengthOfArray = model.returnArray().size();
        model.addDrawingToArray(new ShapeModel());
        Assertions.assertEquals(lengthOfArray + 1, model.returnArray().size());
    }

    @Test
    void clearArray() {
        ShapeModel model = new ShapeModel();
        model.clearArray();
        Assertions.assertEquals(0, model.returnArray().size());
    }
}