package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, length, requireText(director, "DVD director"));
    }

    @Override
    public void play() {
        if (getLength() == 0) {
            throw new IllegalStateException("Cannot play a DVD with zero length.");
        }

        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength());
    }
}
