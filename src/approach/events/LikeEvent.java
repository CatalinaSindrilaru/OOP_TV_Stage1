package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

public class LikeEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("see details") == 0) {
            // verific mai intai daca filmul a fost vizualizat
            MovieInput movie = currentPage.getCurrentMovieList().get(0);

            if (currentPage.getCurrentUser() != null
                    && currentPage.getCurrentUser().watchedMovie(movie)) {

                int numLikes = movie.getNumLikes();
                movie.setNumLikes(numLikes + 1);
                currentPage.getCurrentUser().addAtLikedMovies(movie);
                DisplayCommand.writeInOutput(output, currentPage);

            } else {
                ErrorDisplay.displayError(output);
            }
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
