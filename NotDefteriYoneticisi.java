
public class NotDefteriYoneticisi {
    private static NotDefteriYoneticisi instance; // Tek örnek

    private NotDefteriYoneticisi() {
        // Private kurucu: dışarıdan çağrılamaz
    }

    public static NotDefteriYoneticisi getInstance() {
        if (instance == null) {
            instance = new NotDefteriYoneticisi();
        }
        return instance;
    }

    public void notEkle(String not) {
        System.out.println("Not eklendi: " + not);
    }

    public void notlariGoster() {
        System.out.println("Notlar görüntülendi.");
    }
}
