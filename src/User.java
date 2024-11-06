import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;

public class User{
    //Attributs
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    //Connexion à la BDD
    private static Connection connexion = Requete.getConnexion();
    //Constructeurs
    public User(){}
    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    //Getters et Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Méthodes
    //Redefinition de méthode toString
    public String toString(){
        return "Compte : " + this.nom + " " + this.prenom + " " + this.email;
    }

    //Méthode pour ajouter un compte utilisateur en BDD
    public User addUser() {
        //instancier un Objet User null
        User userAdd = null;
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "INSERT INTO users (nom, prenom, email, password) " + "VALUES (?, ?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, this.nom);
            preparedStatement.setString(2, this.prenom);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, this.password);
            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok
            if (addedRows > 0) {
                //Création d'un Objet User
                userAdd = this;
            }
            //fermeture de la connexion BDD
            stmt.close();
            //connexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet User
        return userAdd;
    }

    //Méthode qui vérifie si le compte existe en BDD
    public boolean findUserExist(){
        //requête SQL
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //Requête SQL
            String sql = "SELECT id FROM users WHERE email = ?";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind le paramètre email
            preparedStatement.setString(1, this.email);
            ResultSet rs = preparedStatement.executeQuery();
            //parcourir le resultat de la requête
            while (rs.next()){
               if(rs.getString(1) != null){
                   return true;
               }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //Méthode qui met à jour un compte en BDD
    public User updateUser(){
        try{
            //connexion à la BDD
            Statement stmt = connexion.createStatement();
            String sql = "UPDATE users SET nom = ? , prenom = ?  WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, this.nom);
            preparedStatement.setString(2, this.prenom);
            preparedStatement.setString(3, this.email);
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                System.out.println("Le compte a été mis à jour en BDD");
                return this;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Le compte n'a pas été mis à jour");
        return this;
    }

    //Méthode qui récupére le compte en BDD
    public User findUserEmail(){
        //requête SQL
        try{
            User getUser = null;
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //Requête SQL
            String sql = "SELECT id, nom, prenom, email, password FROM users WHERE email = ?";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind le paramètre email
            preparedStatement.setString(1, this.email);
            ResultSet rs = preparedStatement.executeQuery();
            //parcourir le resultat de la requête
            while (rs.next()){
                //teste si la requête
                if(rs.getString(1) != null){
                    getUser = new User(rs.getString("nom"),rs.getString("prenom"),
                            this.email, rs.getString("password"));
                    getUser.id = Integer.parseInt(rs.getString("id")); //"4" -> 4
                    System.out.println("Le compte a été récupéré");
                    return getUser;
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        //le compte n'a pas été trouvé en BDD
        System.out.println("Le compte n'a pas été trouvé en BDD");
        return this;
    }

    //Méthode qui hash le mot de passe
    public void hashPassword(){
       this.setPassword(BCrypt.hashpw(this.password, BCrypt.gensalt()));
    }

    //méthode qui teste si le hash est valide
    public boolean checkPassword(String plainPassword){
        return BCrypt.checkpw(plainPassword, this.password);
    }

    //méthode qui modifie le mot de passe en BDD
    public User updatePassword(){
        //hasher le mot de passe
        hashPassword();
        try{
            //connexion à la BDD
            Statement stmt = connexion.createStatement();
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, this.password);
            preparedStatement.setString(2, this.email);
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                System.out.println("Le mot de passe a été mis à jour en BDD");
                return this;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Le mot de passe n'a pas été mis à jour");
        return this;
    }

    //méthode qui supprime un utilisateur en BDD
    public User deleteUser(){
        User delete = new User();
        try{
            //connexion à la BDD
            Statement stmt = connexion.createStatement();
            //Requête SQl
            String sql = "DELETE FROM users WHERE  email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, this.email);
            //récupération du nombre de ligne de sortie de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si la requête à fonctionné
            if(addedRows > 0){
                System.out.println("Le compte a été supprimé en BDD");
                return delete;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Le compte n'a pas été supprimé en BDD");
        return this;
    }
}
