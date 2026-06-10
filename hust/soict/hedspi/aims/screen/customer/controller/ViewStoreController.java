package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ViewStoreController {
    private static final int STORE_COLUMNS = 3;

    private Store store;

    @FXML
    private GridPane gridPane;

    public ViewStoreController(Store store) {
        this.store = store;
    }

    public void setStore(Store store) {
        this.store = store;
        loadStoreItems();
    }

    @FXML
    private void initialize() {
        loadStoreItems();
    }

    @FXML
    private void btnViewCartPressed(ActionEvent event) {
        System.out.println("View cart button pressed");
    }

    private void loadStoreItems() {
        if (gridPane == null || store == null) {
            return;
        }

        gridPane.getChildren().clear();

        for (int i = 0; i < store.getItems().size(); i++) {
            Media media = store.getItems().get(i);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml"));
                Node item = loader.load();

                ItemController itemController = loader.getController();
                itemController.setData(media);

                gridPane.add(item, i % STORE_COLUMNS, i / STORE_COLUMNS);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
