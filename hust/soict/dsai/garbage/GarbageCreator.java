package hust.soict.dsai.garbage;

public class GarbageCreator {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "garbage";
        }

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + " ms");
    }
}