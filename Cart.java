package DigitalVideoDisc;
public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20; 
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full");
            return;
        }
        itemsOrdered[qtyOrdered++] = disc;
        System.out.println("The disc \"" + disc.getTitle() + "\" has been added");
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        int removedIndex = -1;
        for (int i = 0; i < qtyOrdered; ++i) {
            if (itemsOrdered[i] == disc) {
                removedIndex = i;
                break; 
            }
        }
        if (removedIndex == -1) {
            System.out.println("Disc not found!");
            return;
        }
        
        for (int i = removedIndex; i < qtyOrdered - 1; i++) {
            itemsOrdered[i] = itemsOrdered[i + 1];
        }
        itemsOrdered[qtyOrdered - 1] = null;
        qtyOrdered--;
        System.out.println("The disc has been removed");
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; ++i) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("--- Current Cart Items ---");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println((i + 1) + ". " + itemsOrdered[i].getTitle() + " - " + itemsOrdered[i].getCost() + " $");
        }
        System.out.println("Total Cost: " + totalCost() + " $");
        System.out.println("--------------------------");
    }
}