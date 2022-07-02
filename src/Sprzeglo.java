public class Sprzeglo extends Komponent{
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, int cena, int waga, boolean stanSprzegla) {
        this.setNazwa(nazwa);
        this.setCena(cena);
        this.setWaga(waga);
        this.stanSprzegla = stanSprzegla;
    }

    public void wcisnij(){
        stanSprzegla = true;
    }

    public void zwolnij(){
        stanSprzegla = false;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }
}
