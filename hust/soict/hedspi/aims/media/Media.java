package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    // Constructor
    public Media() {}

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    // =========================
    // LAB 15: equals (unique item)
    // =========================
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Media)) return false;

        Media other = (Media) obj;
        return this.title != null && this.title.equals(other.title);
    }

    // =========================
    // LAB 16: toString (polymorphism)
    // =========================
    @Override
    public String toString() {
        return id + " - " + title + " - " + category + " - " + cost + "$";
    }

    // =========================
    // LAB 17: Comparators
    // =========================
    public static Comparator<Media> COMPARE_BY_TITLE_COST =
            new Comparator<Media>() {
                @Override
                public int compare(Media m1, Media m2) {
                    int titleCompare = m1.getTitle().compareTo(m2.getTitle());
                    if (titleCompare != 0) return titleCompare;
                    return Float.compare(m2.getCost(), m1.getCost());
                }
            };

    public static Comparator<Media> COMPARE_BY_COST_TITLE =
            new Comparator<Media>() {
                @Override
                public int compare(Media m1, Media m2) {
                    int costCompare = Float.compare(m2.getCost(), m1.getCost());
                    if (costCompare != 0) return costCompare;
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            };
}