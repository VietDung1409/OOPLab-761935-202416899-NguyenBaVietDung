package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.DuplicateItemException;
import hust.soict.hedspi.aims.exception.InvalidMediaException;
import hust.soict.hedspi.aims.exception.ItemNotFoundException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class ExceptionHandlingTest {
    public static void main(String[] args) {
        expect(InvalidMediaException.class,
                () -> new Book(1, "", "Programming", 10),
                "blank media title");
        expect(InvalidMediaException.class,
                () -> new Book(1, "Java", "Programming", -1),
                "negative media cost");
        expect(InvalidMediaException.class,
                () -> new Track("Song", 0),
                "non-positive track length");

        Book book = new Book(2, "Clean Code", "Programming", 20);
        book.addAuthor("Robert C. Martin");
        expect(DuplicateItemException.class,
                () -> book.addAuthor("Robert C. Martin"),
                "duplicate author");
        expect(ItemNotFoundException.class,
                () -> book.removeAuthor("Unknown"),
                "missing author");

        CompactDisc cd = new CompactDisc(3, "Album", "Music", 15, "Artist");
        Track track = new Track("Track 1", 4);
        cd.addTrack(track);
        expect(DuplicateItemException.class,
                () -> cd.addTrack(track),
                "duplicate track");
        expect(ItemNotFoundException.class,
                () -> cd.removeTrack(new Track("Missing", 2)),
                "missing track");
        expect(IllegalStateException.class,
                () -> new CompactDisc(4, "Empty Album", "Music", 12, "Artist").play(),
                "playing an empty CD");

        Store store = new Store();
        DigitalVideoDisc dvd = new DigitalVideoDisc(
                5, "Inception", "Movie", "Nolan", 148, 19.9f);
        store.addMedia(dvd);
        expect(DuplicateItemException.class,
                () -> store.addMedia(dvd),
                "duplicate store media");

        Cart cart = new Cart();
        for (int i = 0; i < Cart.MAX_NUMBERS_ORDERED; i++) {
            cart.addMedia(new Book(100 + i, "Book " + i, "Test", 1));
        }
        expect(CartFullException.class,
                () -> cart.addMedia(new Book(999, "Overflow", "Test", 1)),
                "cart capacity");
        expect(ItemNotFoundException.class,
                () -> cart.removeMedia(dvd),
                "removing missing cart media");

        System.out.println("All exception handling tests passed.");
    }

    private static void expect(
            Class<? extends RuntimeException> expectedType,
            Runnable action,
            String scenario) {
        try {
            action.run();
        } catch (RuntimeException exception) {
            if (expectedType.isInstance(exception)) {
                System.out.println("PASS: " + scenario);
                return;
            }
            throw new AssertionError(
                    "Unexpected exception for " + scenario + ": " + exception, exception);
        }
        throw new AssertionError("Expected exception was not thrown for " + scenario);
    }
}
