import java.util.Date;

class NotFabrikasi {
    public static Not notOlustur(String tip, String icerik) {
        if (tip.equalsIgnoreCase("BASIT")) {
            return new BasitNot(icerik);  // Basit Not oluştur
        } else if (tip.equalsIgnoreCase("YENI")) {
            return new YeniNot(icerik);  // Yeni Not oluştur
        } else if (tip.equalsIgnoreCase("ONEMLI")) {
            return new OnemliNotDecorator(new YeniNot(icerik));  // Decorator kullanılarak Önemli Not oluştur
        } else {
            throw new IllegalArgumentException("Geçersiz not tipi!");
        }
    }
}
