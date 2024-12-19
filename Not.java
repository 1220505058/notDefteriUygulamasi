import java.util.Date;

public abstract class Not {

    private String icerik;
    private Date olusturmaTarihi;
    private int onemDerecesi;

    public Not(String icerik, Date olusturmaTarihi, int onemDerecesi) {
        if (icerik == null || icerik.trim().isEmpty()) {
            throw new IllegalArgumentException("Not içeriği boş olamaz.");
        }
        this.icerik = icerik;
        this.olusturmaTarihi = olusturmaTarihi;
        this.onemDerecesi = onemDerecesi;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public int getOnemDerecesi() {
        return onemDerecesi;
    }

    public void setOnemDerecesi(int onemDerecesi) {
        this.onemDerecesi = onemDerecesi;
    }

    public abstract String getDetay();

    public abstract String notTipi();

    @Override
    public String toString() {
        return "[Not Detayı] İçerik: " + icerik + ", Tarih: " + olusturmaTarihi + ", Önem: " + onemDerecesi;
    }
}
