package controller;

import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    //Attributs
    private static Scanner scanner = new Scanner(System.in);
    //Méthodes static
    /* 1 ajouter un utilisateur*/
    public static void addUser() {
        //1 afficher un message (pour demander d'ajouter un compte)
        System.out.println("Ajouter un compte : ");
        //2 demander de saisir le nom
        System.out.println("Veuillez saisir le nom");
        String nom = scanner.nextLine();
        //3 demander de saisir le prenom
        System.out.println("Veuillez saisir le prénom");
        String prenom = scanner.nextLine();
        //4 demander de saisir le email
        System.out.println("Veuillez saisir l'email'");
        String email = scanner.nextLine();
        //5 demander de saisir le password
        System.out.println("Veuillez saisir le mot de passe");
        String password = scanner.nextLine();
        //6 vérifier si les 4 données sont non vides
        if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty()){
            System.out.println("Veuillez saisir les informations");
            return;
        }
        //les champs sont tous remplis
        else{
            //7 si ok créer un objet model.User
            User newUser = new User(nom, prenom, email, password);
            //8 vérifier si le compte n'existe pas (findUserExist)
            if(!newUser.findUserExist()){
                //hasher le mot de passe
                newUser.hashPassword();
                //9 Ajouter en BDD
                newUser.add();
                //10 afficher un message pour indiquer que le compte à été ajouté
                System.out.println("Le compte a été ajouté en BDD");
            }
            else {
                System.out.println("Le compte existe déja en BDD");
            }
        }
    }

    /* 2 Modifier un utilisateur*/
    public static void updateUser(){
        System.out.println("Veuillez saisir l'email du compte à éditer");
        String email = scanner.nextLine();
        //Tester si email est non vide
        if(!email.isEmpty()){
            //Vérifier si le compte existe
            User user = new User().setEmail(email);
            //Tester si le compte existe
            if(user.findUserExist()){
                //Demander les informations à modifier
                System.out.println("Veuillez saisir le nouveau nom");
                String nom  = scanner.nextLine();
                System.out.println("Veuillez saisir le nouveau prénom");
                String prenom = scanner.nextLine();
                //Tester si les 2 valeurs sont non vide
                if(!nom.isEmpty() && !prenom.isEmpty()){
                    //Setter le nom et le prénom
                    user.setNom(nom);
                    user.setPrenom(prenom);
                    //Mettre à jour le compte
                    user.update();
                }
                else{
                    //Les valeurs non ou prénom sont vides
                    System.out.println("le nom ou le prénom est vide");
                    return;
                }
            }
            //Sinon le compte n'existe pas
            else{
                System.out.println("Le compte n'existe pas");
                return;
            }
        }
        //Sinon on Affiche un message l'email est vide
        else {
           System.out.println("L'email est vide");
        }
    }

    /* 3 Affichage d'un utilisateur*/
    public static void showUser() {
        System.out.println("Veuillez saisir l'email");
        String email = scanner.nextLine();
        //Tester si email est non vide
        if(!email.isEmpty()) {
            User user = new User().setEmail(email);
            //Tester si le compte existe
            if(user.findUserExist()) {
                //Récupérer et afficher le compte
                System.out.println(user.findUserEmail());
            }
            //Sinon le compte n'existe pas
            else {
                System.out.println("Le compte n'existe pas en BDD");
            }
        }
        //Sinon l'email n'est pas renseigné
        else {
            System.out.println("L'email est vide");
        }
    }

    /* 4 Supprimer un utilisateur*/
    public static void deleteUser(){
        System.out.println("Veuillez saisir l'email");
        String email = scanner.nextLine();
        //Tester si le mail est non vide
        if(!email.isEmpty()) {
            User user = new User().setEmail(email);
            //Tester si le compte existe
            if(user.findUserExist()) {
                user.delete();
            }
            //Sinon afficher un message le compte n'existe pas ou plus
            else {
                System.out.println("Le compte n'existe pas ou plus en BDD");
                return;
            }
        }
        //Sinon le mail est vide
        else {
            System.out.println("L'email est vide");
            return;
        }
    }

    /* 5 Afficher la liste des utilisateurs*/
    public static void showAllUser(){
        System.out.println("Liste des comptes utilisateurs");
        ArrayList<Object> users = User.findAll();
        //Tester si la liste est vide
        if(users.size() == 0) {
            System.out.println("Il n'y à pas de compte en BDD");
            return;
        }
        //La liste est non vide
        else {
            //Parcourir l'ArrayList
            for(Object user : User.findAll()) {
                //Afficher le compte avec non, prenom et email
                System.out.println(user);
            }
        }
    }

    //Récupérer le scanner
    public static Scanner getScanner() {
        return scanner;
    }
}
