import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VeritabaniBaglantisi {

    private static VeritabaniBaglantisi instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://127.0.0.1:3306/kullaniciDB";  // Veritabanı URL'si
    private final String USER = "root";  // Veritabanı kullanıcı adı
    private final String PASSWORD = "";  // Veritabanı şifresi

    // Singleton deseni
    private VeritabaniBaglantisi() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Veritabanı bağlantısı başarılı!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Hata", "Veritabanı bağlantısı sağlanamadı!");
        }
    }

    public static VeritabaniBaglantisi getInstance() {
        if (instance == null) {
            instance = new VeritabaniBaglantisi();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Kullanıcı girişi (kullanıcı adı ve şifreyi kontrol etme)
    public boolean girisYap(String kullaniciAdi, String sifre) {
        String query = "SELECT * FROM kullanici WHERE kullaniciAdi = ? AND sifre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kullaniciAdi);
            stmt.setString(2, sifre);  // Şifreyi düz metin olarak kontrol ediyoruz
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;  // Giriş başarılı
            } else {
                return false;  // Giriş başarısız
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kullanıcı notlarını almak
    public ObservableList<String> kullaniciNotlariGetir(String kullaniciAdi) {
        ObservableList<String> notlarListesi = FXCollections.observableArrayList();
        String query = "SELECT notlar FROM kullanici WHERE kullaniciAdi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kullaniciAdi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String notlar = rs.getString("notlar");
                if (notlar != null && !notlar.isEmpty()) {
                    // Notları satırlara ayırıyoruz
                    String[] notlarArray = notlar.split("\n");
                    for (String not : notlarArray) {
                        notlarListesi.add(not);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notlarListesi;
    }

    // Kullanıcı notlarını ekleme
    public void notEkle(String kullaniciAdi, String yeniNot, String notTipi) {
        String notIcerigi = "[" + notTipi + "] " + yeniNot;

        // Mevcut notlara yeni notu ekle
        String query = "UPDATE kullanici SET notlar = CONCAT(IFNULL(notlar, ''), ?) WHERE kullaniciAdi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "\n" + notIcerigi);  // Yeni notu ekliyoruz
            stmt.setString(2, kullaniciAdi);  // Kullanıcı adı ile eşleştiriyoruz
            stmt.executeUpdate();
            System.out.println("Not başarıyla eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı ekleme (Kullanıcı adı ve şifre ile)
    public boolean kullaniciEkle(String kullaniciAdi, String sifre) {
        String query = "INSERT INTO kullanici (kullaniciAdi, sifre, notlar) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kullaniciAdi);
            stmt.setString(2, sifre);  // Şifreyi ekliyoruz
            stmt.setString(3, "");  // Notlar başlangıçta boş olacak
            stmt.executeUpdate();
            System.out.println("Kullanıcı başarıyla eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Hata", "Kullanıcı eklenirken bir hata oluştu.");
        }
        return false;
    }

    // Alert gösterme fonksiyonu
    private void showAlert(String title, String message) {
        System.out.println(title + ": " + message);
    }
}
