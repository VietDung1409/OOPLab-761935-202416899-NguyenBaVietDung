package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
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
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("Length: " + length);
    }

    @Override
    public String toString() {
        return "DVD - " 
                + getTitle() + " - " 
                + getCategory() + " - " 
                + director + " - " 
                + length + ": " 
                + getCost() + " $";
    }
}