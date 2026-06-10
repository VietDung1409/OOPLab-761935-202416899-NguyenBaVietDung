package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.DuplicateItemException;
import hust.soict.hedspi.aims.exception.ItemNotFoundException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, String artist) {
        super(id, title, category, cost, 0, "");
        this.artist = requireText(artist, "CD artist");
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null.");
        }
        if (tracks.contains(track)) {
            throw new DuplicateItemException("Track already exists: " + track.getTitle());
        }
        tracks.add(track);
        System.out.println("Added track: " + track.getTitle());
    }

    public void removeTrack(Track track) {
        if (track == null || !tracks.remove(track)) {
            throw new ItemNotFoundException("Track is not listed.");
        }
        System.out.println("Removed track: " + track.getTitle());
    }

    @Override
    public int getLength() {
        int total = 0;
        for (Track t : tracks) {
            total += t.getLength();
        }
        return total;
    }

    @Override
    public void play() {
        if (tracks.isEmpty()) {
            throw new IllegalStateException("Cannot play a CD without tracks.");
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);

        for (Track t : tracks) {
            t.play(); 
        }
    }
}
