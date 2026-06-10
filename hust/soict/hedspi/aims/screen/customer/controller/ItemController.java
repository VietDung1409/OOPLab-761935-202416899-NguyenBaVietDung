package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemController {
    private final Cart cart;
    private Media media;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(String.format("%.2f $", media.getCost()));

        boolean canPlay = media instanceof Playable;
        btnPlay.setVisible(canPlay);
        btnPlay.setManaged(canPlay);
    }

    @FXML
    private void btnAddToCartClicked(ActionEvent event) {
        try {
            cart.addMedia(media);
            showMessage("Added to cart", "\"" + media.getTitle() + "\" was added to your cart.");
        } catch (CartFullException exception) {
            showMessage("Cart is full", exception.getMessage());
        }
    }

    @FXML
    private void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                showMessage("Playing media", "Playing \"" + media.getTitle() + "\".");
            } catch (PlayerException exception) {
                logPlayerException(exception);
                showMessage("Cannot play media", formatPlayerException(exception));
            }
        }
    }

    private void logPlayerException(PlayerException exception) {
        System.err.println(exception.getMessage());
        System.err.println(exception.toString());
        exception.printStackTrace();
    }

    private String formatPlayerException(PlayerException exception) {
        return exception.toString() + "\nMessage: " + exception.getMessage();
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
