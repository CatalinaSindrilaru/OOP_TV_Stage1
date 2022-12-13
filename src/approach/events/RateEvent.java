package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;

public class RateEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("see details") == 0) {
            // verific mai intai daca filmul a fost vizuaalizat
            MovieInput movie = currentPage.getCurrentMovieList().get(0);

            if (currentPage.getCurrentUser() != null
                    && currentPage.getCurrentUser().watchedMovie(movie)) {

                int rate = actionInput.getRate();
                if (rate > 5) {
                    ErrorDisplay.displayError(output);
                    return;
                }

                int oldSumRatings = movie.getSumRatings();
                int oldNumRatings = movie.getNumRatings();

                movie.setSumRatings(oldSumRatings + rate);
                movie.setNumRatings(oldNumRatings + 1);

                float newRating = (float)movie.getSumRatings() / movie.getNumRatings();
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
