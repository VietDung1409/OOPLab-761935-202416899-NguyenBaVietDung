package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML private Pane drawingAreaPane;
    @FXML private RadioButton penRadio;
    @FXML private RadioButton eraserRadio;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Mặc định màu mực là đen
        Color inkColor = Color.BLACK;

        // Nếu cục tẩy đang được chọn, đổi màu mực thành trắng
        if (eraserRadio.isSelected()) {
            inkColor = Color.WHITE;
        }

        // Tạo một hình tròn nhỏ tại vị trí con trỏ chuột
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, inkColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}