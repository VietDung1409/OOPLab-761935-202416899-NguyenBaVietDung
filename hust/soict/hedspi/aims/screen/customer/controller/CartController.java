package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
    private final Cart cart;
    private final Store store;
    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    public CartController(Cart cart, Store store) {
        this.cart = cart;
        this.store = store;
    }

    @FXML
    private void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredMedia = new FilteredList<>(cart.getItems(), media -> true);
        tblMedia.setItems(filteredMedia);

        setButtonVisible(btnPlay, false);
        setButtonVisible(btnRemove, false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateButtonBar(newValue));
        tfFilter.textProperty().addListener(
                (observable, oldValue, newValue) -> showFilteredMedia());
        radioBtnFilterId.selectedProperty().addListener(
                (observable, oldValue, newValue) -> showFilteredMedia());
        radioBtnFilterTitle.selectedProperty().addListener(
                (observable, oldValue, newValue) -> showFilteredMedia());

        costLabel.textProperty().bind(Bindings.createStringBinding(
                () -> String.format("%.2f $", cart.totalCost()),
                cart.getItems()));
    }

    private void updateButtonBar(Media media) {
        boolean hasSelection = media != null;
        setButtonVisible(btnRemove, hasSelection);
        setButtonVisible(btnPlay, hasSelection && media instanceof Playable);
    }

    private void setButtonVisible(Button button, boolean visible) {
        button.setVisible(visible);
        button.setManaged(visible);
    }

    private void showFilteredMedia() {
        String filterText = tfFilter.getText().trim().toLowerCase();

        filteredMedia.setPredicate(media -> {
            if (filterText.isEmpty()) {
                return true;
            }
            if (radioBtnFilterId.isSelected()) {
                return Integer.toString(media.getId()).contains(filterText);
            }
            return media.getTitle() != null
                    && media.getTitle().toLowerCase().contains(filterText);
        });
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable) {
            ((Playable) selectedMedia).play();
            showMessage("Playing media", "Playing \"" + selectedMedia.getTitle() + "\".");
        }
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
        }
    }

    @FXML
    private void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == ViewStoreController.class) {
                    return new ViewStoreController(store, cart);
                }
                throw new IllegalArgumentException(
                        "Unsupported controller: " + controllerClass.getName());
            });
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot open the store screen", exception);
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItems().isEmpty()) {
            showMessage("Cart is empty", "Add media to the cart before placing an order.");
            return;
        }

        float total = cart.totalCost();
        cart.clear();
        showMessage("Order placed",
                String.format("Your order of %.2f $ has been placed successfully.", total));
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
