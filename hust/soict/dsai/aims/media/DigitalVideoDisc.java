package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(int id, String title, String category, String director, int length, double cost) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    @Override
    public void play() {
        if (length <= 0) {
            System.out.println("ERROR: DVD cannot be played");
            return;
        }
        System.out.println("Playing DVD: " + title);
        System.out.println("Length: " + length);
    }

    @Override
    public String toString() {
        return "DVD - " + title + " - " + category + " - " + director + " - " + length + ": " + cost + " $";
    }
}