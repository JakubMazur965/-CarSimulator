import java.awt.event.ActionEvent;
import java.lang.Math;

public class Samochód extends Thread{
    private boolean stanWłącznika = false;
    private String nrRejestr;
    private String marka = new String();
    private String model = new String();
    private double prędkośćmax;
    private double obwódKoła = 0.02;
    private double delta_t = 0.02;

    Pozycja aktPozycja;
    Pozycja cel = new Pozycja(0,0);
    Silnik silnik;
    SkrzyniaBiegow skrzynia;


    public Samochód(boolean stanWłącznika, String nrRejestr, String marka, String model, double prędkośćmax, Pozycja aktPozycja, Silnik silnik, SkrzyniaBiegow skrzynia) {
        this.stanWłącznika = stanWłącznika;
        this.nrRejestr = nrRejestr;
        this.marka = marka;
        this.model = model;
        this.prędkośćmax = prędkośćmax;
        this.aktPozycja = aktPozycja;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.start();
    }

    public Pozycja jedźDo(double x, double y){
        cel.setX(x);
        cel.setY(y);
        return cel;
    }

    public Pozycja getAktPozycja() {
        return aktPozycja;
    }

    public void przeniesc(){
        System.out.println();
        double przemieszczenie = 0;

        if(aktPozycja.getX() != cel.getX() && aktPozycja.getY() != cel.getY()) {
            przemieszczenie = getAktPredkosc() * delta_t * ((cel.getX() - aktPozycja.getX())
                    / (Math.sqrt(Math.pow(cel.getX() - aktPozycja.getX(), 2) +
                    Math.pow(cel.getY() - aktPozycja.getY(), 2))));
            aktPozycja.setX(aktPozycja.getX() + przemieszczenie);

            przemieszczenie = getAktPredkosc() * delta_t * ((cel.getY() - aktPozycja.getY())
                    / (Math.sqrt(Math.pow(cel.getX() - aktPozycja.getX(), 2) +
                    Math.pow(cel.getY() - aktPozycja.getY(), 2))));
            aktPozycja.setY(aktPozycja.getY() + przemieszczenie);
        }
        else if (aktPozycja.getX() != cel.getX() && aktPozycja.getY() == cel.getY()) {
            przemieszczenie = getAktPredkosc() * delta_t * ((cel.getX() - aktPozycja.getX())
                    / (Math.sqrt(Math.pow(cel.getX() - aktPozycja.getX(),2) +
                    Math.pow(cel.getY() - aktPozycja.getY(), 2))));
            aktPozycja.setX(aktPozycja.getX() + przemieszczenie);
        }
        else if (aktPozycja.getX() == cel.getX() && aktPozycja.getY() != cel.getY()) {
            przemieszczenie = getAktPredkosc() * delta_t * ((cel.getY() - aktPozycja.getY())
                    / (Math.sqrt(Math.pow(cel.getX() - aktPozycja.getX(),2) +
                    Math.pow(cel.getY() - aktPozycja.getY(), 2))));
            aktPozycja.setY(aktPozycja.getY() + przemieszczenie);
        }

        if(Math.abs(aktPozycja.getX() - cel.getX()) < przemieszczenie){
            aktPozycja.setX(cel.getX());
        }

        if(Math.abs(aktPozycja.getY() - cel.getY()) < przemieszczenie){
            aktPozycja.setY(cel.getY());
        }

        System.out.println("pozycja:" + aktPozycja.getX() + " " + aktPozycja.getY());
        System.out.println("obroty: " + silnik.getObroty());
        System.out.println("bieg: " + skrzynia.getAktualnyBieg());
        System.out.println("przelozenie: " + skrzynia.getAktualnePrzelozenie());
    }

    public void run(){
        while (true) {
            if(aktPozycja.getX() != cel.getX() || aktPozycja.getY() != cel.getY()) {
                przeniesc();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public double getAktPredkosc(){
        if(this.aktPozycja.getX() != cel.getX() || this.aktPozycja.getY() != cel.getY()) {
            double predkosc = silnik.getObroty() * skrzynia.getAktualnePrzelozenie() * obwódKoła;
            if(predkosc > this.prędkośćmax){
                predkosc = prędkośćmax;
            }
            return predkosc;
        } else {
            return 0;
        }
    }

    public String getModel() {
        return model;
    }

    public String getNrRejestr() {
        return nrRejestr;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public int waga(){
        int waga_s = this.getSilnik().getWaga() + this.getSkrzynia().getWaga() + this.getSkrzynia().getSprzeglo().getWaga() + 10000;
        return waga_s;
    }

    public void wlacz(){
        if(this.stanWłącznika == false){
            this.silnik.setObroty(1000);
            this.stanWłącznika = true;
        }
        else {
            System.out.println("Samochód jest już włączony!");
        }
    }

    public void wylacz(){
        if(this.stanWłącznika == true) {
            this.stanWłącznika = false;
            this.silnik.setObroty(0);
        }
        else {
            System.out.println("Samochód jest już wyłączony");
        }
    }

    public void zwiekszBieg(){
        if(this.skrzynia.sprzeglo.isStanSprzegla() == true) {
            if (this.silnik.getObroty() >= 1500) {
                this.skrzynia.zwiększBieg();
                this.silnik.zmniejszObroty(500);
            } else {
                System.out.println("Za niskie oborty, żeby zmienić bieg");
            }
        } else {
            System.out.println("Sprzęgło nie wciśnięte, nie można zmienić biegu");
        }
    }

    public void zmniejszBieg(){
        if(this.skrzynia.sprzeglo.isStanSprzegla() == true) {
            this.skrzynia.zmniejszBieg();
            this.silnik.zwiekszOboroty(500);
        } else {
            System.out.println("Sprzęgło nie wciśnięte, nie można zmienić biegu");
        }
    }

    public void dodajGazu(int x){
        if (getAktPredkosc() < this.prędkośćmax) {
            if (this.skrzynia.sprzeglo.isStanSprzegla() == false) {
                this.silnik.zwiekszOboroty(x);
            } else {
                System.out.println("Sprzegło wciśnięte - nie można dodać gazu");
            }
        } else {
            System.out.println("Osiągnięto maksymalną prędkość dla samochodu");
        }
    }

    @Override
    public String toString(){
        String nazwa = marka;
        return nazwa;
    }


    /*public static void main(String[] args) {
        // startowe pozycje
        Pozycja pozycja_f = new Pozycja(0,0);
        Pozycja pozycja_s = new Pozycja(0,0);

        // silnik do ferrari
        Silnik v8 = new Silnik("v8", 8000, 1000, false, 2000, 100000);
        // silnik do skody
        Silnik v4 = new Silnik("v4", 7000, 1000, false, 1500, 35000);

        // skrzynia biegów dla ferrari
        SkrzyniaBiegow skrzynia_ferrari = new SkrzyniaBiegow(1, 6, 1000, "SK1", 500, new Sprzeglo("SP1", 25000, 150, false));
        // skrzynia biegów do skody
        SkrzyniaBiegow skrzynia_skoda = new SkrzyniaBiegow(1, 6, 1000, "SK2", 500, new Sprzeglo("SP2", 10000, 300, false));

        Samochód ferrari = new Samochód(false, "KRA1234", "ferrari", 350, pozycja_f, v8, skrzynia_ferrari);
        Samochód skoda = new Samochód(false, "WWA3245", "skoda", 200, pozycja_s, v4, skrzynia_skoda);
    }*/
}
