import java.util.Scanner;

public class UserController {
    private static Scanner scanner = new Scanner(System.in);
    //Méthodes static
    /* 1 ajouter un utilisateur*/
    public static void addUser(){
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
            //7 si ok créer un objet User
            User newUser = new User(nom, prenom, email, password);
            //8 vérifier si le compte n'existe pas (findUserExist)
            if(!newUser.findUserExist()){
                //hasher le mot de passe
                newUser.hashPassword();
                //9 Ajouter en BDD
                newUser.addUser();
                //10 afficher un message pour indiquer que le compte à été ajouté
                System.out.println("Le compte a été ajouté en BDD");
            }
            else {
                System.out.println("Le compte existe déja en BDD");
            }
        }
    }

    /* 2 Modifier un utilisateur*/
    public static void updateUser(){}

    /* 3 Affichage d'un utilisateur*/
    public static void showUser(){}

    /* 4 Supprimer un utilisateur*/
    public static void deleteUser(){}

    /* 5 Afficher la liste des utilisateurs*/
    public static void showAllUser(){}

    //récupérer le scanner
    public static Scanner getScanner() {
        return scanner;
    }
}
