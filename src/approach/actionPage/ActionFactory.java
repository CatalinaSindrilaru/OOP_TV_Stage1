package approach.actionPage;

public class ActionFactory {
    public ActionPage resolve(String type) {

        if (type.compareTo("change page") == 0) {
            return new ChangePage();
        } else {
            return new OnPage();
        }
    }
}
