package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        cart.addMedia(media);
    }

    @FXML
    private void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            ((Playable) media).play();
        }
    }
}
