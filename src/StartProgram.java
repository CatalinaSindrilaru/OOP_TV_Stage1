import Displays.DisplayCommand;
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
        // scoatem userii, filmele, actiunile

        ArrayList<ActionInput> actions = input.getActions();

        CurrentPage currentPage = new CurrentPage(); // posibil sa l fac singleton
        currentPage.setPageName("Homepage neautentificat");

        String prevAction = null;
        for (ActionInput action : actions) {

            if (action.getType().compareTo("change page") == 0) {
                // apelare functie pt change page
                ChangePage changePage = ChangePage.getInstance();
                changePage.resolveCommand(currentPage, input, action, output);

            } else { // daca e "on page"
                // apelare functie on page
                OnPage onPage = OnPage.getInstance();
                int error = onPage.resolveCommand(input, currentPage, action, output, prevAction);
                // scriere in display
                if (error == 0) {
                    ErrorDisplay.displayError(output);
                }
//                } else {
////                    DisplayCommand.writeInOutput(output, error, currentPage); // ar trebui sa mut asta la fiecare functie care are nevoie de output in parte
//                }

                prevAction = action.getFeature();
//                currentPage.clearCurrentMoviesList(); // resetez mereu lista de current movies // posibil nu aici
            }
        }
    }

}
