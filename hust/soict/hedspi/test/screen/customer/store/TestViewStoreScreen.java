package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {
    private final Store store = createStore();
    private final Cart cart = new Cart();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"));
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == ViewStoreController.class) {
                return new ViewStoreController(store, cart);
            }
            throw new IllegalArgumentException("Unsupported controller: " + controllerClass.getName());
        });

        Parent root = loader.load();
        stage.setTitle("AIMS Store");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Store createStore() {
        Store sampleStore = new Store();
        sampleStore.addMedia(new DigitalVideoDisc(
                1, "Inception", "Sci-Fi", "Nolan", 148, 19.9f));
        sampleStore.addMedia(new DigitalVideoDisc(
                2, "Avatar", "Action", "Cameron", 160, 25.0f));

        CompactDisc thriller = new CompactDisc(
                3, "Thriller", "Music", 15.0f, "Michael Jackson");
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

    public static void main(String[] args) {
        launch(args);
    }
}
