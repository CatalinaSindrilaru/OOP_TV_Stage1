package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Credentials;
import fileio.Input;
import fileio.UserInput;

public class RegisterEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("register") == 0) {

            Credentials credentials = actionInput.getCredentials();

            UserInput user = new UserInput();
            user.setCredentials(credentials);
            input.addUser(user);

            currentPage.setCurrentUser(user);
            currentPage.setPageName("Homepage autentificat");
            currentPage.clearCurrentMoviesList();
            DisplayCommand.writeInOutput(output, currentPage);

        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
