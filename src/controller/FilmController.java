package controller;
import model.Film;
import java.time.LocalDate;
import java.util.ArrayList;
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

    /* 2 Modifier un utilisateur*/
    public static void updateFilm(){
        System.out.println("Veuillez saisir le titre du éditer");
        String titre = scanner.nextLine();
        //Tester si email est non vide
        if(!titre.isEmpty()){
            //Vérifier si le compte existe
            Film film = new Film();
            film.setTitre(titre);
            //Tester si le compte existe
            if(film.findFilmExist()){
                //Demander les informations à modifier
                System.out.println("Veuillez saisir la nouvelle description");
                String desc  = scanner.nextLine();
                System.out.println("Veuillez saisir la nouvelle année de sortie");
                int year = scanner.nextInt();
                System.out.println("Veuillez saisir le nouveau mois de sortie");
                int month = scanner.nextInt();
                System.out.println("Veuillez saisir le numéro du jour de sortie");
                int day = scanner.nextInt();
                //Tester si les 2 valeurs sont non vide
                if(!Integer.toString(year).isEmpty() && !Integer.toString(month).isEmpty() && !Integer.toString(day).isEmpty()
                        && !desc.isEmpty()){
                    //Setter le nom et le prénom
                    film.setDescription(desc);
                    film.setDateSortie(LocalDate.of(year, month, day));
                    //Mettre à jour le film
                    film.update();
                }
                else{
                    //Les valeurs non ou prénom sont vides
                    System.out.println("la description ou la date est vide");
                }
            }
            //Sinon le film n'existe pas
            else{
                System.out.println("Le film n'existe pas");
            }
        }
        //Sinon on Affiche un message l'email est vide
        else {
            System.out.println("Le titre est vide");
        }
    }

    /* 3 Affichage d'un utilisateur*/
    public static void showFilm() {
        System.out.println("Veuillez saisir le titre du film");
        String titre = scanner.nextLine();
        //Tester si titre est non vide
        if(!titre.isEmpty()) {
            Film film = new Film();
            film.setTitre(titre);
            //Tester si le film existe
            if(film.findFilmExist()) {
                //Récupérer et afficher le film
                System.out.println(film.find());
                return;
            }
            System.out.println("Le Film n'existe pas en BDD");
        }
        //Sinon le titre n'est pas renseigné
        else {
            System.out.println("Le titre est vide");
        }
    }

    /* 4 Supprimer un utilisateur*/
    public static void deleteFilm(){
        System.out.println("Veuillez saisir le titre du film");
        String titre = scanner.nextLine();
        //Tester si le titre est non vide
        if(!titre.isEmpty()) {
            Film film = new Film();
            film.setTitre(titre);
            //Tester si le film existe
            if(film.findFilmExist()) {
                film.delete();
            }
            //Sinon afficher un message le film n'existe pas ou plus
            else {
                System.out.println("Le film n'existe pas ou plus en BDD");
            }
        }
        //Sinon le titre est vide
        else {
            System.out.println("Le Titre est vide");
        }
    }

    /* 5 Afficher la liste des utilisateurs*/
    public static void showAllFilm(){
        System.out.println("Liste des Films");
        ArrayList<Object> films = Film.findAll();
        //Tester si la liste est vide
        if(films.size() == 0) {
            System.out.println("Il n'y à pas de Film en BDD");
        }
        //La liste est non vide
        else {
            //Parcourir l'ArrayList
            for(Object film : Film.findAll()) {
                //Afficher le film avec le titre, la description et la date
                System.out.println(film);
            }
        }
    }

    //Récupérer le scanner
    public static Scanner getScanner() {
        return scanner;
    }
}
