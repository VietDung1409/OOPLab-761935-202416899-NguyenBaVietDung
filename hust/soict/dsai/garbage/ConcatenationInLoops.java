package hust.soict.dsai.garbage;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        int n = 50000;

        // 🔴 String (+)
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String (+): " + (end - start) + " ms");

        // 🟡 StringBuilder
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (end - start) + " ms");

        // 🟢 StringBuffer
        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sbf.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuffer: " + (end - start) + " ms");
    }
}