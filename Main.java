import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private VeritabaniBaglantisi veritabaniBaglantisi = VeritabaniBaglantisi.getInstance();  // Veritabanı bağlantısı
    private NotDefteri notDefteri = new NotDefteri();  // Not defteri nesnesi

    @Override
    public void start(Stage primaryStage) {
        // Kullanıcı adı ve şifre giriş ekranı
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10; -fx-alignment: center;");

        TextField kullaniciAdiInput = new TextField();
        kullaniciAdiInput.setPromptText("Kullanıcı Adı");

        PasswordField sifreInput = new PasswordField();
        sifreInput.setPromptText("Şifre");

        Button girisButton = new Button("Giriş Yap");
        Button kayitOlButton = new Button("Kayıt Ol");

        // Giriş yapma işlemi
        girisButton.setOnAction(e -> {
            String kullaniciAdi = kullaniciAdiInput.getText();
            String sifre = sifreInput.getText();

            if (!kullaniciAdi.isEmpty() && !sifre.isEmpty()) {
                boolean girisBasarili = veritabaniBaglantisi.girisYap(kullaniciAdi, sifre);
                if (girisBasarili) {
                    showAlert("Başarılı", "Giriş başarılı!");
                    openNotEkrani(primaryStage, kullaniciAdi);
                } else {
                    showAlert("Hata", "Geçersiz kullanıcı adı veya şifre.");
                }
            } else {
                showAlert("Hata", "Kullanıcı adı ve şifre boş olamaz.");
            }
        });

        // Kayıt olma işlemi
        kayitOlButton.setOnAction(e -> {
            String kullaniciAdi = kullaniciAdiInput.getText();
            String sifre = sifreInput.getText();

            if (!kullaniciAdi.isEmpty() && !sifre.isEmpty()) {
                veritabaniBaglantisi.kullaniciEkle(kullaniciAdi, sifre);
                showAlert("Başarılı", "Kayıt işlemi başarılı!");
            } else {
                showAlert("Hata", "Kullanıcı adı ve şifre boş olamaz.");
            }
        });

        root.getChildren().addAll(
                kullaniciAdiInput, sifreInput, girisButton, kayitOlButton
        );

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Kullanıcı Girişi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openNotEkrani(Stage primaryStage, String kullaniciAdi) {
        ListView<String> listView = new ListView<>();
        ObservableList<String> notlar = FXCollections.observableArrayList(notDefteri.kullaniciNotlariGetir(kullaniciAdi));
        listView.setItems(notlar);

        TextField notInput = new TextField();
        notInput.setPromptText("Yeni Not veya Güncellenecek Not");

        Label notTipiLabel = new Label("Not Türünü Seçin:");
        ComboBox<String> notTipiComboBox = new ComboBox<>();
        notTipiComboBox.getItems().addAll("Basit", "Önemli");

        Button notEkleButton = new Button("Not Ekle");
        Button notSilButton = new Button("Not Sil");
        Button notGuncelleButton = new Button("Not Güncelle");

        // ListView'den seçilen notu TextField'a yükleme
        listView.setOnMouseClicked(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                String secilenNot = listView.getItems().get(selectedIndex);
                notInput.setText(secilenNot);
            }
        });

        // Not ekleme işlemi
        notEkleButton.setOnAction(e -> {
            String yeniNot = notInput.getText();
            String notTipi = notTipiComboBox.getValue();

            if (yeniNot.isEmpty() || notTipi == null) {
                showAlert("Hata", "Not içeriği ve türü seçilmelidir.");
            } else {
                notDefteri.notEkle(kullaniciAdi, yeniNot, notTipi);
                listView.setItems(FXCollections.observableArrayList(notDefteri.kullaniciNotlariGetir(kullaniciAdi)));
                notInput.clear();
                notTipiComboBox.setValue(null);
                showAlert("Başarılı", "Not başarıyla eklendi.");
            }
        });

        // Not silme işlemi
        notSilButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                notDefteri.notSil(kullaniciAdi, selectedIndex);
                listView.setItems(FXCollections.observableArrayList(notDefteri.kullaniciNotlariGetir(kullaniciAdi)));
                notInput.clear();
                showAlert("Başarılı", "Not başarıyla silindi.");
            } else {
                showAlert("Hata", "Silmek için bir not seçiniz.");
            }
        });

        // Not güncelleme işlemi
        notGuncelleButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            String yeniIcerik = notInput.getText();

            if (selectedIndex >= 0 && !yeniIcerik.isEmpty()) {
                notDefteri.notGuncelle(kullaniciAdi, selectedIndex, yeniIcerik);
                listView.setItems(FXCollections.observableArrayList(notDefteri.kullaniciNotlariGetir(kullaniciAdi)));
                notInput.clear();
                showAlert("Başarılı", "Not başarıyla güncellendi.");
            } else {
                showAlert("Hata", "Güncellemek için bir not seçin ve yeni içeriği girin.");
            }
        });

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10; -fx-alignment: center;");
        root.getChildren().addAll(
                new Label("Notlarınız:"), listView,
                notTipiLabel, notTipiComboBox,
                notInput, notEkleButton, notSilButton, notGuncelleButton
        );

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Notlarım");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
