import java.util.Arrays;
import java.util.Scanner;

public class Bai6_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so phan tu: ");
        int n = sc.nextInt();
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextDouble();
        }
        Arrays.sort(arr);
        double sum = 0;
        for (double x : arr) {
            sum += x;
        }
        double avg = sum / n;
        System.out.println("Mang sau khi sap xep: " + Arrays.toString(arr));
        System.out.println("Tong = " + sum);
        System.out.println("Trung binh = " + avg);
    }
}