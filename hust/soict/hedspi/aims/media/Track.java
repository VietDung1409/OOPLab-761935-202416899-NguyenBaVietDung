package hust.soict.hedspi.aims.media;

import java.util.Objects;

import hust.soict.hedspi.aims.exception.InvalidMediaException;
import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Track title must not be blank.");
        }
        if (length < 0) {
            throw new InvalidMediaException("Track length must be non-negative.");
        }
        this.title = title.trim();
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            String message = "Cannot play track \"" + getTitle()
                    + "\": length must be positive.";
            System.err.println(message);
            throw new PlayerException(message);
        }

        System.out.println("Playing Track: " + this.title);
        System.out.println("Track length: " + this.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Track)) return false;

        Track other = (Track) obj;
        return this.title != null
                && this.title.equals(other.title)
                && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }
}
