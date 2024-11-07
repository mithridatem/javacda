import java.util.ArrayList;

public abstract class AbstractModel {
    //Méthodes Abstraites
    public abstract Object add();

    public abstract Object update();

    public abstract Object delete();

    public abstract Object find();

    public abstract Object findBy(String param);

    public static ArrayList<Object> findAll() {
        return null;
    }
}
