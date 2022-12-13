package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

public class WatchEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("see details") == 0) {
            // verific daca filmul a fost cumparat mai intai de user
            MovieInput movie = currentPage.getCurrentMovieList().get(0);

            if (currentPage.getCurrentUser() != null
                    && currentPage.getCurrentUser().purchasedMovie(movie)) {

                currentPage.getCurrentUser().addAtWatchedMovies(movie);
                DisplayCommand.writeInOutput(output, currentPage);

            } else {
                ErrorDisplay.displayError(output);
            }
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
