import java.sql.*;

public class Requete {
    //Attribut paramètre BDD
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Java?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    //Connexion à la BDD
    private static Connection connexion;

    static {
        try {
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connexion Ok");
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    public static Connection getConnexion() {
        return connexion;
    }
}
