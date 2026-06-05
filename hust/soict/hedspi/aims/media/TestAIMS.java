package hust.soict.hedspi.aims.media;

public class TestAIMS {
    public static void main(String[] args) {
        // DigitalVideoDisc(id, title, category, director, length, cost)
        DigitalVideoDisc dvd = new DigitalVideoDisc(1, "Lion King", "Animation", "Roger", 90, 19.95f);
        dvd.play();

        // CompactDisc(id, title, category, cost, artist)
        CompactDisc cd = new CompactDisc(2, "Hits", "Music", 15.5f, "Artist");
        cd.addTrack(new Track("Track 1", 5));
        cd.addTrack(new Track("Track 2", 7));
        cd.play();
    }
}
