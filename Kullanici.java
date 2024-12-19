public class Kullanici {
    private String kullaniciAdi;
    private String sifre;

    public Kullanici(String kullaniciAdi, String sifre) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    // Şifre doğrulaması yapalım
    public boolean sifreDogrula(String sifre) {
        return this.sifre.equals(sifre);
    }

    // Kullanıcı detaylarını döndüren metod
    public String getDetaylar() {
        return "Kullanıcı Adı: " + kullaniciAdi + "\nŞifre: " + sifre;
    }
}
