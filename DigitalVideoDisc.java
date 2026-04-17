public class DigitalVideoDisc {
    private static int nbDigitalVideoDiscs = 0;

    private int id;
    private String title;    
    private String category; 
    private String director; 
    private int length;      
    private double cost;

    public DigitalVideoDisc(String title, String category, String director, int length, double cost) {
        this.id = ++nbDigitalVideoDiscs;
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDirector() { return director; }
    public int getLength() { return length; }
    public double getCost() { return cost; }

    // 🔥 toString() → return type là String
    @Override
    public String toString() {
        return "DVD - " + title + " - " + category + " - " 
             + director + " - " + length + ": " + cost + " $";
    }

    // 🔥 tìm theo title (không phân biệt hoa thường)
    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }
}