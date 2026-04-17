package hust.soict.dsai.aims.media;
public class TestAIMS {
    public static void main(String[] args) {
        DigitalVideoDisc dvd = new DigitalVideoDisc(1, "Lion King", "Animation", "Roger", 90, 19.95);
        dvd.play();

        CompactDisc cd = new CompactDisc(2, "Hits", "Music", "Artist", "Dir", 15.5);
        cd.addTrack(new Track("Track 1", 5));
        cd.addTrack(new Track("Track 2", 7));
        cd.play();
    }
}