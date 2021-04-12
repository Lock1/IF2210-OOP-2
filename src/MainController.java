import Frontends.InventoryDialogController;
import Supports.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class MainController {
    @FXML private void handleOnInventoryClicked(MouseEvent event)
    {
        try {
            InventoryDialogController inventory = new InventoryDialogController();
            inventory.displayInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void startBattle(MouseEvent event) throws Exception{
        Parent battleWindow = FXMLLoader.load(getClass().getResource("battle_window.fxml"));
        ScreenSettings.pStage.setTitle("Battle Window");
        ScreenSettings.pStage.setScene(new Scene(battleWindow, (int) ScreenSettings.mediaSize.getWidth(), (int) ScreenSettings.mediaSize.getHeight() - 80));
        ScreenSettings.pStage.show();
    }
}
