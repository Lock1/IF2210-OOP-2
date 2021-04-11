package Frontends;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryDialogController {

    public void displayInventory() throws Exception{
        Stage inventoryStage = new Stage();

        inventoryStage.initModality(Modality.APPLICATION_MODAL);

        Parent root = FXMLLoader.load(getClass().getResource("inventory_dialog.fxml"));
        inventoryStage.setTitle("Inventory Window");
        inventoryStage.setScene(new Scene(root, 300, 275));
        inventoryStage.show();
    }
}
