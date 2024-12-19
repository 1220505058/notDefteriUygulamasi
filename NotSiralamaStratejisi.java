import java.util.*;

// Strategy Pattern
interface NotSiralamaStratejisi {
    List<Not> sirala(List<Not> notlar);
}

class TariheGoreSiralama implements NotSiralamaStratejisi {
    public List<Not> sirala(List<Not> notlar) {
        notlar.sort(Comparator.comparing(Not::getOlusturmaTarihi));
        return notlar;
    }
}

class OnemDerecesineGoreSiralama implements NotSiralamaStratejisi {
    public List<Not> sirala(List<Not> notlar) {
        notlar.sort(Comparator.comparingInt(Not::getOnemDerecesi).reversed());
        return notlar;
    }
}

class NotSiralamaYoneticisi {
    private List<Not> notlar = new ArrayList<>();
    private NotSiralamaStratejisi siralamaStratejisi;

    public void setSiralamaStratejisi(NotSiralamaStratejisi strateji) {
        this.siralamaStratejisi = strateji;
    }

    public void notEkle(Not not) {
        notlar.add(not);
    }

    public List<Not> notlariSirala() {
        if (siralamaStratejisi != null) {
            return siralamaStratejisi.sirala(notlar);
        }
        return notlar;
    }

    public void notlariGoster() {
        for (Not not : notlariSirala()) {
            System.out.println(not);
        }
    }
}