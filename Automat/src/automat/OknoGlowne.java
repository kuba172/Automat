package automat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class OknoGlowne extends JFrame {
    //sekcja menu
    private JMenuBar mbar;
    private JMenu menu;
    private JMenuItem miOtworzOknoSerwisowe;
    private JMenuItem miPrzelaczTryb;
    private JMenu menuSpecjalne;
    private JMenuItem miKopnijAutomat;
    //sekcja przycisków
    private JButton napojZwyklyGuzik1;
    private JButton napojZwyklyGuzik2;
    private JButton napojZwyklyGuzik3;
    private JButton napojZwyklyGuzik4;
    private JButton napojZwyklyGuzik5;
    private JButton napojZwyklyGuzik6;
    private JButton napojZwyklyGuzik7;
    private JButton napojZwyklyGuzik8;
    //
    private JButton napojCieplyGuzik1;
    private JButton napojCieplyGuzik2;
    private JButton napojCieplyGuzik3;
    private JButton napojCieplyGuzik4;
    private JButton napojCieplyGuzik5;
    private JButton napojCieplyGuzik6;
    //
    private JButton bezCukruGuzik;
    private JButton zDodatkowymCukremGuzik;
    //
    private JButton anulujGuzik;
    //
    private JTextPane wyswietlacz;
    //
    private OknoSerwisowe oknoSerwisowe;
    //
    private String PIN;
    private Automat automat;
    //
    Monety przyjeteMonety;

    public OknoGlowne(){
        //podstawowa konfiguracja
        automat = new Automat();
        try{
        automat.wczytajAutomat();
        } catch (FileNotFoundException exc) {automat.ladujDomyslne(); JOptionPane.showMessageDialog(null,"Nie znaleziono pliku stan.txt. Załadowano dane domyślne.", "Informacja.", JOptionPane.INFORMATION_MESSAGE);}
            catch (InputMismatchException | IOException exc) {automat.ladujDomyslne(); JOptionPane.showMessageDialog(null,"Napotkano błąd przy odczycie pliku stan.txt. Załadowano dane domyślne. Przy wyjściu z programu plik stan.txt zostanie nadpisany nowymi danymi.",
                "Błąd!", JOptionPane.WARNING_MESSAGE);}

        PIN = "5425";
        przyjeteMonety = new Monety();

        this.setTitle("Panel Przedni Automatu");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);     //podpięty jest tutaj listener do automatycznego zapisu stanu
        this.setSize(600, 600);
        this.setResizable(true);
        this.setLayout(new GridLayout(0,3));

        //okno serwisowe - na początku jest ukryte
        oknoSerwisowe = new OknoSerwisowe(automat);

        //MenuBar
        menu = new JMenu("Tryb Serwisowy");
        menuSpecjalne = new JMenu("Wyżyj się");
        mbar = new JMenuBar();
        miOtworzOknoSerwisowe = new JMenuItem("Otwórz Okno Serwisowe");
        miPrzelaczTryb = new JMenuItem("Przełącz Tryb Serwisowy");
        menu.add(miOtworzOknoSerwisowe);
        menu.add(miPrzelaczTryb);
        mbar.add(menu);
        miKopnijAutomat = new JMenuItem("Kopnij w Automat");
        menuSpecjalne.add(miKopnijAutomat);
        mbar.add(menuSpecjalne);
        setJMenuBar(mbar);
        //
        napojZwyklyGuzik1 = new JButton("1. "+ automat.infoONapoju(1));
        napojZwyklyGuzik2 = new JButton("2. "+ automat.infoONapoju(2));
        napojZwyklyGuzik3 = new JButton("3. "+ automat.infoONapoju(3));
        napojZwyklyGuzik4 = new JButton("4. "+ automat.infoONapoju(4));
        napojZwyklyGuzik5 = new JButton("5. "+ automat.infoONapoju(5));
        napojZwyklyGuzik6 = new JButton("6. "+ automat.infoONapoju(6));
        napojZwyklyGuzik7 = new JButton("7. "+ automat.infoONapoju(7));
        napojZwyklyGuzik8 = new JButton("8. "+ automat.infoONapoju(8));
        //
        napojCieplyGuzik1 = new JButton("1. "+ automat.infoONapojuCieplym(1));
        napojCieplyGuzik2 = new JButton("2. "+ automat.infoONapojuCieplym(2));
        napojCieplyGuzik3 = new JButton("3. "+ automat.infoONapojuCieplym(3));
        napojCieplyGuzik4 = new JButton("4. "+ automat.infoONapojuCieplym(4));
        napojCieplyGuzik5 = new JButton("5. "+ automat.infoONapojuCieplym(5));
        napojCieplyGuzik6 = new JButton("6. "+ automat.infoONapojuCieplym(6));
        //
        anulujGuzik = new JButton("Anuluj");
        bezCukruGuzik = new JButton("Bez cukru");
        zDodatkowymCukremGuzik = new JButton("Dodatkowy cukier");
        //
        wyswietlacz = new JTextPane();
        wyswietlacz.setEditable(false);
        wyswietlacz.setText("Witamy.");
        //panel przycisków
        this.add(wyswietlacz);
        this.add(napojZwyklyGuzik1);
        this.add(napojZwyklyGuzik2);
        this.add(napojZwyklyGuzik3);
        this.add(napojZwyklyGuzik4);
        this.add(napojZwyklyGuzik5);
        this.add(napojZwyklyGuzik6);
        this.add(napojZwyklyGuzik7);
        this.add(napojZwyklyGuzik8);
        //
        this.add(napojCieplyGuzik1);
        this.add(napojCieplyGuzik2);
        this.add(napojCieplyGuzik3);
        this.add(napojCieplyGuzik4);
        this.add(napojCieplyGuzik5);
        this.add(napojCieplyGuzik6);
        //koniec

        napojZwyklyGuzik1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(1);
            }
        });
        napojZwyklyGuzik2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(2);
            }
        });
        napojZwyklyGuzik3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(3);
            }
        });
        napojZwyklyGuzik4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(4);
            }
        });
        napojZwyklyGuzik5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(5);
            }
        });
        napojZwyklyGuzik6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(6);
            }
        });
        napojZwyklyGuzik7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(7);
            }
        });
        napojZwyklyGuzik8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzZwyklyNapoj(8);
            }
        });

        napojCieplyGuzik1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(1);
            }
        });
        napojCieplyGuzik2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(2);
            }
        });
        napojCieplyGuzik3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(3);
            }
        });
        napojCieplyGuzik4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(4);
            }
        });
        napojCieplyGuzik5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(5);
            }
        });
        napojCieplyGuzik6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obsluzCieplyNapoj(6);
            }
        });

        miKopnijAutomat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int los = (int) Math.ceil(Math.random() * 10);
                if (los <= 1) {
                    automat.wywolajUsterke();
                    JOptionPane.showMessageDialog(null,"Kopiesz w automat i rozlega się dziwny mechaniczny dźwięk. Chyba coś się zepsuło!", "O jejku!",
                        JOptionPane.WARNING_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null,"Z impetem kopiesz w automat.");
            }
        });

        miOtworzOknoSerwisowe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!automat.getTrybSerwisowy()){
                    wlaczTrybSerwisowyZPIN();
                }
                oknoSerwisowe.setVisible(automat.getTrybSerwisowy());
            }
        });

        miPrzelaczTryb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!automat.getTrybSerwisowy()){
                   wlaczTrybSerwisowyZPIN();
                }
                else {
                    int odp = JOptionPane.showConfirmDialog(null, "Czy chcesz wyłączyć tryb serwisowy?", "Potwierdź",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (odp == JOptionPane.YES_OPTION){
                        automat.wylaczTrybSerwisowy();
                        oknoSerwisowe.setVisible(false);
                        /*JOptionPane.showMessageDialog(null,"Tryb serwisowy wyłączony.", "Informacja",
                                JOptionPane.INFORMATION_MESSAGE);*/
                    }
                }
            }
        });

        //zapisywanie przy wyjściu z programu
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame)e.getSource();
                try {
                automat.zapiszStan();
                } catch (IOException exc) {JOptionPane.showMessageDialog(null,"Automatyczny zapis stanu automatu nie powiódł się.", "Błąd!",
                        JOptionPane.WARNING_MESSAGE);}
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        this.setVisible(true);
    }

    private void wlaczTrybSerwisowyZPIN(){
        String odp;
        try{
            odp = (JOptionPane.showInputDialog("Podaj PIN serwisowy."));
            if (odp.equals(PIN)){
                automat.wlaczTrybSerwisowy();
                    /*JOptionPane.showMessageDialog(null,"Tryb serwisowy włączony.", "Informacja",
                            JOptionPane.INFORMATION_MESSAGE);*/
            }
            else {
                JOptionPane.showMessageDialog(null,"Niepoprawny PIN.", "Informacja",
                        JOptionPane.INFORMATION_MESSAGE);}
        } catch(NullPointerException exc) {}    //gdyby niepotwierdzono PINu i nie byłoby przez to odpowiedzi, nie trzeba robić nic
    }

    private void obsluzZwyklyNapoj(int numer){
        if (automat.getUsterka()) {wyswietlacz.setText("ERROR. ERROR."); return;}
        try{
            pobierajMonety();
        }
        catch(IllegalArgumentException | NullPointerException | InputMismatchException exc)  {przyjeteMonety.zeruj();
            JOptionPane.showMessageDialog(null,"Niedozwolone dane.", "O jejku!",
                    JOptionPane.WARNING_MESSAGE);}
        //jeśli starczyło pieniędzy:
        if (przyjeteMonety.obliczWartosc() < automat.getCenaNapoju(numer)) {
            wyswietlacz.setText("ERROR: wrzucono za małą kwotę."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                    JOptionPane.WARNING_MESSAGE); return;
        }
        try{
            automat.wydajNapoj(numer);
            Monety M = automat.zaplacZReszta(przyjeteMonety, automat.getCenaNapoju(numer));
            wyswietlacz.setText("Cena: " + automat.getCenaNapoju(numer)+ " zł" + "\n Zapłacono: " + przyjeteMonety.obliczWartosc() + " zł" + "\n Wydano: " + M.obliczWartosc() + " zł");
            JOptionPane.showMessageDialog(null,"Automat wydał napój i wydał resztę: \n"+ M.toString());
        } catch (OutOfStockException exc) {wyswietlacz.setText("ERROR: brak produktu."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                JOptionPane.WARNING_MESSAGE);}
        losujUsterke();
    }

    private void obsluzCieplyNapoj(int numer){
        if (automat.getUsterka()) {wyswietlacz.setText("ERROR. ERROR."); return;}
        int poziomCukru = 0;
        try{
            poziomCukru = pobierzPoziomCukru();
            pobierajMonety();
        }
        catch(IllegalArgumentException | NullPointerException | InputMismatchException exc)  {przyjeteMonety.zeruj();
            JOptionPane.showMessageDialog(null,"Niedozwolone dane.", "O jejku!",
                    JOptionPane.WARNING_MESSAGE);}
        //jeśli starczyło pieniędzy:
        if (przyjeteMonety.obliczWartosc() < automat.getCenaNapojuCieplego(numer, poziomCukru)) {
            wyswietlacz.setText("ERROR: wrzucono za małą kwotę."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                    JOptionPane.WARNING_MESSAGE); return;
        }
        try{
            automat.wydajNapojCieply(numer, poziomCukru);
            Monety M = automat.zaplacZReszta(przyjeteMonety, automat.getCenaNapojuCieplego(numer, poziomCukru));
            wyswietlacz.setText("Cena: " + automat.getCenaNapojuCieplego(numer, poziomCukru)+ " zł" + "\n Zapłacono: " + przyjeteMonety.obliczWartosc() + " zł" + "\n Wydano: " + M.obliczWartosc() + " zł");
            JOptionPane.showMessageDialog(null,"Automat wydał napój i wydał resztę: \n"+ M.toString());
        }
            catch (CupsOutOfStockException exc) {wyswietlacz.setText("ERROR: brak kubków."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                JOptionPane.WARNING_MESSAGE);}
            catch (SugarOutOfStockException exc) {wyswietlacz.setText("ERROR: brak cukru."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                JOptionPane.WARNING_MESSAGE);}
            catch (OutOfStockException exc) {wyswietlacz.setText("ERROR: brak produktu."); JOptionPane.showMessageDialog(null,"Automat nie wydał napoju, ale zwrócił wszystkie monety.", "O jejku!",
                JOptionPane.WARNING_MESSAGE);}
        losujUsterke();
    }

    private int pobierzPoziomCukru() {
        Object[] options = {"Bez cukru", "Standardowo", "Z dodatkowym cukrem (+20gr)"};
        int n = JOptionPane.showOptionDialog(null,
                "Ile cukru? ",
                "Poziom Cukru",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        if (n == -1) n = 1;     //jeśli zamknięto bez podania opcji, to zakładamy standard
        n--;                    //żeby przetłumaczyć wynik na to, co zostanie zrozumiane przez metodę wydajNapojCieply()
        return n;
    }

    private void pobierajMonety() throws InputMismatchException, NullPointerException, IllegalArgumentException {
        przyjeteMonety.zeruj();
        int liczba;
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 5zł: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("5zł", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 2zł: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("2zł", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 1zł: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("1zł", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 50gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("50gr", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 20gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("20gr", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 10gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("10gr", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 5gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("5gr", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 2gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("2gr", liczba);
        liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj ile wrzucasz monet o nominale 1gr: ", "0"));
        if (liczba < 0) throw new IllegalArgumentException();
        przyjeteMonety.dodajMonete("1gr", liczba);
    }

    private void losujUsterke() {
        int los = (int) Math.ceil(Math.random() * 1000);
        if (los <= 1) {
            automat.wywolajUsterke();
        }
    }
}
