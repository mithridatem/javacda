package controller;
import model.Film;
import java.time.LocalDate;
import java.util.Scanner;

public class FilmController {
    //Attributs
    private static Scanner scanner = new Scanner(System.in);
    //Méthodes static
    /* 1 ajouter un utilisateur*/
    public static void addFilm() {
        //1 afficher un message (pour demander d'ajouter un compte)
        System.out.println("Ajouter un film : ");
        //2 demander de saisir le nom
        System.out.println("Veuillez saisir le titre");
        String titre = scanner.nextLine();
        //4 demander de saisir la description
        System.out.println("Veuillez saisir la description");
        String desc = scanner.nextLine();
        //3 demander de saisir la date
        System.out.println("Veuillez saisir l'année de sortie");
        int year = scanner.nextInt();
        System.out.println("Veuillez saisir le numéro du mois de sortie");
        int month = scanner.nextInt();
        System.out.println("Veuillez saisir le numéro du jour de sortie");
        int day = scanner.nextInt();

        //6 vérifier si les 4 données sont non vides
        if(titre.isEmpty() || Integer.toString(year).isEmpty() || Integer.toString(month).isEmpty() || Integer.toString(day).isEmpty()
        || desc.isEmpty()) {
            System.out.println("Veuillez saisir les informations");
            return;
        }
        //les champs sont tous remplis
        else{
            //7 si ok créer un objet model.Film
            Film newFilm = new Film(titre,LocalDate.of(year, month, day), desc);
            //8 vérifier si le model.Film n'existe pas (findFilmExist)
            if(!newFilm.findFilmExist()){
                //9 Ajouter en BDD
                newFilm.add();
            }
            else {
                System.out.println("Le model.Film existe déja en BDD");
            }
        }
    }

    /* 2 Modifier un utilisateur*/
    public static void updateFilm(){

    }

    /* 3 Affichage d'un utilisateur*/
    public static void showFilm() {

    }

    /* 4 Supprimer un utilisateur*/
    public static void deleteFilm(){

    }

    /* 5 Afficher la liste des utilisateurs*/
    public static void showAllFilm(){
    }

    //Récupérer le scanner
    public static Scanner getScanner() {
        return scanner;
    }
}
