package Lotek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author zp107327 (Przemysław Kasprzak)
 */
public class Lotek {

    public static void main(String[] args) {
        ArrayList<Integer> liczby = new ArrayList<>();
        Random komoraLosujaca = new Random();
        liczby.add(komoraLosujaca.nextInt(50));
        while (liczby.size() < 6) {
            int x = komoraLosujaca.nextInt(50);
            if ((x > 0) && (!liczby.contains(x))) {
                liczby.add(x);
            }

        };
        Collections.sort(liczby);        
        System.out.println("Wylosowane liczby: " + liczby);

    }
}
