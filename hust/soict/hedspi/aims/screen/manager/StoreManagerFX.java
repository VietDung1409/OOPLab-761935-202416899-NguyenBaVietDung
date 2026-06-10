package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Disc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StoreManagerFX extends Application {
    private final Store store = new Store();
    private final FlowPane mediaPane = new FlowPane();
    private int nextId = 6;

    @Override
    public void start(Stage primaryStage) {
        seedStore();

        BorderPane root = new BorderPane();
        root.setTop(createTopBar());
        root.setCenter(createStoreContent());

        Scene scene = new Scene(root, 980, 640);
        primaryStage.setTitle("AIMS Store - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        reloadStoreView();
    }

    private VBox createTopBar() {
        Label title = new Label("AIMS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Label storeLabel = new Label("Store");
        storeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label cartLabel = new Label("Cart");
        cartLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        HBox header = new HBox(24, storeLabel, title, cartLabel);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(16, 24, 12, 24));
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        return new VBox(createMenuBar(), header);
    }

    private MenuBar createMenuBar() {
        Menu updateStore = new Menu("Update Store");

        MenuItem addBook = new MenuItem("Add Book");
        addBook.setOnAction(event -> showAddBookDialog());

        MenuItem addCD = new MenuItem("Add CD");
        addCD.setOnAction(event -> showAddCDDialog());

        MenuItem addDVD = new MenuItem("Add DVD");
        addDVD.setOnAction(event -> showAddDVDDialog());

        updateStore.getItems().addAll(addBook, addCD, addDVD);
        return new MenuBar(updateStore);
    }

    private ScrollPane createStoreContent() {
        mediaPane.setPadding(new Insets(20));
        mediaPane.setHgap(16);
        mediaPane.setVgap(16);
        mediaPane.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(mediaPane);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private VBox createMediaCard(Media media) {
        Label title = new Label(media.getTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setWrapText(true);
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);

        Label type = new Label(media.getClass().getSimpleName());
        Label category = new Label(media.getCategory());
        Label cost = new Label(String.format("%.2f $", media.getCost()));

        VBox details = new VBox(4, type, category, cost);
        details.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(8);
        buttons.setAlignment(Pos.CENTER);

        Button detailsButton = new Button("Details");
        detailsButton.setOnAction(event -> showDetails(media));
        buttons.getChildren().add(detailsButton);

        if (media instanceof Playable) {
            Button playButton = new Button("Play");
            playButton.setOnAction(event -> showPlay(media));
            buttons.getChildren().add(playButton);
        }

        VBox card = new VBox(12, title, details, buttons);
        card.setPrefSize(220, 170);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(16));
        card.setStyle("-fx-border-color: #c9c9c9; -fx-border-radius: 4; -fx-background-color: white;");
        return card;
    }

    private void reloadStoreView() {
        mediaPane.getChildren().clear();
        for (Media media : store.getItems()) {
            mediaPane.getChildren().add(createMediaCard(media));
        }
    }

    private void showAddBookDialog() {
        TextField title = new TextField();
        TextField category = new TextField();
        TextField cost = new TextField();
        TextField authors = new TextField();

        Dialog<ButtonType> dialog = createFormDialog("Add Book",
                new String[] {"Title", "Category", "Cost", "Authors, comma separated"},
                new TextField[] {title, category, cost, authors});

        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    Book book = new Book(nextId, title.getText(), category.getText(), parseCost(cost.getText()));
                    for (String author : authors.getText().split(",")) {
                        if (!author.trim().isEmpty()) {
                            book.addAuthor(author.trim());
                        }
                    }
                    store.addMedia(book);
                    nextId++;
                    reloadStoreView();
                } catch (IllegalArgumentException | IllegalStateException exception) {
                    showError(exception.getMessage());
                }
            }
        });
    }

    private void showAddCDDialog() {
        TextField title = new TextField();
        TextField category = new TextField();
        TextField cost = new TextField();
        TextField artist = new TextField();

        Dialog<ButtonType> dialog = createFormDialog("Add CD",
                new String[] {"Title", "Category", "Cost", "Artist"},
                new TextField[] {title, category, cost, artist});

        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    CompactDisc cd = new CompactDisc(nextId, title.getText(), category.getText(),
                            parseCost(cost.getText()), artist.getText());
                    store.addMedia(cd);
                    nextId++;
                    reloadStoreView();
                } catch (IllegalArgumentException | IllegalStateException exception) {
                    showError(exception.getMessage());
                }
            }
        });
    }

    private void showAddDVDDialog() {
        TextField title = new TextField();
        TextField category = new TextField();
        TextField director = new TextField();
        TextField length = new TextField();
        TextField cost = new TextField();

        Dialog<ButtonType> dialog = createFormDialog("Add DVD",
                new String[] {"Title", "Category", "Director", "Length", "Cost"},
                new TextField[] {title, category, director, length, cost});

        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    DigitalVideoDisc dvd = new DigitalVideoDisc(nextId, title.getText(), category.getText(),
                            director.getText(), parseLength(length.getText()), parseCost(cost.getText()));
                    store.addMedia(dvd);
                    nextId++;
                    reloadStoreView();
                } catch (IllegalArgumentException | IllegalStateException exception) {
                    showError(exception.getMessage());
                }
            }
        });
    }

    private Dialog<ButtonType> createFormDialog(String title, String[] labels, TextField[] fields) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(16));

        for (int i = 0; i < labels.length; i++) {
            form.add(new Label(labels[i] + ":"), 0, i);
            form.add(fields[i], 1, i);
        }

        dialog.getDialogPane().setContent(form);
        return dialog;
    }

    private void showDetails(Media media) {
        showInfo("Media Details", media.toString());
    }

    private void showPlay(Media media) {
        if (media instanceof Disc) {
            Disc disc = (Disc) media;
            showInfo("Playing", media.getTitle() + "\nLength: " + disc.getLength());
        } else {
            showInfo("Playing", media.getTitle());
        }
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private float parseCost(String value) {
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Cost must be a valid number.", exception);
        }
    }

    private int parseLength(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Length must be a valid integer.", exception);
        }
    }

    private void seedStore() {
        store.addMedia(new DigitalVideoDisc(1, "Inception", "Sci-Fi", "Nolan", 148, 19.9f));
        store.addMedia(new DigitalVideoDisc(2, "Avatar", "Action", "Cameron", 160, 25.0f));

        CompactDisc thriller = new CompactDisc(3, "Thriller", "Music", 15.0f, "Michael Jackson");
        thriller.addTrack(new Track("Beat It", 4));
        thriller.addTrack(new Track("Billie Jean", 5));
        store.addMedia(thriller);

        Book cleanCode = new Book(4, "Clean Code", "Programming", 29.9f);
        cleanCode.addAuthor("Robert C. Martin");
        store.addMedia(cleanCode);

        Book effectiveJava = new Book(5, "Effective Java", "Programming", 35.0f);
        effectiveJava.addAuthor("Joshua Bloch");
        store.addMedia(effectiveJava);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
