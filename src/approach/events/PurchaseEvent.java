package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;

public class PurchaseEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("see details") == 0) {

            MovieInput movie = currentPage.getCurrentMovieList().get(0);
            UserInput currentUser = currentPage.getCurrentUser();

            if (currentUser.getCredentials().getAccountType().compareTo("premium") == 0) {

                if (currentUser.getNumFreePremiumMovies() >= 1) {
                    int freeMovies = currentUser.getNumFreePremiumMovies();
                    currentUser.setNumFreePremiumMovies(freeMovies - 1);
                } else {
                    int tokens = currentUser.getTokensCount();
                    currentUser.setTokensCount(tokens - 2);
                }

            } else {
                int tokens = currentUser.getTokensCount();
                currentUser.setTokensCount(tokens - 2);
            }

            currentUser.addAtPurchasedMovies(movie);
            DisplayCommand.writeInOutput(output, currentPage);

        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
