package hust.soict.hedspi.aims.media;

public class TestBook {
    public static void main(String[] args) {
        Book b = new Book(1, "Java Basics", "Programming", 10.5f);

        b.addAuthor("Nguyen Van A");
        b.addAuthor("Tran Van B");
        try {
            b.addAuthor("Nguyen Van A"); // test duplicate
        } catch (RuntimeException exception) {
            System.out.println("Handled: " + exception.getMessage());
        }

        System.out.println(b);

        b.removeAuthor("Tran Van B");
        try {
            b.removeAuthor("ABC"); // test missing author
        } catch (RuntimeException exception) {
            System.out.println("Handled: " + exception.getMessage());
        }

        System.out.println(b);
    }
}
