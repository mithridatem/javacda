package model;

import java.sql.*;
import java.time.LocalDate;

public class Film extends AbstractModel {
    //Attributs
    private int id;
    private String titre;
    private LocalDate dateSortie;
    private String description;
    //Connexion à la BDD
    private static final Connection connexion = Bdd.getConnexion();
    //Constructeurs
    public Film(){}
    public Film(String titre, LocalDate dateSortie, String description) {
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.description = description;
    }

    //Getters et Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateSortie() {
        return this.dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Méthodes
    public String toString(){
        return "model.Film : " + this.titre + ", date : " + this.dateSortie + ", description : " + this.description;
    }

    @Override
    public Film add() {
        //instancier un Objet model.User null
        Film film = null;
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "INSERT INTO film (titre, date_sortie, description) " + "VALUES (?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, this.titre);
            preparedStatement.setString(2, this.dateSortie.toString());
            preparedStatement.setString(3, this.description);
            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok
            if (addedRows > 0) {
                //Création d'un Objet model.User
                film = this;
                System.out.println("Le film a été ajouté en BDD");
            }
            //fermeture de la connexion BDD
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet model.User
        return film;
    }

    @Override
    public Object update() {
        return null;
    }

    @Override
    public Object delete() {
        return null;
    }

    @Override
    public Film find() {
        return null;
    }

    @Override
    public Object findBy(String param) {
        return null;
    }

    //Méthode qui vérifie si le film existe en BDD
    public boolean findFilmExist(){
        //requête SQL
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //Requête SQL
            String sql = "SELECT id FROM film WHERE titre = ? AND date_sortie = ?";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind le paramètre email
            preparedStatement.setString(1, this.titre);
            preparedStatement.setString(2, this.dateSortie.toString());
            ResultSet rs = preparedStatement.executeQuery();
            //parcourir le resultat de la requête
            while (rs.next()){
                if(rs.getString(1) != null){
                    return true;
                }
            }
            //fermeture de la connexion BDD
            stmt.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
