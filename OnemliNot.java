public class OnemliNot extends NotDecorator {

    public OnemliNot(Not not) {
        super(not);  // Decorator'ı çağırarak 'not' nesnesini başlatıyoruz
    }

    @Override
    public String getDetay() {
        // "Onemli" etiketi eklenerek detay döndürülüyor
        return "[!ÖNEMLİ!] " + super.getDetay();
    }
}
