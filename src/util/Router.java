package util;

import controller.FilmController;
import controller.UserController;

public class Router {
    //Méthode qui lance le router utilisateur
    public static void userRouter(){
        System.out.println("Veuillez choisir les actions à réaliser");
        System.out.println("Saisir add -> pour ajouter un compte");
        System.out.println("Saisir update -> pour modifier un compte");
        System.out.println("Saisir show -> pour afficher un compte");
        System.out.println("Saisir delete -> pour supprimer un compte");
        System.out.println("Saisir showAll -> pour afficher la liste des comptes");
        System.out.println("Saisir stop -> pour arrêter");
        String route = UserController.getScanner().nextLine();
        switch (route){
            case "add":
                UserController.addUser();
                break;
            case "update":
                UserController.updateUser();
                break;
            case "show":
                UserController.showUser();
                break;
            case "delete" :
                UserController.deleteUser();
                break;
            case "showAll":
                UserController.showAllUser();
                break;
            case "stop" :
                System.out.println("Arret du programme");
                break;
            default: break;
        }
    }

    //Méthode qui lance le router film
    public static void filmRouter(){
        System.out.println("Veuillez choisir les actions à réaliser");
        System.out.println("Saisir add -> pour ajouter un film");
        System.out.println("Saisir update -> pour modifier un film");
        System.out.println("Saisir show -> pour afficher un film");
        System.out.println("Saisir delete -> pour supprimer un film");
        System.out.println("Saisir showAll -> pour afficher la liste des film");
        System.out.println("Saisir stop -> pour arrêter");
        String route = FilmController.getScanner().nextLine();
        switch (route){
            case "add":
                FilmController.addFilm();
                break;
            case "update":
                FilmController.updateFilm();
                break;
            case "show":
                FilmController.showFilm();
                break;
            case "delete" :
                FilmController.deleteFilm();
                break;
            case "showAll":
                FilmController.showAllFilm();
                break;
            case "stop" :
                System.out.println("Arret du programme");
                break;
            default: break;
        }
    }

    public static void mainRouter(){
        System.out.println("Saisir user -> pour gérer les utilisateurs");
        System.out.println("Saisir film -> pour gérer les films");
        System.out.println("Saisir stop -> pour arrêter");
        String route = FilmController.getScanner().nextLine();
        switch (route){
            case "user":
                userRouter();
                break;
            case "film":
                filmRouter();
                break;
            case "stop":
                System.out.println("Arret du programme");
                break;
            default:break;
        }
    }
}
