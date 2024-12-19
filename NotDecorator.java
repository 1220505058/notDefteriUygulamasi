public abstract class NotDecorator extends Not {
    protected Not not;

    public NotDecorator(Not not) {
        super(not.getIcerik(), not.getOlusturmaTarihi(), not.getOnemDerecesi());
        this.not = not;
    }

    @Override
    public String getDetay() {
        return not.getDetay();  // Mevcut detay korunur, ek bir etiket eklenmez.
    }

    @Override
    public String notTipi() {
        return not.notTipi();
    }
}


class OnemliNotDecorator extends NotDecorator {

    public OnemliNotDecorator(Not not) {
        super(not);
    }

    @Override
    public String getDetay() {
        return "[!ÖNEMLİ!] " + super.getDetay();
    }

    @Override
    public String notTipi() {
        return "Önemli " + super.notTipi();
    }
}

class RenkliNotDecorator extends NotDecorator {
    private String renk;

    public RenkliNotDecorator(Not not, String renk) {
        super(not);
        this.renk = renk;
    }

    @Override
    public String getDetay() {
        return "[Renk: " + renk + "] " + super.getDetay();
    }

    @Override
    public String notTipi() {
        return "Renkli " + super.notTipi();
    }
}
