package hust.soict.hedspi.aims;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

public class Aims {

    static Store store = new Store();
    static Cart cart = new Cart();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //  Sample data
        initStore();

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCart();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= MAIN MENU =================
    public static void showMenu() {
        System.out.println("\nAIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Choose: ");
    }

    // ================= STORE MENU =================
    public static void storeMenu() {
        System.out.println("\nSTORE MENU:");
        System.out.println("--------------------------------");
        System.out.println("1. See media details");
        System.out.println("2. Add media to cart");
        System.out.println("3. Play media");
        System.out.println("4. See cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Choose: ");
    }

    // ================= MEDIA MENU =================
    public static void mediaDetailsMenu() {
        System.out.println("\nMEDIA MENU:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Choose: ");
    }

    // ================= CART MENU =================
    public static void cartMenu() {
        System.out.println("\nCART MENU:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media");
        System.out.println("2. Sort media");
        System.out.println("3. Remove media");
        System.out.println("4. Play media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Choose: ");
    }

    // ================= VIEW STORE =================
    public static void viewStore() {
        store.print();

        storeMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                seeMediaDetails();
                break;
            case 2:
                addMediaToCart();
                break;
            case 3:
                playMediaFromStore();
                break;
            case 4:
                seeCart();
                break;
        }
    }

    // ================= UPDATE STORE =================
    public static void updateStore() {
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            store.addMedia(new DigitalVideoDisc(0, title, "Unknown", "Unknown", 0, 0));
        } else {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            store.removeMedia(new DigitalVideoDisc(0, title, "", "", 0, 0));
        }
    }

    // ================= CART =================
    public static void seeCart() {
        cart.print();

        cartMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                filterCart();
                break;
            case 2:
                sortCart();
                break;
            case 3:
                removeFromCart();
                break;
            case 4:
                playFromCart();
                break;
            case 5:
                System.out.println("Order placed!");
                cart = new Cart();
                break;
        }
    }

    // ================= ADD TO CART =================
    public static void addMediaToCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (Media m : store.getItems()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                cart.addMedia(m);
                return;
            }
        }

        System.out.println("Media not found!");
    }

    // ================= PLAY =================
    public static void playFromCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (Media m : cart.getItems()) {
            if (m.getTitle().equalsIgnoreCase(title) && m instanceof Playable) {
                ((Playable) m).play();
                return;
            }
        }

        System.out.println("Cannot play this media!");
    }

    public static void playMediaFromStore() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (Media m : store.getItems()) {
            if (m.getTitle().equalsIgnoreCase(title) && m instanceof Playable) {
                ((Playable) m).play();
                return;
            }
        }

        System.out.println("Cannot play this media!");
    }

    // ================= SORT =================
    public static void sortCart() {
        System.out.println("1. Sort by title-cost");
        System.out.println("2. Sort by cost-title");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Collections.sort(cart.getItems(), Media.COMPARE_BY_TITLE_COST);
        } else {
            Collections.sort(cart.getItems(), Media.COMPARE_BY_COST_TITLE);
        }
    }

    // ================= FILTER =================
    public static void filterCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (Media m : cart.getItems()) {
            if (m.isMatch(title)) {
                System.out.println(m);
            }
        }
    }

    // ================= REMOVE =================
    public static void removeFromCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        cart.getItems().removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

    // ================= MEDIA DETAILS =================
    public static void seeMediaDetails() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        for (Media m : store.getItems()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                System.out.println(m);

                mediaDetailsMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) cart.addMedia(m);
                if (choice == 2 && m instanceof Playable)
                    ((Playable) m).play();

                return;
            }
        }

        System.out.println("Not found!");
    }

    // ================= SAMPLE DATA =================
    public static void initStore() {
        store.addMedia(new DigitalVideoDisc(1, "Inception", "Movie", "Nolan", 148, 19.9f));
        store.addMedia(new DigitalVideoDisc(2, "Avatar", "Movie", "Cameron", 160, 25.0f));
        store.addMedia(new CompactDisc(3, "Hits", "Music", 15.0f, "Artist A"));
    }
}