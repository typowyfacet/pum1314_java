package BazaDanych;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class BazaDanych {

    public static String DATABASE_FILE_PATH = "jdbc:sqlite:src/BazaDanych/accounts.sqlite";

    public static String ACCOUNTS_TABLE = "accounts";
    public static String FIRST_NAME_COLUMN = "firstName";
    public static String LAST_NAME_COLUMN = "lastName";
    public static String CAPITAL_COLUMN = "capital";
    public static String[] IMIONA = {"Stefan", "Waldemar", "Bogumił", "Zygfryd", "Punfacy", "Feliks"};
    public static String[] NAZWISKA = {"Siarzewski", "Zapodaj", "Bonk", "Przytył", "Szczyl", "Mandaryn"};

    public static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            // IT'S WRONG, BUT LEAVE IT
        }
    }

    private static void openConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_FILE_PATH);
        } catch (SQLException ex) {
            // IT'S WRONG, BUT LEAVE IT
        }
    }

    private static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            // IT'S WRONG, BUT LEAVE IT
        }
    }

    public static void insertAccount(String imie, String nazwisko, float kapital) throws SQLException {
        Statement statement = null;
        String kapitalS = Float.toString(kapital);
        String insertTableSQL = "INSERT INTO " + ACCOUNTS_TABLE
                + " (" + FIRST_NAME_COLUMN + ", " + LAST_NAME_COLUMN + ", " + CAPITAL_COLUMN + ") VALUES"
                + "('" + imie + "','" + nazwisko + "'," + kapitalS + ");";
        openConnection();
        statement = connection.createStatement();
        statement.executeUpdate(insertTableSQL);
        statement.close();
        closeConnection();
    }
    
    public static void removeAll() throws SQLException {
        Statement statement = null;
        
        String insertTableSQL = "DELETE FROM " + ACCOUNTS_TABLE+ " ;";
        System.out.println(insertTableSQL);
        openConnection();
        statement = connection.createStatement();
        statement.executeUpdate(insertTableSQL);
        statement.close();
        closeConnection();
    }

    public static Account[] getAccounts() throws SQLException {
        ArrayList<Account> accountList = new ArrayList<Account>();

        openConnection();

        PreparedStatement prepExam = connection.prepareStatement("select "
                + FIRST_NAME_COLUMN + ", " + LAST_NAME_COLUMN + ", " + CAPITAL_COLUMN
                + " from " + ACCOUNTS_TABLE + ";");
        ResultSet rs = prepExam.executeQuery();
        while (rs.next()) {
            String firstName = rs.getString(FIRST_NAME_COLUMN);
            String lastName = rs.getString(LAST_NAME_COLUMN);
            float capital = rs.getFloat(CAPITAL_COLUMN);

            System.out.println(firstName + ";" + lastName + ";" + capital);
        }
        rs.close();

        closeConnection();

        return accountList.toArray(new Account[]{});
    }

    public static void main(String[] args) throws SQLException {
// removeAll();        
getAccounts();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            insertAccount(IMIONA[r.nextInt(6)], NAZWISKA[r.nextInt(6)], r.nextInt(10000)+r.nextFloat());
        }
       
        getAccounts();

    }
}
