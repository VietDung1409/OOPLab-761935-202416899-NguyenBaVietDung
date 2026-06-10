package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.DuplicateItemException;
import hust.soict.hedspi.aims.exception.InvalidMediaException;
import hust.soict.hedspi.aims.exception.ItemNotFoundException;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Add author (không trùng)
    public void addAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new InvalidMediaException("Author name must not be blank.");
        }
        String normalizedName = authorName.trim();
        if (authors.contains(normalizedName)) {
            throw new DuplicateItemException("Author already exists: " + normalizedName);
        }
        authors.add(normalizedName);
        System.out.println("Added author: " + normalizedName);
    }

    // Remove author
    public void removeAuthor(String authorName) {
        if (authorName == null || !authors.remove(authorName.trim())) {
            throw new ItemNotFoundException("Author is not listed: " + authorName);
        }
        System.out.println("Removed author: " + authorName.trim());
    }

    @Override
    public String toString() {
        return "Book - " 
                + getTitle() + " - " 
                + getCategory() + " - Authors: " 
                + authors + " - " 
                + getCost() + " $";
    }
}
