package DigitalVideoDisc;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20; 
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    // --- PHƯƠNG THỨC GỐC (Thêm 1 đĩa) ---
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full. Cannot add: " + disc.getTitle());
            return;
        }
        itemsOrdered[qtyOrdered++] = disc;
        System.out.println("The disc \"" + disc.getTitle() + "\" has been added");
    }

    // --- PHẦN 14.1: Overloading bằng cách truyền MẢNG hoặc VARARGS ---
    
    /* 
       Ghi chú: Java coi DigitalVideoDisc[] và DigitalVideoDisc... là giống nhau về signature.
       Dưới đây mình dùng Varargs (...) vì nó bao hàm luôn cả mảng và linh hoạt hơn.
    */
    public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
        for (DigitalVideoDisc disc : dvdList) {
            if (qtyOrdered < MAX_NUMBERS_ORDERED) {
                addDigitalVideoDisc(disc); // Gọi lại phương thức gốc
            } else {
                System.out.println("The cart is almost full. Cannot add the rest of the list.");
                break;
            }
        }
    }

    // --- PHẦN 14.2: Overloading bằng cách truyền ĐÚNG 2 tham số ---
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1); // Thêm đĩa 1
        addDigitalVideoDisc(dvd2); // Thêm đĩa 2
    }


    // --- CÁC PHƯƠNG THỨC KHÁC GIỮ NGUYÊN ---
    
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
        System.out.println("\n--- Current Cart Items ---");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println((i + 1) + ". " + itemsOrdered[i].getTitle() + " - " + itemsOrdered[i].getCost() + " $");
        }
        System.out.println("Total Cost: " + totalCost() + " $");
        System.out.println("--------------------------\n");
    }
}