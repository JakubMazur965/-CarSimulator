public class Silnik extends Komponent{
    private int maxobroty;
    private int obroty;
    private boolean stan;

    public Silnik(String nazwa, int maxobroty, int obroty, boolean stan, int waga, int cena) {
        this.setNazwa(nazwa);
        this.maxobroty = maxobroty;
        this.obroty = obroty;
        this.stan = stan;
        this.setWaga(waga);
        this.setCena(cena);
    }

    public void uruchom(){}

    public void zatrzymaj(){}

    public void zwiekszOboroty(int x){
        if(obroty + x <= maxobroty){
            obroty = obroty + x;
        }
        else
        {
            System.out.println("Nie mozna zmienic obrotów - osiagnieto maximum");
        }
    }

    public void zmniejszObroty(int x){
        if(obroty - x >= 1000){
            obroty = obroty - x;
        }
        else
        {
            System.out.println("Nie mozna zmienic obrotów - obroty poniżej 1000");
        }
    }

    public int getObroty() {
        return obroty;
    }

    public void setObroty(int obroty) {
        this.obroty = obroty;
    }
}
