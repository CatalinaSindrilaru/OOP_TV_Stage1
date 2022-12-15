package approach.events;

import displays.DisplayCommand;
import displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

/**
 * Class for the search action
 */
public class SearchEvent implements Event {

    /**
     * Search the movie that starts with the given word
     * @param currentPage the current page I'm on
     * @param actionInput action information
     * @param input information about users, movies, actions
     * @param output final ArrayNode in which must be added
     */
    @Override
    public void makeEvent(final CurrentPage currentPage, final ActionInput actionInput,
                          final Input input, final ArrayNode output) {

        if (currentPage.getPageName().compareTo("movies") == 0) {

            MovieInput movie = currentPage.findMovie(actionInput.getStartsWith());
            currentPage.clearCurrentMoviesList();

            if (movie != null) {
                currentPage.getCurrentMovieList().add(movie);
            }
            DisplayCommand.writeInOutput(output, currentPage);

        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
