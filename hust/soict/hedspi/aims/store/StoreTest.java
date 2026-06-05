package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Sci-fi", "George Lucas", 124, 24.95f);

        // test add
        store.addDVD(dvd1);
        store.addDVD(dvd2);

        store.printStore();

        // test remove
        System.out.println("\nAfter removing:");
        store.removeDVD(dvd1);
        store.printStore();
    }
}
