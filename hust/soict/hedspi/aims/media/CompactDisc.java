package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.DuplicateItemException;
import hust.soict.hedspi.aims.exception.ItemNotFoundException;
import hust.soict.hedspi.aims.exception.PlayerException;

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
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            String message = "Cannot play CD \"" + getTitle()
                    + "\": total length must be positive.";
            System.err.println(message);
            throw new PlayerException(message);
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);

        for (Track t : tracks) {
            try {
                t.play();
            } catch (PlayerException exception) {
                System.err.println("Track error message: " + exception.getMessage());
                System.err.println("Track exception: " + exception.toString());
                exception.printStackTrace();

                String message = "Cannot play CD \"" + getTitle()
                        + "\" because track \"" + t.getTitle() + "\" cannot be played.";
                System.err.println(message);
                throw new PlayerException(message, exception);
            }
        }
    }
}
