public class Main {
    public static void main(String[] args){
        Bdd.getConnexion();
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
}
