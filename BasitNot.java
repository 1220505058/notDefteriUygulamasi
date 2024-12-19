import java.util.Date;

public class BasitNot extends Not {

    public BasitNot(String icerik, Date olusturmaTarihi, int onemDerecesi) {
        super(icerik, olusturmaTarihi, onemDerecesi);
    }

    public BasitNot(String icerik) {
        super(icerik, new Date(), 1);
    }

    @Override
    public String getDetay() {
        return "[Basit Not] " + getIcerik();
    }

    @Override
    public String notTipi() {
        return "Basit Not";
    }
}
