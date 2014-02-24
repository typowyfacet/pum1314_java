package Wojna;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Przemek Kasprzak
 *
 *
 *
 *
 * /
 **
 * @param args the command line arguments
 */
public class Wojna {

    private static int iGraczy;             // ilosc graczy
    public static List<Karta> stolGry;      // stół gry
    public static String[] imiona= {"Adam","Stefan","Anna","Waldemar","Józek","Katarzyna","Ola","Magda","Krystyna","Adam","Stefan","Anna","Waldemar","Józek","Katarzyna","Ola","Magda","Krystyna"};
    public enum Karta implements Comparable<Karta> {

        DZIEWIATKA(1), DZIESIATKA(2), WALET(3), DAMA(4), KROL(5), AS(6);
        private final int waga;
        

        Karta(int waga) {
            this.waga = waga;
        }

        public int getWaga() {
            return this.waga;
        }
    }

    public static void KartaNaStol(Gracz gracz) {
        stolGry.add(gracz.karty.get(0));
        gracz.usunKarte(0);
    }

    public static void KartyZeStoluDoGracza(Gracz gracz) {
        for (Karta k : stolGry) {
            gracz.dodajKarte(k);
            //stolGry.remove(0);
        }
    }
public static void WyswietlZawStolu (){
   System.out.println("Stol:");
    for (Karta k : stolGry){
        System.out.println("  #"+k.toString());    
    }
}
    public static void WyswietlKartyGraczy(List<Gracz> kGraczy) {
        int o = 0;
        for (Gracz g : kGraczy) {
            o++;
            System.out.println(" - ");
            System.out.print(" -- gracz " + o +" "+g.imieGracza+ ":");
            for (Karta k : g.karty) {
                System.out.print(k.toString()+" ; ");
            }
        }
    }
public static boolean ZnanyZwyciezca(List<Gracz> kGraczy){
    boolean x=false;
    for (Gracz k:kGraczy) {
        if (k.karty.size()==24) x=true;
    }
    return x;
}
    public static Karta Pojedynek(List<Gracz> kGraczy) { 
        Karta zwyciezkaKarta=kGraczy.get(0).karty.get(0);
        //Gracz tempGracz = kGraczy.get(0);
        for (int i = 0; i < kGraczy.size(); i++) {
            if (zwyciezkaKarta.compareTo(kGraczy.get(i).karty.get(0))<0) {
                zwyciezkaKarta=kGraczy.get(i).karty.get(0);
            }

        }
        List<Gracz> kolekcjaPowtorzen = new ArrayList<>();

        for (int j = 0; j < kGraczy.size(); j++) {
            if (kGraczy.get(j).karty.get(0) == zwyciezkaKarta) {
                kolekcjaPowtorzen.add(kGraczy.get(j));
            }
            KartaNaStol(kGraczy.get(j));
            
        }
        WyswietlZawStolu();
        
        if (kolekcjaPowtorzen.size()>1){ //sprawdzam czy w razie wojny gracz ma inne jednostki
            for (int i=0;i<kolekcjaPowtorzen.size();i++) {
                if (kolekcjaPowtorzen.get(i).karty.size()==0) {
                    kolekcjaPowtorzen.remove(i);
                    System.out.println("^^ "+"   nie ma juz czym walczyc ^^");
                }
                
            }
        }
        
        if (kolekcjaPowtorzen.size()==1) {
            System.out.println("");
            System.out.println("zwyciezca "+zwyciezkaKarta);
            KartyZeStoluDoGracza(kolekcjaPowtorzen.get(0));
        }
        else {
        java.awt.Toolkit.getDefaultToolkit().beep();
            System.out.println("WOJNA !");
            Pojedynek(kolekcjaPowtorzen);
        };
        //System.out.println("liczba powtorzen" + kolekcjaPowtorzen.size());
        
        WyswietlKartyGraczy(kGraczy);
        return zwyciezkaKarta;
    }

    public static void main(String[] args) {

       // System.out.println(Karta.DZIESIATKA.compareTo(Karta.AS) > 0 ? "Tak WIEKSZE" : "MNIEJSZE I CHUJ");
        Scanner inputGraczy = new Scanner(System.in);
        System.out.println("Podaj liczbe graczy: ");
        iGraczy = inputGraczy.nextInt();
        System.out.println("Ilosc graczy " + iGraczy);
        List<Gracz> tGraczy = new ArrayList<>();
        for (int i = 1; i < iGraczy + 1; i++) {
            tGraczy.add(new Gracz(imiona[i]));
        }
        // utworzenie i wypełnienie talii kart 9-AS=24 karty
        List<Karta> talia = new ArrayList<>();
        for (Karta k : Karta.values()) {

            for (int i = 0; i < 4; i++) {

                talia.add(k);

            }

        }
        System.out.println(talia.size());
        //tasowanie talii
        Collections.shuffle(talia);

        //rozdanie kart dla graczy
        int c = 1;
        for (Karta k : talia) {
            System.out.println(c + " " + k.toString());
            tGraczy.get(c - 1).dodajKarte(k);
            c = (c == iGraczy) ? c = 1 : c + 1;

        }
        int pojedynki=0;
        while (!ZnanyZwyciezca(tGraczy) && pojedynki < 10000) {
        pojedynki++;
        System.out.println(" ");
            System.out.println("                           @@@@@   Pojedynek "+pojedynki +" @@@@@");
            
             System.out.println("          ");
              System.out.println("                        ");
            stolGry = new ArrayList<>();
        List<Gracz> zywyGracz = new ArrayList<>();
//int lzGraczy = 0;
        for (int i = 0; i < iGraczy; i++) {
            if (tGraczy.get(i).karty.size() > 0) {
                zywyGracz.add(tGraczy.get(i));

            }
        }
         Pojedynek(zywyGracz);
        
        }
        System.out.println(" ");
        if (pojedynki>=9999) System.out.println("Petla! Lepiej rozdajcie jeszcze raz..."); 
        System.out.println("KONIEC !");
//Gracz[] tG=new Gracz[zywyGracz.size()];
        // for (int j=0;j<zywyGracz.size(),j++) tG[j]=zywyGracz.get(j);
        //Pojedynek (tG.);

        //Pojedynek(zywyGracz.get(0), zywyGracz.get(1), zywyGracz.get(2), zywyGracz.get(3)); //test pojedynek
      
//        System.out.println("karty na stol !" + "ilosc graczy: " + zywyGracz.size());
//        for (Gracz g : zywyGracz) {
//            stolGry.add(g.karty.get(0));

    }



}
