package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
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

    public ViewStoreController() {
        this.store = createSampleStore();
    }

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

    private Store createSampleStore() {
        Store sampleStore = new Store();
        sampleStore.addMedia(new DigitalVideoDisc(1, "Inception", "Sci-Fi", "Nolan", 148, 19.9f));
        sampleStore.addMedia(new DigitalVideoDisc(2, "Avatar", "Action", "Cameron", 160, 25.0f));

        CompactDisc thriller = new CompactDisc(3, "Thriller", "Music", 15.0f, "Michael Jackson");
        thriller.addTrack(new Track("Beat It", 4));
        thriller.addTrack(new Track("Billie Jean", 5));
        sampleStore.addMedia(thriller);

        Book cleanCode = new Book(4, "Clean Code", "Programming", 29.9f);
        cleanCode.addAuthor("Robert C. Martin");
        sampleStore.addMedia(cleanCode);

        Book effectiveJava = new Book(5, "Effective Java", "Programming", 35.0f);
        effectiveJava.addAuthor("Joshua Bloch");
        sampleStore.addMedia(effectiveJava);

        return sampleStore;
    }
}
