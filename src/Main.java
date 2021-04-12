import Supports.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreenSettings.pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        ScreenSettings.pStage.setTitle("ManggaMon");
        ScreenSettings.pStage.setScene(new Scene(root, (int)ScreenSettings.mediaSize.getWidth(), (int)ScreenSettings.mediaSize.getHeight() - 80));
        ScreenSettings.pStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
