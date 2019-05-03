package com.jimmy9;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField bsize;

    boolean toolSelected = false;

    GraphicsContext brushTool;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        brushTool = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            try{
                double size = Double.parseDouble(bsize.getText());
                double x = e.getX() - size/2;
                double y = e.getY() - size/2;

                if(toolSelected && !bsize.getText().isEmpty()) {
                    brushTool.setFill(colorPicker.getValue());
                    brushTool.fillRoundRect(x, y, size, size, size, size);
                }
            } catch (NumberFormatException ex){
                System.out.println("\'Size\' field is empty.");
            }
        });
    }

    @FXML
    public void toolSelected(ActionEvent e){
        toolSelected = true;
    }
}
