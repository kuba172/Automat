package automat;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Automat {
    private Napoj [] magazynekNapojow;
    private NapojCieply [] magazynekNapojowCieplych;   //chcemy je mieć oddzielnie w celu modelowania automatu - oddzielne moduły, oddzielne miejsca!
    private KasetkaMonet kasetka;    //pieniądze w maszynie do wydawania reszty i w ogóle
    private float gCukru;   //teoretyczny limit powinien wynieść 5kg
    private int liczbaKubkow;  //do napojów ciepłych
    private boolean trybSerwisowy;
    private boolean usterka;    //TODO możliwość kopnięcia automatu, gdyby nie chciał czegoś zwrócić - skutkiem ubocznym może być wystąpienie usterki

    public Automat() {
        magazynekNapojow = new Napoj [8];
        magazynekNapojowCieplych = new NapojCieply [6];
        kasetka = new KasetkaMonet();
    }
    public void wczytajAutomat() throws FileNotFoundException, IOException, InputMismatchException {
            BufferedReader plik = new BufferedReader(new FileReader("stan.txt"));
            Scanner odczyt = new Scanner(plik);
            //pierwsza linijka
            gCukru = Float.parseFloat(odczyt.next());
            liczbaKubkow = odczyt.nextInt();
            trybSerwisowy = odczyt.nextBoolean();
            usterka = odczyt.nextBoolean();
            //druga linijka - kasetka
            Monety M = new Monety();
            for (int i=0; i<9; i++){
                M.ile[i] = odczyt.nextInt();
            }
            kasetka.ustawZawartosc(M);
            //trzecia linia: napoje zimne - w oddzielnej linii nazwa, w kolejnej linii wszystkie dane pod tę nazwę
            odczyt.nextLine();  //przejdź do kolejnej linii
            for (int i=0; i<8; i++){
                magazynekNapojow[i] = new Napoj();
                magazynekNapojow[i].setNazwa(odczyt.nextLine());
                magazynekNapojow[i].setIlosc(Integer.parseInt(odczyt.next()));
                magazynekNapojow[i].setCena(Float.parseFloat(odczyt.next()));
                odczyt.nextLine();  //przejdź do kolejnej linii
            }
            //czwarta linia: napoje ciepłe
            for (int i=0; i<6; i++){
                magazynekNapojowCieplych[i] = new NapojCieply();
                magazynekNapojowCieplych[i].setNazwa(odczyt.nextLine());
                magazynekNapojowCieplych[i].setIlosc(Integer.parseInt(odczyt.next()));
                magazynekNapojowCieplych[i].setCena(Float.parseFloat(odczyt.next()));
                magazynekNapojowCieplych[i].setGCukru(Float.parseFloat(odczyt.next()));
                odczyt.nextLine();  //przejdź do kolejnej linii
            }
            plik.close();
    }

    public void zapiszStan() throws IOException {         //TODO nie zapomnij o podpięciu do WindowListener w interfejsie
        BufferedWriter zapis = new BufferedWriter(new FileWriter("stan.txt"));
        //pierwsza linijka - stan ogólny
        zapis.write(Float.toString(gCukru) + " ");
        zapis.write(Integer.toString(liczbaKubkow) + " ");
        zapis.write(Boolean.toString(trybSerwisowy) + " ");
        zapis.write(Boolean.toString(usterka));
        zapis.newLine();
        //druga linijka - kasetka
        Monety M = kasetka.getZawartosc();
        for (int i=0; i<9; i++){
            zapis.write(Integer.toString(M.ile[i]) + " ");
        }
        zapis.newLine();
        //trzecia linijka - napoje zimne
        for (int i=0; i<8; i++){
            zapis.write(magazynekNapojow[i].getNazwa());
            zapis.newLine();
            zapis.write(Integer.toString(magazynekNapojow[i].getIlosc()) +  " ");
            zapis.write(Float.toString(magazynekNapojow[i].getCena()) + " ");
            zapis.newLine();
        }
        //czwarta linijka - napoje ciepłe
        for (int i=0; i<6; i++){
            zapis.write(magazynekNapojowCieplych[i].getNazwa());
            zapis.newLine();
            zapis.write(Integer.toString(magazynekNapojowCieplych[i].getIlosc()) +  " ");
            zapis.write(Float.toString(magazynekNapojowCieplych[i].getCena()) + " ");
            zapis.write(Float.toString(magazynekNapojowCieplych[i].getGCukru())+ " ");
            zapis.newLine();
        }
        zapis.close();
    }

    public void przeladujWszystkieNapoje(){
        magazynekNapojow[0] = new Napoj("Pepsi 500ml", 10, (float) 1.50);
        magazynekNapojow[1] = new Napoj("Burn 250ml", 10, (float) 1.80);
        magazynekNapojow[2] = new Napoj("Coca-Cola 500ml", 10, (float) 1.65);
        magazynekNapojow[3] = new Napoj("Fanta 500ml", 10, (float) 1.55);
        magazynekNapojow[4] = new Napoj("Mirinda 500ml", 10, (float) 1.55);
        magazynekNapojow[5] = new Napoj("7-Up 500ml", 10, (float) 1.55);
        magazynekNapojow[6] = new Napoj("Tiger 250ml", 10, (float) 1.75);
        magazynekNapojow[7] = new Napoj("Nestea 500ml", 10, (float) 1.50);
        magazynekNapojowCieplych[0] = new NapojCieply("Gorąca czekolada", 20, (float) 1.50, 15);
        magazynekNapojowCieplych[1] = new NapojCieply("Cappuccino", 15, (float) 2.00, 10);
        magazynekNapojowCieplych[2] = new NapojCieply("Espresso", 15, (float) 1.90, 10);
        magazynekNapojowCieplych[3] = new NapojCieply("Kawa czarna", 20, (float) 1.75, 8);
        magazynekNapojowCieplych[4] = new NapojCieply("Kawa z mlekiem", 20, (float) 1.75, 15);
        magazynekNapojowCieplych[5] = new NapojCieply("Herbata cytrynowa", 20, (float) 1.20, 15);
    }

    public void ladujDomyslne(){
        //poniżej dane domyślne, gdy nie ma stan.txt lub parsowanie się nie uda
        kasetka.ladujDomyslne();
        gCukru = 2500;
        liczbaKubkow = 110;    //starczy dla całego asortymentu
        przeladujWszystkieNapoje();
        trybSerwisowy = false;
        usterka = false;
    }

    public Monety zaplacZReszta(Monety M, float cena) throws InsufficientMoneyException {   //jeśli cena przekracza wartość podanych monet
        return kasetka.obsluzMonety(M, cena);
    }

    public float getCenaNapoju(int numer) {
        return magazynekNapojow[numer-1].getCena();
    }

    public float getCenaNapojuCieplego(int numer) {
        return magazynekNapojowCieplych[numer-1].getCena();
    }
    public float getCenaNapojuCieplego(int numer, int poziomCukru) {
       if (poziomCukru == 1 ) return (magazynekNapojowCieplych[numer-1].getCena() + (float) 0.20);
        else return (magazynekNapojowCieplych[numer-1].getCena());
    }

    public Napoj wydajNapoj(int numer) throws OutOfStockException {
        numer--;
        if (liczbaKubkow < 1) throw new CupsOutOfStockException();
        Napoj zwrot = new Napoj(magazynekNapojow[numer].getNazwa(), 1, magazynekNapojow[numer].getCena());  //na zewnątrz klasy będziemy mieć indeksowanie od jedynki
        if(magazynekNapojow[numer].getIlosc() > 0) {
            magazynekNapojow[numer].rozlej();
            liczbaKubkow--;
        }
        else throw new OutOfStockException();
        return zwrot;
    }

    public Napoj wydajNapojCieply(int numer, int poziomCukru) throws OutOfStockException {
        numer--;
        if (liczbaKubkow < 1) throw new CupsOutOfStockException();
        NapojCieply zwrot = new NapojCieply(magazynekNapojowCieplych[numer-1].getNazwa(), 1, magazynekNapojowCieplych[numer-1].getCena(), magazynekNapojowCieplych[numer-1].getGCukru());  //na zewnątrz klasy będziemy mieć indeksowanie od jedynki
        if(magazynekNapojowCieplych[numer].getIlosc() > 0) {
            switch (poziomCukru){
                case -1: zwrot.usunCukier(); break;     //bez cukru
                case 0: break;    //standardowy cukier
                case 1: zwrot.dodajPlatnyCukier(5); break;     //dodatkowy cukier
            }
            if (gCukru < zwrot.getGCukru()) throw new SugarOutOfStockException();
            magazynekNapojowCieplych[numer].rozlej();
            gCukru-=zwrot.getGCukru();  //zużywamy cukier z automatu
            liczbaKubkow--;
        }
        else throw new OutOfStockException();
        return zwrot;
    }

    public int uzupelnijNapoj(int numer, int ilosc) throws IllegalArgumentException, IllegalStateException {    //zwraca również nadmiar
        numer--;
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        if (ilosc < 0) throw new IllegalArgumentException();
        int nadmiar = 0;
        magazynekNapojow[numer].uzupelnij(ilosc);
        if (magazynekNapojow[numer].getIlosc() > 10){
            nadmiar = magazynekNapojow[numer].getIlosc() - 10;
            magazynekNapojow[numer].setIlosc(10);
        }
        return nadmiar;
    }

    public int uzupelnijNapojCieply(int numer, int ilosc) throws IllegalArgumentException, IllegalStateException {    //zwraca również nadmiar
        numer--;
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        if (ilosc < 0) throw new IllegalArgumentException();
        int nadmiar = 0;
        magazynekNapojowCieplych[numer].uzupelnij(ilosc);
        if (magazynekNapojowCieplych[numer].getIlosc() > 20){
            nadmiar = magazynekNapojowCieplych[numer].getIlosc() - 20;
            magazynekNapojowCieplych[numer].setIlosc(20);
        }
        return nadmiar;
    }

    public float uzupelnijCukier(float gCukru) throws IllegalArgumentException, IllegalStateException {     //zwraca również nadmiar dosypanego cukru
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        if (gCukru < 0) throw new IllegalArgumentException();
        float nadmiar = 0;
        this.gCukru+=gCukru;
        if (this.gCukru > 5000){
            nadmiar = this.gCukru - 5000;
            this.gCukru = 5000;
        }
        return nadmiar;
    }

    public int uzupelnijKubki(int liczbaKubkow) throws IllegalArgumentException, IllegalStateException {    //zwraca nadmiar kubków
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        if (liczbaKubkow < 0) throw new IllegalArgumentException();
        int nadmiar = 0;
        this.liczbaKubkow+=liczbaKubkow;
        if (this.liczbaKubkow > 150){        //jeśli przekroczymy limit 100 kubków, to nastąpi przepełnienie
            nadmiar = this.liczbaKubkow - 150;
            this.liczbaKubkow = 150;
        }
        return nadmiar;
    }

    public Monety oproznijKasetkeMonet() throws IllegalStateException {
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        Monety zwrot = kasetka.getZawartosc();
        kasetka.zeruj();
        return zwrot;
    }

    public void przeladujKasetkeMonet() throws IllegalStateException {
        if (!trybSerwisowy) throw new IllegalStateException("Dostępne tylko w trybie serwisowym.");
        kasetka.ladujDomyslne();
    }

    public String zwrocStanRoznych() {
        return ("Kubków: " + liczbaKubkow + ", gramów cukru: " + gCukru);
    }

    public String zwrocStanMagazynkuNapojow() {
        StringBuilder S = new StringBuilder();
        for (int i=0; i<8; i++){
            S.append((i+1)+": "+magazynekNapojow[i].toString()+"\n");
        }
        return S.toString();
    }

    public String zwrocStanMagazynkuNapojowCieplych() {
        StringBuilder S = new StringBuilder();
        for (int i=0; i<6; i++){
            S.append((i+1)+": "+magazynekNapojowCieplych[i].toString()+"\n");
        }
        return S.toString();
    }

    public String infoONapoju(int numer) {
        return (magazynekNapojow[numer-1].getNazwa()+ ": " + magazynekNapojow[numer-1].getCena() + " zł");
    }

    public String infoONapojuCieplym(int numer) {
        return (magazynekNapojowCieplych[numer-1].getNazwa()+ ": " + magazynekNapojowCieplych[numer-1].getCena() + " zł");
    }

    public void wlaczTrybSerwisowy() { trybSerwisowy = true; }
    public void wylaczTrybSerwisowy() { trybSerwisowy = false; }
    public void wywolajUsterke() { usterka = true; }
    public void usunUsterke() { usterka = false; }
    public String zwrocStanKasetki() { return kasetka.toString(); }
    public float wartoscKasetki() { return kasetka.obliczWartosc(); }
    public Napoj[] getMagazynekNapojow(){ return magazynekNapojow; }
    public Napoj[] getMagazynekNapojowCieplych(){ return magazynekNapojowCieplych; }
    public boolean getTrybSerwisowy() { return trybSerwisowy; }
    public boolean getUsterka() { return usterka; }
    public int getLiczbaKubkow() {
        return liczbaKubkow;
    }
    public float getGCukru() {
        return gCukru;
    }
}