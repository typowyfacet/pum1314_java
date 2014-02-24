package Wojna;

import java.util.ArrayList;
import java.util.List;
import Wojna.Wojna.Karta;

/**
 *
 * @author 786397
 */
public class Gracz {

    public List<Karta> karty;
    public String imieGracza = "";
    public void dodajKarte(Karta karta) {
        
        karty.add(karta);
    }
    public void usunKarte(int indeksKarty){
        System.out.println(" * zabieram graczowi "+karty.get(indeksKarty));
        karty.remove(indeksKarty);
        
    }
    public Gracz(String imie) {
        this.imieGracza = imie;
        karty=new ArrayList<>();
    }
}
