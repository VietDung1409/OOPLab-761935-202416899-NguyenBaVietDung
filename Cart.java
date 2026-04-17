import java.util.ArrayList;

public class Cart {
    private ArrayList<DigitalVideoDisc> itemsOrdered = new ArrayList<>();

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        itemsOrdered.add(disc);
        System.out.println("The disc \"" + disc.getTitle() + "\" has been added");
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        itemsOrdered.remove(disc);
        System.out.println("The disc has been removed");
    }

    public double totalCost() {
        double total = 0;
        for (DigitalVideoDisc d : itemsOrdered) {
            total += d.getCost();
        }
        return total;
    }

    // 🔥 In giỏ hàng
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        int i = 1;
        for (DigitalVideoDisc d : itemsOrdered) {
            System.out.println(i + ". " + d.toString());
            i++;
        }

        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***************************************************");
    }

    // 🔥 Search theo ID
    public void searchById(int id) {
        for (DigitalVideoDisc d : itemsOrdered) {
            if (d.getId() == id) {
                System.out.println("Found: " + d.toString());
                return;
            }
        }
        System.out.println("No matching disc found.");
    }

    // 🔥 Search theo title
    public void searchByTitle(String title) {
        boolean found = false;
        for (DigitalVideoDisc d : itemsOrdered) {
            if (d.isMatch(title)) {
                System.out.println("Found: " + d.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching disc found.");
        }
    }
}