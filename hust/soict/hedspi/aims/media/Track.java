package hust.soict.hedspi.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() {
        if (this.length <= 0) {
            System.out.println("ERROR: Track length is non-positive!");
            return;
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
}