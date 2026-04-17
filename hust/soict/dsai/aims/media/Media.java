package hust.soict.dsai.aims.media;

public abstract class Media {
    protected int id;
    protected String title;
    protected String category;
    protected double cost;

    public Media(int id, String title, String category, double cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public String getTitle() { return title; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return title + " - " + category + " - " + cost + " $";
    }
}