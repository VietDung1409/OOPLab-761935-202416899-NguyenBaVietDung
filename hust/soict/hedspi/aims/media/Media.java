package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

import hust.soict.hedspi.aims.exception.InvalidMediaException;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    // Constructor
    public Media() {}

    public Media(int id, String title, String category, float cost) {
        setId(id);
        setTitle(title);
        setCategory(category);
        setCost(cost);
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new InvalidMediaException("Media ID must be non-negative.");
        }
        this.id = id;
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = requireText(title, "Media title");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = requireText(category, "Media category");
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (!Float.isFinite(cost) || cost < 0) {
            throw new InvalidMediaException("Media cost must be a finite, non-negative number.");
        }
        this.cost = cost;
    }

    protected static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidMediaException(fieldName + " must not be blank.");
        }
        return value.trim();
    }

    // =========================
    // LAB 15: equals (unique item)
    // =========================
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Media)) {
            return false;
        }

        Media other = (Media) obj;
        return title.equals(other.title)
                && Float.compare(cost, other.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    @Override
    public int compareTo(Media other) {
        Objects.requireNonNull(other, "Media to compare must not be null.");

        int titleComparison = title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        return Float.compare(cost, other.cost);
    }

    // =========================
    // LAB 16: toString (polymorphism)
    // =========================
    // isMatch: tìm theo title (không phân biệt hoa thường)
    public boolean isMatch(String searchTitle) {
        return searchTitle != null
                && this.title.toLowerCase().contains(searchTitle.toLowerCase());
    }

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
