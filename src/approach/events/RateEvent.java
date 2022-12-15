package approach.events;

import displays.DisplayCommand;
import displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

/**
 * Class for the rate action
 */
public class RateEvent implements Event {

    public static final int MAX_RATE = 5;
    /**
     * Rate the movie and add it to the rated movies list of the current user
     * @param currentPage the current page I'm on
     * @param actionInput action information
     * @param input information about users, movies, actions
     * @param output final ArrayNode in which must be added
     */
    @Override
    public void makeEvent(final CurrentPage currentPage, final ActionInput actionInput,
                          final Input input, final ArrayNode output) {

        if (currentPage.getPageName().compareTo("see details") == 0) {
            MovieInput movie = currentPage.getCurrentMovieList().get(0);

            /* verify if the movie was visualised first */
            if (currentPage.getCurrentUser() != null
                    && currentPage.getCurrentUser().watchedMovie(movie)) {

                int rate = actionInput.getRate();
                if (rate > MAX_RATE) {
                    ErrorDisplay.displayError(output);
                    return;
                }

                /* Update the sum and num for rating */
                int oldSumRatings = movie.getSumRatings();
                int oldNumRatings = movie.getNumRatings();

                movie.setSumRatings(oldSumRatings + rate);
                movie.setNumRatings(oldNumRatings + 1);

                /* Calculate again */
                float newRating = (float) movie.getSumRatings() / movie.getNumRatings();
                movie.setRating(newRating);

                currentPage.getCurrentUser().addAtRatedMovies(movie);
                DisplayCommand.writeInOutput(output, currentPage);

            } else {
                ErrorDisplay.displayError(output);
            }
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
