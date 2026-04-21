package hust.soict.hedspi.aims.store;

public class Store {
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[100];
    private int qty = 0;

    // thêm DVD
    public void addDVD(DigitalVideoDisc disc) {
        if (qty < itemsInStore.length) {
            itemsInStore[qty] = disc;
            qty++;
            System.out.println("Added: " + disc.getTitle());
        } else {
            System.out.println("Store is full");
        }
    }

    // xóa DVD
    public void removeDVD(DigitalVideoDisc disc) {
        boolean found = false;
 (int i = 0; i < qty; i++) {
            if (itemsInStore[i] == disc) {
                found = true;

                // dồn mảng
                for (int j = i; j < qty - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }

                itemsInStore[qty - 1] = null;
                qty--;
                System.out.println("Removed: " + disc.getTitle());
                break;
            }
        }

        if (!found) {
            System.out.println("Disc not found");
        }
    }

    // in store
    public void printStore() {
        System.out.println("----- STORE -----");
        for (int i = 0; i < qty; i++) {
            System.out.println(itemsInStore[i]);
        }
    }
}