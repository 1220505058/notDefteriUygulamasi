import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KullaniciYonetimiGUI extends JFrame {
    private KullaniciYonetimi kullaniciYonetimi;

    public KullaniciYonetimiGUI() {
        kullaniciYonetimi = new KullaniciYonetimi();

        setTitle("Kullanıcı Yönetimi");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Üst Panel: Kullanıcı Ekleme
        JPanel ustPanel = new JPanel();
        ustPanel.setLayout(new FlowLayout());

        JTextField kullaniciAdiGirisi = new JTextField(15);
        JButton kullaniciEkleBtn = new JButton("Kullanıcı Ekle");

        ustPanel.add(new JLabel("Kullanıcı Adı: "));
        ustPanel.add(kullaniciAdiGirisi);
        ustPanel.add(kullaniciEkleBtn);

        // Orta Panel: Kullanıcıları Listeleme
        JTextArea kullaniciListesi = new JTextArea();
        kullaniciListesi.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(kullaniciListesi);

        // Alt Panel: Kullanıcı Silme ve Detay Gösterme
        JPanel altPanel = new JPanel();
        altPanel.setLayout(new FlowLayout());

        JTextField silinecekKullaniciGirisi = new JTextField(15);
        JButton kullaniciSilBtn = new JButton("Kullanıcı Sil");
        JButton detayGosterBtn = new JButton("Detay Göster");

        altPanel.add(new JLabel("Kullanıcı Adı: "));
        altPanel.add(silinecekKullaniciGirisi);
        altPanel.add(kullaniciSilBtn);
        altPanel.add(detayGosterBtn);

        // Eylemler
        kullaniciEkleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = kullaniciAdiGirisi.getText();
                if (!kullaniciAdi.isEmpty()) {
                    Kullanici yeniKullanici = new Kullanici(kullaniciAdi, "defaultPassword");
                    kullaniciYonetimi.kullaniciEkle(yeniKullanici);
                    kullaniciAdiGirisi.setText("");
                    kullaniciListesiniGuncelle(kullaniciListesi);
                    System.out.println("Kullanıcı eklendi: " + kullaniciAdi);
                }
            }
        });

        kullaniciSilBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String silinecekKullanici = silinecekKullaniciGirisi.getText();
                if (!silinecekKullanici.isEmpty()) {
                    kullaniciYonetimi.kullaniciSil(silinecekKullanici);
                    silinecekKullaniciGirisi.setText("");
                    kullaniciListesiniGuncelle(kullaniciListesi);
                    System.out.println("Kullanıcı silindi: " + silinecekKullanici);
                }
            }
        });

        detayGosterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = silinecekKullaniciGirisi.getText();
                if (!kullaniciAdi.isEmpty()) {
                    Kullanici kullanici = kullaniciYonetimi.getKullanici(kullaniciAdi);
                    if (kullanici != null) {
                        JOptionPane.showMessageDialog(null, "Kullanıcı Detayları: \n" + kullanici.getDetaylar());
                        System.out.println("Detay gösterildi: " + kullaniciAdi);
                    } else {
                        JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.");
                        System.out.println("Kullanıcı bulunamadı: " + kullaniciAdi);
                    }
                }
            }
        });

        // Pencereye Panelleri Ekle
        add(ustPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(altPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void kullaniciListesiniGuncelle(JTextArea kullaniciListesi) {
        StringBuilder sb = new StringBuilder();
        for (Kullanici kullanici : kullaniciYonetimi.getKullanicilar()) {
            sb.append(kullanici.getKullaniciAdi()).append("\n");
        }
        kullaniciListesi.setText(sb.toString());
    }

    public static void main(String[] args) {
        new KullaniciYonetimiGUI();
    }
}
