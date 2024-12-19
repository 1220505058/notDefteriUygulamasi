
class NotDurumYonetimi {
    private NotDurumu durum;

    public void setDurum(NotDurumu durum) {
        this.durum = durum;
    }

    public void durumBilgisi() {
        if (durum != null) {
            durum.durumBilgisi();
        } else {
            System.out.println("Durum belirtilmedi.");
        }
    }
}
