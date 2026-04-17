package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, length, director);
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("ERROR: DVD cannot be played");
            return;
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("Length: " + getLength());
    }
}