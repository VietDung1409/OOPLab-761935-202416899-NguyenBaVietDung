package hust.soict.hedspi.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class MediaComparisonTest {
    public static void main(String[] args) {
        Media book = new Book(1, "Java", "Book", 10);
        Media sameTitleAndCost = new DigitalVideoDisc(
                2, "Java", "Movie", "Director", 90, 10);
        Media sameTitleDifferentCost = new Book(3, "Java", "Book", 15);
        Media differentTitle = new Book(4, "Algorithms", "Book", 20);

        assertTrue(book.equals(sameTitleAndCost),
                "Media with the same title and cost must be equal.");
        assertTrue(book.hashCode() == sameTitleAndCost.hashCode(),
                "Equal media must have equal hash codes.");
        assertTrue(!book.equals(sameTitleDifferentCost),
                "Media with different costs must not be equal.");
        assertTrue(!book.equals(differentTitle),
                "Media with different titles must not be equal.");
        assertTrue(!book.equals(null), "Media must not equal null.");
        assertTrue(!book.equals("Java"), "Media must not equal another object type.");

        assertTrue(book.compareTo(sameTitleAndCost) == 0,
                "compareTo must be consistent with equals.");
        assertTrue(book.compareTo(sameTitleDifferentCost) < 0,
                "Cost must break ties between equal titles.");

        List<Media> media = new ArrayList<>();
        media.add(book);
        media.add(sameTitleDifferentCost);
        media.add(differentTitle);
        Collections.sort(media);
        assertTrue(media.get(0) == differentTitle,
                "Natural ordering must sort by title first.");

        try {
            book.compareTo(null);
            throw new AssertionError("compareTo(null) must throw NullPointerException.");
        } catch (NullPointerException exception) {
            System.out.println("PASS: compareTo null check");
        }

        System.out.println("All media comparison tests passed.");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
        System.out.println("PASS: " + message);
    }
}
