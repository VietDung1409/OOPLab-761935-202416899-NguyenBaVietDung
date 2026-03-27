import java.util.Scanner;

public class tinhtoancoban {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Nhap so thu nhat: ");
            double num1 = scanner.nextDouble();

            System.out.print("Nhap so thu hai: ");
            double num2 = scanner.nextDouble();

            System.out.println("Tong: " + (num1 + num2));
            System.out.println("Hieu: " + (num1 - num2));
            System.out.println("Tich: " + (num1 * num2));

            if (num2 != 0) {
                System.out.println("Thuong: " + (num1 / num2));
            } else {
                System.out.println("Khong the chia cho 0!");
            }
        }
    }
}
