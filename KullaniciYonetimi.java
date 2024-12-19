import java.util.ArrayList;
import java.util.List;

public class KullaniciYonetimi {
    private List<Kullanici> kullanicilar;

    public KullaniciYonetimi() {
        this.kullanicilar = new ArrayList<>();
    }

    public void kullaniciEkle(Kullanici kullanici) {
        kullanicilar.add(kullanici);
        System.out.println("Kullanıcı başarıyla eklendi: " + kullanici.getKullaniciAdi());
    }

    public void kullaniciSil(String kullaniciAdi) {
        Kullanici kullanici = getKullanici(kullaniciAdi);
        if (kullanici != null) {
            kullanicilar.remove(kullanici);
            System.out.println("Kullanıcı başarıyla silindi: " + kullaniciAdi);
        } else {
            System.out.println("Kullanıcı bulunamadı: " + kullaniciAdi);
        }
    }

    public Kullanici getKullanici(String kullaniciAdi) {
        for (Kullanici kullanici : kullanicilar) {
            if (kullanici.getKullaniciAdi().equalsIgnoreCase(kullaniciAdi)) {
                return kullanici;
            }
        }
        System.out.println("Kullanıcı bulunamadı: " + kullaniciAdi);
        return null;
    }

    public List<Kullanici> getKullanicilar() {
        return new ArrayList<>(kullanicilar);
    }
}
