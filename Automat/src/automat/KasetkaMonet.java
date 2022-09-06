package automat;

public class KasetkaMonet {
    private Monety zawartosc;

    public KasetkaMonet(){
        zawartosc = new Monety();
    }

    public KasetkaMonet(Monety M){
        zawartosc = M;
    }

    public void ladujDomyslne(){
        zawartosc.ile[0] = 50;
        zawartosc.ile[1] = 40;
        zawartosc.ile[2] = 30;
        zawartosc.ile[3] = 20;
        zawartosc.ile[4] = 15;
        zawartosc.ile[5] = 12;
        zawartosc.ile[6] = 10;
        zawartosc.ile[7] = 10;
        zawartosc.ile[8] = 0;
    }

    public Monety obsluzMonety(Monety M, float cena) throws InsufficientMoneyException {    //może wydać z niekompletną resztą, gdy zabraknie odpowiednich monet
        return new Monety(zawartosc.dodajMonetyZReszta(M, cena));   //zwraca resztę
    }

    public float obliczWartosc() {
        return zawartosc.obliczWartosc();
    }

    public void ustawZawartosc(Monety M){
        zawartosc = M;
    }

    public Monety getZawartosc(){
        return new Monety (zawartosc);
    }

    public String toString() {
        return zawartosc.toString();
    }

    public void zeruj(){
        zawartosc.zeruj();
    }
}
