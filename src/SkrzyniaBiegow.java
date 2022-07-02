public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;
    Sprzeglo sprzeglo;

    public SkrzyniaBiegow(int aktualnyBieg, int iloscBiegow, int cena, String nazwa, int waga, Sprzeglo sprzeglo) {
        this.aktualnyBieg = aktualnyBieg;
        this.iloscBiegow = iloscBiegow;
        this.setCena(cena);
        this.setNazwa(nazwa);
        this.setWaga(waga);
        this.sprzeglo = sprzeglo;
    }

    public int zwiększBieg(){
        if(aktualnyBieg < iloscBiegow) {
            aktualnyBieg = aktualnyBieg + 1;
            zmienprzelozenie();
        }
        return aktualnyBieg;
    }

    public int zmniejszBieg(){
        if(aktualnyBieg > 0) {
            aktualnyBieg = aktualnyBieg - 1;
            zmienprzelozenie();
        }
        return aktualnyBieg;
    }

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public double zmienprzelozenie(){
        if(aktualnyBieg == 0){
            aktualnePrzelozenie = 0;
        }
        else if(aktualnyBieg == 1){
            aktualnePrzelozenie = 1.2;
        }
        else if(aktualnyBieg == 2){
            aktualnePrzelozenie = 1.5;
        }
        else if(aktualnyBieg == 3){
            aktualnePrzelozenie = 1.8;
        }
        else if(aktualnyBieg == 4){
            aktualnePrzelozenie = 2.1;
        }
        else if(aktualnyBieg == 5){
            aktualnePrzelozenie = 2.4;
        }
        else if(aktualnyBieg == 6){
            aktualnePrzelozenie = 2.8;
        }
        return aktualnePrzelozenie;
    }

    public double getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }
}
