package hust.soict.hedspi.test;
public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Sci-fi", "George Lucas", 124, 24.95);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", "Guy Ritchie", 90, 18.99);

        cart.addDigitalVideoDisc(dvd1);
        cart.addDigitalVideoDisc(dvd2);
        cart.addDigitalVideoDisc(dvd3);

        cart.print();

        // 🔍 test search
        System.out.println("\nSearch by ID:");
        cart.searchById(2);

        System.out.println("\nSearch by Title:");
        cart.searchByTitle("star");

        System.out.println("\nSearch not found:");
        cart.searchByTitle("abc");
    }
}