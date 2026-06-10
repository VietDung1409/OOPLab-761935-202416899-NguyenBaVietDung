package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class ViewStoreController {
    private static final int STORE_COLUMNS = 3;

    private final Store store;
    private final Cart cart;

    @FXML
    private GridPane gridPane;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        loadStoreItems();
    }

    @FXML
    private void btnViewCartPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == CartController.class) {
                    return new CartController(cart, store);
                }
                throw new IllegalArgumentException(
                        "Unsupported controller: " + controllerClass.getName());
            });
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot open the cart screen", exception);
        }
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
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == ItemController.class) {
                        return new ItemController(cart);
                    }
                    throw new IllegalArgumentException(
                            "Unsupported controller: " + controllerClass.getName());
                });
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
