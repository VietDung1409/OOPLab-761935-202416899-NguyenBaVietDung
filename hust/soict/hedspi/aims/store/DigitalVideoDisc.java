package hust.soict.hedspi.aims.store;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private double cost;

    public DigitalVideoDisc(String title, String category, String director, int length, double cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DVD - " + title + " - " + category + " - " 
             + director + " - " + length + ": " + cost + " $";
    }
}
