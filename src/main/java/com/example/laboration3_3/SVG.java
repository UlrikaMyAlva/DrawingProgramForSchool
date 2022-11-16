package com.example.laboration3_3;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SVG {

    public void translateDrawingToSvgString(File file, List<Shape> allDrawings) {

        StringBuilder sb = new StringBuilder();

        sb.append("<svg version=\"1.1\" width=\"300\" height=\"300\" xmlns=\"http://www.w3.org/2000/svg\">\n");

        for (int i = 0; i < allDrawings.size(); i++) {
            if (allDrawings.get(i).getType() == 1) { //Rectangle
                sb.append("<rect x=\"").append(allDrawings.get(i).getX()).append("\" y=\"").append(allDrawings.get(i).getY())
                        .append("\" width=\"").append(allDrawings.get(i).getWidth())
                        .append("\" height=\"").append(allDrawings.get(i).getHeight())
                        .append("\" stroke=\"").append(toHexString(allDrawings.get(i).getColor()))
                        .append("\" fill=\"").append(toHexString(allDrawings.get(i).getColor()))
                        .append("\" stroke-width=\"1\"")
                        .append("/>\n");
            }
            else if (allDrawings.get(i).getType() == 2) { //Circle
                double radie = allDrawings.get(i).getHeight() / 2;
                sb.append("<circle cx=\"").append(allDrawings.get(i).getX()).append("\" cy=\"").append(allDrawings.get(i).getY())
                        .append("\" r=\"").append(radie)
                        .append("\" stroke=\"").append(toHexString(allDrawings.get(i).getColor()))
                        .append("\" fill=\"").append(toHexString(allDrawings.get(i).getColor()))
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

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

}
