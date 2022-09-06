package automat;
public class Napoj {
    private String nazwa;
    private int ilosc;  //chodzi o porcje - jedna puszka tudzież kubek
    private float cena;

    public Napoj(String nazwa, int ilosc, float cena){
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.cena = cena;
    }


    public Napoj() {
    }

    public void uzupelnij(int liczba){
        ilosc +=liczba;
    }
    public void rozlej(){
        ilosc--;
    }
    public int getIlosc(){
        return ilosc;
    }
    public String getNazwa(){
        return nazwa;
    }
    public float getCena() { return cena; }
    public void setCena(float cena) { this.cena = cena; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
    public void setIlosc(int ilosc) { this.ilosc = ilosc; }
    public void zwiekszCene(float koszt) {this.cena+=koszt;}

    public String toString(){
        return (nazwa + " w ilosci " + ilosc + " w cenie za sztukę " + cena);
    }
}
