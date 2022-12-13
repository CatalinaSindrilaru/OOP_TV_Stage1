import approach.actionPage.ActionFactory;
import approach.actionPage.ActionPage;
import approach.actionPage.ChangePage;
import approach.CurrentPage;
import approach.actionPage.OnPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;

import java.util.ArrayList;

public class StartProgram {

    // Singleton
    private static StartProgram instance = null;
    private StartProgram() {

    }

    public static StartProgram getInstance() {
        if (instance == null) {
            instance = new StartProgram();
        }
        return instance;
    }

    public void run(Input input, ArrayNode output) {

        ArrayList<ActionInput> actions = input.getActions();

        CurrentPage currentPage = CurrentPage.getInstance();
        currentPage.setPageName("Homepage neautentificat");

        for (ActionInput action : actions) {
            ActionFactory actionFactory = new ActionFactory();

            ActionPage actionPage;
            if (action.getType().compareTo("change page") == 0) {
                actionPage = actionFactory.resolve("change page");

            } else {
                actionPage = actionFactory.resolve("on page");
            }

            actionPage.resolveCommand(currentPage, action, input, output);
        }
    }

}
