package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, double cost) {
        super(id, title, category, cost);
    }

    public void addAuthor(String author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }

    @Override
    public String toString() {
        return "Book - " + title + " - " + category + " - Authors: " + authors + " - " + cost + " $";
    }
}