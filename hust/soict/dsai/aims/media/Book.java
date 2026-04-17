package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private String category;
    private float cost;
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor
    public Book(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getter (không cần setter cho authors theo đề)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    // 🔥 Add author (không trùng)
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added author: " + authorName);
        } else {
            System.out.println("Author already exists");
        }
    }

    // 🔥 Remove author
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author not found");
        }
    }

    // In thông tin book
    @Override
    public String toString() {
        return "Book - " + title + " - " + category + " - Authors: " 
             + authors + " - " + cost + " $";
    }
}