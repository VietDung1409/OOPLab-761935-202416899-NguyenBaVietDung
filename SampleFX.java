import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SampleFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label lbl = new Label("JavaFX is working!");
        StackPane root = new StackPane(lbl);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("SampleFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
