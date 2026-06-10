package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.InvalidMediaException;

public class Disc extends Media {
    private int length;
    private String director;

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        if (length < 0) {
            throw new InvalidMediaException("Disc length must be non-negative.");
        }
        this.length = length;
        this.director = director == null ? "" : director.trim();
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
}
