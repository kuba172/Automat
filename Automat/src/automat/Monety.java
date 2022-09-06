package automat;

public class Monety {
    private float nominal[];    //w złotych
    public int ile [];          //nie ma potrzeby hermetyzacji tutaj - może i indeksy nie są intuicyjne, ale mamy do prostych zadań wygodne metody

    public Monety(){
        nominal = new float [9];
        nominal[0] = (float) 0.01;
        nominal[1] = (float) 0.02;
        nominal[2] = (float) 0.05;
        nominal[3] = (float) 0.10;
        nominal[4] = (float) 0.20;
        nominal[5] = (float) 0.50;
        nominal[6] = (float) 1.00;
        nominal[7] = (float) 2.00;
        nominal[8] = (float) 5.00;
        ile = new int [9];
    }

    public Monety(Monety M) {
        this();
        this.dodajMonety(M);
    }

    public void dodajMonete(String nominal, int ile) throws IllegalArgumentException {       //akceptuje również wartości ujemne - dodaj/usuń
        switch(nominal){
            case "1gr": this.ile[0]+=ile; break;
            case "2gr": this.ile[1]+=ile; break;
            case "5gr": this.ile[2]+=ile; break;
            case "10gr": this.ile[3]+=ile; break;
            case "20gr": this.ile[4]+=ile; break;
            case "50gr": this.ile[5]+=ile; break;
            case "1zl": this.ile[6]+=ile; break;
            case "1zł": this.ile[6]+=ile; break;
            case "2zl": this.ile[7]+=ile; break;
            case "2zł": this.ile[7]+=ile; break;
            case "5zl": this.ile[8]+=ile; break;
            case "5zł": this.ile[8]+=ile; break;
            default: throw new IllegalArgumentException();
        }
    }

    public void ustawMonete(String nominal, int ile) throws IllegalArgumentException {
        switch(nominal){
            case "1gr": this.ile[0]=ile; break;
            case "2gr": this.ile[1]=ile; break;
            case "5gr": this.ile[2]=ile; break;
            case "10gr": this.ile[3]=ile; break;
            case "20gr": this.ile[4]=ile; break;
            case "50gr": this.ile[5]=ile; break;
            case "1zl": this.ile[6]=ile; break;
            case "1zł": this.ile[6]=ile; break;
            case "2zl": this.ile[7]=ile; break;
            case "2zł": this.ile[7]=ile; break;
            case "5zl": this.ile[8]=ile; break;
            case "5zł": this.ile[8]=ile; break;
            default: throw new IllegalArgumentException();
        }
    }

    public int zwrocIle(String nominal) throws IllegalArgumentException {
        switch(nominal){
            case "1gr": return this.ile[0];
            case "2gr": return this.ile[1];
            case "5gr": return this.ile[2];
            case "10gr": return this.ile[3];
            case "20gr": return this.ile[4];
            case "50gr": return this.ile[5];
            case "1zl": return this.ile[6];
            case "1zł": return this.ile[6];
            case "2zl": return this.ile[7];
            case "2zł": return this.ile[7];
            case "5zl": return this.ile[8];
            case "5zł": return this.ile[8];
            default: throw new IllegalArgumentException();
        }
    }

    public float obliczWartosc(){
        float suma=0;
        for (int i=0; i<9; i++){
            suma += (nominal[i]*ile[i]);
        }
        return suma;
    }

    public void dodajMonety(Monety M){
        for (int i=0; i<9; i++){
            this.ile[i]+=M.ile[i];
        }
    }

    public Monety dodajMonetyZReszta(Monety M, float cena) throws InsufficientMoneyException {      //prosty algorytm zachłanny
        Monety zwrot = new Monety();
        float pozostaloDoZwrotu = M.obliczWartosc() - cena;
        if (pozostaloDoZwrotu < 0) throw new InsufficientMoneyException();
        this.dodajMonety(M);  //najpierw dodajemy monety, żeby był lepszy wybór przy wydawaniu
        int razy;
        for (int i=8; i>=0; i--){
            if (pozostaloDoZwrotu==0) break;     //jeśli już wydaliśmy resztę, kończymy pętlę
            razy = (int)(pozostaloDoZwrotu/nominal[i]);    //ile razy możemy wypłacić tym samym nominałem
            if (razy > this.ile[i]) razy = this.ile[i];    //zabezpiecza przed płaceniem nieistniejącymi monetami
            this.ile[i]-=razy;   //oddajemy monety
            zwrot.ile[i]+=razy;  //i przekazujemy je do zwrotu
            pozostaloDoZwrotu-=razy*nominal[i];   //pomniejszamy 'dług'
        }
        return zwrot;
    }

    public void zeruj() {
        for (int i=0; i<9; i++) {
            ile[i] = 0;
        }
    }

    public String toString() {
        StringBuilder S = new StringBuilder();
        for (int i=0; i<9; i++){
            S.append(nominal[i]+" zł: " + ile[i] + "\n");
        }

        return S.toString();
    }
}
