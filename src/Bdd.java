import java.sql.*;

public class Bdd {

    //Connexion à la BDD
    private static Connection connexion;

    static {
        try {
            connexion = DriverManager.getConnection(Env.DB_URL+Env.DB_NAME+Env.DB_SERVER, Env.DB_USERNAME, Env.DB_PASSWORD);
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
