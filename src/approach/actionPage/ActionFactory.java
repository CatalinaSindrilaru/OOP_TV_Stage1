package approach.actionPage;

/**
 *  Class that implements Factory Design Pattern for actions
 */
public class ActionFactory {

    /**
     * Return new instance depending on type actions
     * @param type type of action (change / on)
     * @return object that implements interface ActionPage
     */
    public ActionPage resolve(final String type) {
        if (type.compareTo("change page") == 0) {
            return new ChangePage();
        } else {
            return new OnPage();
        }
    }
}
