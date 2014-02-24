package BazaDanych;

import java.util.Date;

public abstract class Investment {

    protected static float oprocentowanie;

    public abstract void getOprocenotwanie();

    public abstract Date getIleCzasuDoKoncaOkresuRozliczeniowego();
}
