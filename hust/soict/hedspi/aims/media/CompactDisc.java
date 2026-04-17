package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private String director;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, String artist, String director, double cost) {
        super(id, title, category, cost);
        this.artist = artist;
        this.director = director;
    }

    public void addTrack(Track t) {
        tracks.add(t);
    }

    public int getLength() {
        int total = 0;
        for (Track t : tracks) {
            total += t.getLength();
        }
        return total;
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("ERROR: CD cannot be played");
            return;
        }

        System.out.println("Playing CD: " + title);
        System.out.println("Total Length: " + getLength());

        for (Track t : tracks) {
            t.play();
        }
    }

    @Override
    public String toString() {
        return "CD - " + title + " - " + category + " - " + artist + " - " + getLength() + ": " + cost + " $";
    }
}