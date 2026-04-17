package hust.soict.dsai.aims.media;

public class TestBook {
    public static void main(String[] args) {
        Book b = new Book(1, "Java Basics", "Programming", 10.5f);

        b.addAuthor("Nguyen Van A");
        b.addAuthor("Tran Van B");
        b.addAuthor("Nguyen Van A"); // test trùng

        System.out.println(b);

        b.removeAuthor("Tran Van B");
        b.removeAuthor("ABC"); // test không tồn tại

        System.out.println(b);
    }
}