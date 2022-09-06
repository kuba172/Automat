package automat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class OknoSerwisowe extends JFrame {

    private Automat a;
    private JMenuBar mbar;
    private JMenu menuKasetka;
    private JMenu menuRozne;
    private JMenu menuNapojeZwykle;
    private JMenu menuNapojeCieple;
    private JMenu menuDiagnostyka;

    private JMenuItem pokazStanKasetki;
    private JMenuItem oproznijKasetke;
    private JMenuItem przeladujKasetke;
    private JMenuItem pokazNapojeZwykle;
    private JMenuItem pokazNapojeCieple;
    private JMenuItem uzupelnijNapojZwykly;
    private JMenuItem uzupelnijNapojCieply;
    private JMenuItem przeladujWszystkieNapoje;
    private JMenuItem pokazStanRozne;
    private JMenuItem uzupelnijKubki;
    private JMenuItem uzupelnijCukier;
    private JMenuItem naprawUsterke;

    public OknoSerwisowe(Automat automat){
        this.a = automat;

        this.setTitle("Panel Serwisowy Automatu");
        setLayout(new FlowLayout());
        setSize(400, 90);
        setResizable(true);

        mbar = new JMenuBar();
        menuKasetka = new JMenu("Kasetka");
        menuNapojeCieple = new JMenu("Napoje Ciepłe");
        menuNapojeZwykle = new JMenu("Napoje Zwykłe");
        menuRozne = new JMenu("Różne");
        menuDiagnostyka = new JMenu("Diagnostyka");

        pokazStanKasetki = new JMenuItem("Pokaż Stan Kasetki");
        oproznijKasetke = new JMenuItem("Opróżnij Kasetkę");
        przeladujKasetke = new JMenuItem("Przeładuj Kasetkę");
        pokazNapojeZwykle = new JMenuItem("Pokaż Napoje Zwykłe");
        uzupelnijNapojZwykly = new JMenuItem("Uzupełnij Napój Zwykły");
        pokazNapojeCieple = new JMenuItem("Pokaż Napoje Ciepłe");
        uzupelnijNapojCieply = new JMenuItem("Uzupełnij Napój Ciepły");
        przeladujWszystkieNapoje = new JMenuItem("Przeładuj Wszystkie Napoje");
        pokazStanRozne = new JMenuItem("Pokaż Stan Rzeczy Różnych");
        uzupelnijKubki = new JMenuItem("Uzupełnij Kubki");
        uzupelnijCukier = new JMenuItem("Uzupełnij Cukier");
        naprawUsterke = new JMenuItem("Napraw Usterkę");

        menuKasetka.add(pokazStanKasetki);
        menuKasetka.add(oproznijKasetke);
        menuKasetka.add(przeladujKasetke);
        menuNapojeZwykle.add(pokazNapojeZwykle);
        menuNapojeZwykle.add(uzupelnijNapojZwykly);
        menuNapojeCieple.add(pokazNapojeCieple);
        menuNapojeCieple.add(uzupelnijNapojCieply);
        menuRozne.add(pokazStanRozne);
        menuRozne.add(uzupelnijKubki);
        menuRozne.add(uzupelnijCukier);
        menuRozne.add(przeladujWszystkieNapoje);
        menuDiagnostyka.add(naprawUsterke);

        mbar.add(menuKasetka);
        mbar.add(menuNapojeZwykle);
        mbar.add(menuNapojeCieple);
        mbar.add(menuRozne);
        mbar.add(menuDiagnostyka);
        setJMenuBar(mbar);

        pokazStanKasetki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, a.zwrocStanKasetki());
            }
        });
        oproznijKasetke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Monety M = a.oproznijKasetkeMonet();
                JOptionPane.showMessageDialog(null, "Kasetka opróżniona. Otrzymano: \n" + M.toString());
            }
        });
        przeladujKasetke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (a.wartoscKasetki() > 0) JOptionPane.showMessageDialog(null, "Kasetka nie jest pusta. Należy ją najpierw opróżnić!");
                else {
                    a.przeladujKasetkeMonet();
                    JOptionPane.showMessageDialog(null, "Kasetka przeładowana.");
                }
            }
        });
        pokazNapojeZwykle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Napoje zwykłe: \n" + a.zwrocStanMagazynkuNapojow());
            }
        });
        pokazNapojeCieple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Napoje ciepłe: \n" + a.zwrocStanMagazynkuNapojowCieplych());
            }
        });
        uzupelnijNapojZwykly.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numer = Integer.parseInt(JOptionPane.showInputDialog("Podaj numer napoju."));
                    if (numer <= 0 || numer > 8) {JOptionPane.showMessageDialog(null, "Niepoprawny numer napoju."); return;}
                    int ilosc = Integer.parseInt(JOptionPane.showInputDialog("Podaj o ile sztuk chcesz uzupełnić."));

                    int nadmiar = a.uzupelnijNapoj(numer, ilosc);
                    if (nadmiar <= 0) JOptionPane.showMessageDialog(null, "Napój uzupełniony!");
                    else JOptionPane.showMessageDialog(null, "Napój uzupełniony, zwrócony nadmiar: " + nadmiar);
                } catch (InputMismatchException | IllegalArgumentException exc) {JOptionPane.showMessageDialog(null, "Niepoprawna liczba!");}
                catch (NullPointerException exc) {}     //jeśli nic nie zostało podane, to się tym nie martwimy
            }
        });
        uzupelnijNapojCieply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numer = Integer.parseInt(JOptionPane.showInputDialog("Podaj numer napoju."));
                    if (numer <= 0 || numer > 6) {JOptionPane.showMessageDialog(null, "Niepoprawny numer napoju."); return;}
                    int ilosc = Integer.parseInt(JOptionPane.showInputDialog("Podaj o ile sztuk chcesz uzupełnić."));

                    int nadmiar = a.uzupelnijNapojCieply(numer, ilosc);
                    if (nadmiar <= 0) JOptionPane.showMessageDialog(null, "Napój uzupełniony!");
                    else JOptionPane.showMessageDialog(null, "Napój uzupełniony, zwrócony nadmiar: " + nadmiar);
                } catch (InputMismatchException | IllegalArgumentException exc) {JOptionPane.showMessageDialog(null, "Niepoprawna liczba!");}
                catch (NullPointerException exc) {}     //jeśli nic nie zostało podane, to się tym nie martwimy
            }
        });
        pokazStanRozne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, a.zwrocStanRoznych());
            }
        });
        uzupelnijKubki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int liczba = Integer.parseInt(JOptionPane.showInputDialog("Podaj liczbę kubków."));
                    int nadmiar = a.uzupelnijKubki(liczba);

                    if (nadmiar <= 0) JOptionPane.showMessageDialog(null, "Kubki uzupełnione!");
                    else JOptionPane.showMessageDialog(null, "Kubki uzupełnione, zwrócony nadmiar: " + nadmiar);
                } catch (InputMismatchException | IllegalArgumentException exc) {JOptionPane.showMessageDialog(null, "Niepoprawna liczba!");}
                catch (NullPointerException exc) {}     //jeśli nic nie zostało podane, to się tym nie martwimy
            }
        });
        uzupelnijCukier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float liczba = Float.parseFloat(JOptionPane.showInputDialog("Podaj ile gramów cukru chcesz dodać."));
                    float nadmiar = a.uzupelnijCukier(liczba);

                    if (nadmiar <= 0) JOptionPane.showMessageDialog(null, "Cukier uzupełniony.");
                    else JOptionPane.showMessageDialog(null, "Cukier uzupełniony, zwrócony nadmiar: " + nadmiar);
                } catch (InputMismatchException | IllegalArgumentException exc) {JOptionPane.showMessageDialog(null, "Niepoprawna liczba!");}
                catch (NullPointerException exc) {}     //jeśli nic nie zostało podane, to się tym nie martwimy
            }
        });
        przeladujWszystkieNapoje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a.przeladujWszystkieNapoje();
                JOptionPane.showMessageDialog(null, "Wszystkie napoje przeładowane.");
            }
        });
        naprawUsterke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!a.getUsterka()) JOptionPane.showMessageDialog(null, "Sprawdzasz automat i okazuje się, że nie było żadnej usterki.");
                else {
                    a.usunUsterke();
                    JOptionPane.showMessageDialog(null, "Sprawdzasz automat i usuwasz przyczynę usterki!");
                }
            }
        });
    }
}
