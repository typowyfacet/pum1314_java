package BazaDanych;


import java.util.HashMap;
import java.util.List;

public abstract class Account {

    protected static HashMap<String, Class<? extends Investment>> availableInvestments;

    public abstract List<String> getAvailableInvestementNames();

    public abstract Investment createInvestement(String investmentName, float contribution);
}
