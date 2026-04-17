package hust.soict.hedspi.aims.store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Sci-fi", "George Lucas", 124, 24.95);

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