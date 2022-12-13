package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

public class SearchEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

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
