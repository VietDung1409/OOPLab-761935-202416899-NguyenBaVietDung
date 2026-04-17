package hust.soict.dsai.aims.media;

public class Track {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public int getLength() { return length; }

    public void play() {
        if (length <= 0) {
            System.out.println("ERROR: Track cannot be played");
            return;
        }
        System.out.println("Playing Track: " + title);
        System.out.println("Length: " + length);
    }
}