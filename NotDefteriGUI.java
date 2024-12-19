import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class NotDefteriGUI extends Application {

    private NotDefteri notDefteri; // Veritabanı veya bellekteki notlar
    private NotSiralamaYoneticisi siralamaYoneticisi;

    public NotDefteriGUI() {
        notDefteri = new NotDefteri();
        siralamaYoneticisi = new NotSiralamaYoneticisi();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Not Defteri");

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // Notlar Listesi
        TextArea notListesi = new TextArea();
        notListesi.setEditable(false);
        notListesi.setPrefHeight(300);

        // Butonlar
        Button tarihSiralaBtn = new Button("Tarihe Göre Sırala");
        Button onemSiralaBtn = new Button("Önem Derecesine Göre Sırala");

        // Tarih Sıralama butonuna aksiyon ekle
        tarihSiralaBtn.setOnAction(e -> {
            siralamaYoneticisi.setSiralamaStratejisi(new TariheGoreSiralama());
            siralamaYoneticisi.notlariGoster();
            notListesi.setText(formatNotListesi(siralamaYoneticisi.notlariSirala()));
        });

        // Önem Sıralama butonuna aksiyon ekle
        onemSiralaBtn.setOnAction(e -> {
            siralamaYoneticisi.setSiralamaStratejisi(new OnemDerecesineGoreSiralama());
            siralamaYoneticisi.notlariGoster();
            notListesi.setText(formatNotListesi(siralamaYoneticisi.notlariSirala()));
        });

        // Arayüze butonları ekle
        HBox buttonBox = new HBox(10, tarihSiralaBtn, onemSiralaBtn);
        root.getChildren().addAll(buttonBox, notListesi);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Listeyi uygun formatta TextArea'ya ekle
    private String formatNotListesi(List<Not> notlar) {
        StringBuilder sb = new StringBuilder();
        for (Not not : notlar) {
            sb.append(not.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
