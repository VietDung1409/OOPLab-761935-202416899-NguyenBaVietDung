import java.util.Scanner;

public class hpt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chon loai phuong trinh:");
        System.out.println("1. Bac nhat 1 an (ax + b = 0)");
        System.out.println("2. He bac nhat 2 an");
        System.out.println("3. Bac hai 1 an (ax^2 + bx + c = 0)");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhap a, b: ");
                double a = sc.nextDouble(), b = sc.nextDouble();
                if (a == 0) {
                    if (b == 0) System.out.println("Phuong trinh vo so nghiem.");
                    else System.out.println("Phuong trinh vo nghiem.");
                } else System.out.println("Nghiem x = " + (-b / a));
                break;

            case 2:
                System.out.println("He: a11x + a12y = b1; a21x + a22y = b2");
                System.out.print("Nhap a11, a12, b1, a21, a22, b2: ");
                double a11 = sc.nextDouble(), a12 = sc.nextDouble(), b1 = sc.nextDouble();
                double a21 = sc.nextDouble(), a22 = sc.nextDouble(), b2 = sc.nextDouble();
                double D = a11 * a22 - a21 * a12;
                double D1 = b1 * a22 - b2 * a12;
                double D2 = a11 * b2 - a21 * b1;
                if (D != 0) System.out.println("Nghiem: x = " + (D1 / D) + ", y = " + (D2 / D));
                else if (D1 == 0 && D2 == 0) System.out.println("Vo so nghiem.");
                else System.out.println("Vo nghiem.");
                break;

            case 3:
                System.out.print("Nhap a, b, c: ");
                double A = sc.nextDouble(), B = sc.nextDouble(), C = sc.nextDouble();
                if (A == 0) {
                    if (B == 0) System.out.println(C == 0 ? "Vo so nghiem." : "Vo nghiem.");
                    else System.out.println("x = " + (-C / B));
                } else {
                    double delta = B * B - 4 * A * C;
                    if (delta < 0) System.out.println("Vo nghiem thuc.");
                    else if (delta == 0) System.out.println("Nghiem kep x = " + (-B / (2 * A)));
                    else {
                        double x1 = (-B + Math.sqrt(delta)) / (2 * A);
                        double x2 = (-B - Math.sqrt(delta)) / (2 * A);
                        System.out.println("x1 = " + x1 + ", x2 = " + x2);
                    }
                }
                break;
        }
        sc.close();
    }
}