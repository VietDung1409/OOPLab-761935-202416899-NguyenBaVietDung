package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, length, requireText(director, "DVD director"));
    }

    @Override
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            String message = "Cannot play DVD \"" + getTitle()
                    + "\": length must be positive.";
            System.err.println(message);
            throw new PlayerException(message);
        }

        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength());
    }
}
