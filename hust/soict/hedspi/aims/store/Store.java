package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    // Thêm Media (DVD, Book, CD đều được)
    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println("The media \"" + media.getTitle() + "\" has been added to the store");
    }

    // Alias cho StoreTest (backward compat)
    public void addDVD(hust.soict.hedspi.aims.media.DigitalVideoDisc dvd) {
        addMedia(dvd);
    }

    // Xóa Media
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the store");
        } else {
            System.out.println("The media is not in the store");
        }
    }

    // Alias cho StoreTest
    public void removeDVD(hust.soict.hedspi.aims.media.DigitalVideoDisc dvd) {
        removeMedia(dvd);
    }

    // Lấy danh sách items
    public ArrayList<Media> getItems() {
        return itemsInStore;
    }

    // In danh sách store
    public void print() {
        System.out.println("***************STORE***************");
        for (Media m : itemsInStore) {
            System.out.println(m.toString());
        }
        System.out.println("***********************************");
    }

    // Alias cho StoreTest
    public void printStore() {
        print();
    }
}