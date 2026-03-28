import java.util.Scanner;

public class Bai6_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap thang: ");
        String input = sc.nextLine().trim().toLowerCase();

        System.out.print("Nhap nam: ");
        int year = sc.nextInt();
        int month = getMonth(input);
        if (month == -1 || year < 0) {
            System.out.println("Du lieu khong hop le!");
        } else {
            int days;

            if (month == 2) {
                if (isLeapYear(year)) days = 29;
                else days = 28;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                days = 30;
            } else {
                days = 31;
            }

            System.out.println("So ngay: " + days);
        }

        sc.close(); 
    }

    public static int getMonth(String input) {
        switch (input) {
            case "january": case "jan": case "jan.": case "1": return 1;
            case "february": case "feb": case "feb.": case "2": return 2;
            case "march": case "mar": case "mar.": case "3": return 3;
            case "april": case "apr": case "apr.": case "4": return 4;
            case "may": case "5": return 5;
            case "june": case "jun": case "6": return 6;
            case "july": case "jul": case "7": return 7;
            case "august": case "aug": case "aug.": case "8": return 8;
            case "september": case "sep": case "sept": case "9": return 9;
            case "october": case "oct": case "oct.": case "10": return 10;
            case "november": case "nov": case "nov.": case "11": return 11;
            case "december": case "dec": case "dec.": case "12": return 12;
            default: return -1;
        }
    }
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}