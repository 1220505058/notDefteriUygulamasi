import java.util.Date;

public class YeniNot extends Not {

    public YeniNot(String icerik) {
        super(icerik, new Date(), 0);
    }

    @Override
    public String getDetay() {
        return "[Yeni Not] " + getIcerik();  // "[Yeni Not]" etiketi ekleniyor.
    }

    @Override
    public String notTipi() {
        return "Yeni Not";
    }
}
