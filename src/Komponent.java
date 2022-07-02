public class Komponent {
    private String nazwa = new String();
    private int waga;
    private int cena;

    public String getNazwa() {
        return nazwa;
    }

    public int getWaga() {
        return waga;
    }

    public int getCena() {
        return cena;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
