package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    // Thêm Media (DVD, Book, CD)
    public void addMedia(Media media) {
        itemsOrdered.add(media);
        System.out.println("The media \"" + media.getTitle() + "\" has been added");
    }

    // Xóa Media
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed");
        } else {
            System.out.println("The media is not in the cart");
        }
    }

    // Tổng tiền
    public double totalCost() {
        double total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    // In giỏ hàng
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        int i = 1;
        for (Media m : itemsOrdered) {
            System.out.println(i + ". " + m.toString());
            i++;
        }

        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***************************************************");
    }

    // Search theo ID
    public void searchById(int id) {
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                System.out.println("Found: " + m.toString());
                return;
            }
        }
        System.out.println("No matching media found.");
    }

    // Search theo title
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.isMatch(title)) {
                System.out.println("Found: " + m.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching media found.");
        }
    }
}