import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotDefteri {
    private Connection connection;

    public NotDefteri() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kullaniciDB", "root", "");
            System.out.println("Veritabanı bağlantısı başarılı!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcıya ait notları almak
    public List<String> kullaniciNotlariGetir(String kullaniciAdi) {
        List<String> notlarListesi = new ArrayList<>();
        String query = "SELECT notlar FROM kullanici WHERE kullaniciAdi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kullaniciAdi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String notlar = rs.getString("notlar");
                if (notlar != null && !notlar.isEmpty()) {
                    String[] notlarArray = notlar.split("\n");
                    for (String not : notlarArray) {
                        notlarListesi.add(not.trim());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notlarListesi;
    }

    // Not eklemek için kullanılan metod
    public void notEkle(String kullaniciAdi, String yeniNot, String notTipi) {
        String notIcerigi = notTipi.equals("Önemli") ? "[!ÖNEMLİ!] " + yeniNot : yeniNot;
        String query = "UPDATE kullanici SET notlar = CONCAT(IFNULL(notlar, ''), ?) WHERE kullaniciAdi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "\n" + notIcerigi);  // Yeni notu ekliyoruz
            stmt.setString(2, kullaniciAdi);  // Kullanıcı adı ile eşleştiriyoruz
            stmt.executeUpdate();
            System.out.println("Not başarıyla eklendi: " + notIcerigi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Not silmek için kullanılan metod
    public void notSil(String kullaniciAdi, int notIndex) {
        List<String> notlar = kullaniciNotlariGetir(kullaniciAdi);
        if (notIndex >= 0 && notIndex < notlar.size()) {
            notlar.remove(notIndex);

            StringBuilder yeniNotlar = new StringBuilder();
            for (String not : notlar) {
                yeniNotlar.append(not).append("\n");
            }

            String query = "UPDATE kullanici SET notlar = ? WHERE kullaniciAdi = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, yeniNotlar.toString().trim());
                stmt.setString(2, kullaniciAdi);
                stmt.executeUpdate();
                System.out.println("Not başarıyla silindi.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Geçersiz not indexi.");
        }
    }

    // Not güncellemek için kullanılan metod
    public void notGuncelle(String kullaniciAdi, int notIndex, String yeniIcerik) {
        List<String> notlar = kullaniciNotlariGetir(kullaniciAdi);
        if (notIndex >= 0 && notIndex < notlar.size()) {
            notlar.set(notIndex, yeniIcerik);

            StringBuilder yeniNotlar = new StringBuilder();
            for (String not : notlar) {
                yeniNotlar.append(not).append("\n");
            }

            String query = "UPDATE kullanici SET notlar = ? WHERE kullaniciAdi = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, yeniNotlar.toString().trim());
                stmt.setString(2, kullaniciAdi);
                stmt.executeUpdate();
                System.out.println("Not başarıyla güncellendi.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Geçersiz not indexi.");
        }
    }
}
