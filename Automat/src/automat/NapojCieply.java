package automat;

public class NapojCieply extends Napoj {
    float gCukru;   //czyli ile zawiera cukru

    public NapojCieply(String nazwa, int ilosc, float cena, float gCukru){
        super(nazwa, ilosc, cena);
        this.gCukru = gCukru;   //domyślnie napój ciepły może wymagać nieco cukru
    }

    public NapojCieply() {
    }

    public void dodajPlatnyCukier(float gram) {
        gCukru+=gram;
        super.zwiekszCene((float) 0.20);
    }

    public void dodajCukier(float gram){
        gCukru+=gram;
    }
    public void usunCukier() { gCukru=0; }
    public void setGCukru(float gCukru) { this.gCukru=gCukru; }
    public float getGCukru() { return gCukru; }

    public String toString(){
        return (super.toString() + " z cukrem w gramach: " + gCukru);
    }
}
