package hust.soict.hedspi.aims.media; 
public class TestCD {
    public static void main(String[] args) {
        CompactDisc cd = new CompactDisc(1, "Hits", "Music", 15.0f, "Artist A");

        Track t1 = new Track("Song 1", 3);
        Track t2 = new Track("Song 2", 4);

        cd.addTrack(t1);
        cd.addTrack(t2);
        try {
            cd.addTrack(t1); // test duplicate
        } catch (RuntimeException exception) {
            System.out.println("Handled: " + exception.getMessage());
        }

        System.out.println("Total length: " + cd.getLength());

        cd.play();
    }
}
