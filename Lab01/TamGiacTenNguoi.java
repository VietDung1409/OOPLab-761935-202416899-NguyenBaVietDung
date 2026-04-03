import javax.swing.*;

public class TamGiacTenNguoi {
    public static void main(String[] args) {

        // Giả sử đặt sẵn tên người
        String An = "An";      
        String Binh = "Binh";  
        String Cuong = "Cuong";
        int AnC = An.length();
        int BinhC = Binh.length();
        int CuongC = Cuong.length();
        if (AnC + BinhC > CuongC && AnC + CuongC > BinhC && BinhC + CuongC > AnC) {
            double p = (AnC + BinhC + CuongC) / 2.0;
            double dienTich = Math.sqrt(p * (p - AnC) * (p - BinhC) * (p - CuongC));

            JOptionPane.showMessageDialog(null,
                    "Do dai canh: " + AnC + ", " + BinhC + ", " + CuongC +
                    "\nDien tich: " + String.format("%.2f", dienTich));
        } else {
            JOptionPane.showMessageDialog(null, "Khong tao thanh tam giac!");
        }
    }
}