package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public class LoginEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("login") == 0) {

            if (input.findUser(actionInput.getCredentials()) != null) {

                currentPage.setCurrentUser(input.findUser(actionInput.getCredentials()));
                currentPage.setPageName("Homepage autentificat");
                currentPage.clearCurrentMoviesList();
                DisplayCommand.writeInOutput(output, currentPage);

            } else {
                // eroare la login, userul nu exista in baza de date
                currentPage.setPageName("Homepage neautentificat");
                ErrorDisplay.displayError(output);
            }
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
