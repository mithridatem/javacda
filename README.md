# javacda
1 Créer à la racine de src un fichier **model.Env.java** (*il va servir à configurer la BDD*)

2 Editer le fichier comme ci-dessous (en remplaçant avec vos informations:
```java
public final class model.Env {
    //Constantes Informations BDD MySQL
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String DB_NAME = "nom Base";
    public static final String DB_SERVER = "?serverTimezone=UTC";
    public static final String DB_USERNAME = "nom du compte";
    public static final String DB_PASSWORD = "mot de passe";
}

```
