package hust.soict.hedspi.aims.test.media;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.*;

public class TestPolymorphism {
    public static void main(String[] args) {

        ArrayList<Media> mediaList = new ArrayList<>();

        Media dvd = new DigitalVideoDisc(1, "Inception", "Sci-Fi", "Nolan", 148, 19.9f);
        Media cd = new CompactDisc(2, "Hits", "Music", 15.0f, "Artist A");
        Media book = new Book(3, "Java Basics", "Education", 10.0f);

        mediaList.add(dvd);
        mediaList.add(cd);
        mediaList.add(book);

        for (Media m : mediaList) {
            System.out.println(m.toString());
        }
    }
}