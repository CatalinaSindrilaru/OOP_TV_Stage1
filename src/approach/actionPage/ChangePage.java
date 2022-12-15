package approach.actionPage;

import displays.DisplayCommand;
import approach.CurrentPage;
import approach.pages.Errors;
import approach.pages.ErrorsPagesFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import displays.ErrorDisplay;
import fileio.Input;
import fileio.MovieInput;

/**
 * Class for "change page" action
 */
public class ChangePage implements ActionPage {

    /**
     * Resolve each type of "change page" action
     * @param currentPage the current page I'm on
     * @param actionInput action information
     * @param input information about users, movies, actions
     * @param output final ArrayNode in which must be added
     */
    @Override
    public void resolveCommand(final CurrentPage currentPage, final ActionInput actionInput,
                               final Input input, final ArrayNode output) {

        ErrorsPagesFactory errorsPagesFactory = new ErrorsPagesFactory();
        boolean errorOccurred = false;

        Errors page;
        if (currentPage.getPageName().compareTo("Homepage neautentificat") == 0) {
            page = errorsPagesFactory.createPage("Homepage neautentificat");
            errorOccurred = page.checkErrors(currentPage, actionInput, output);
        }

        if (currentPage.getPageName().compareTo("Homepage autentificat") == 0) {
            page = errorsPagesFactory.createPage("Homepage autentificat");
            errorOccurred = page.checkErrors(currentPage, actionInput, output);
        }

        if (currentPage.getPageName().compareTo("movies") == 0) {
            page = errorsPagesFactory.createPage("movies");
            errorOccurred = page.checkErrors(currentPage, actionInput, output);
        }

        if (errorOccurred) {
            return;
        }

        if (actionInput.getPage().compareTo("register") == 0
                || actionInput.getPage().compareTo("login") == 0) {

            if (currentPage.getPageName().compareTo("Homepage neautentificat") == 0) {
                currentPage.setPageName(actionInput.getPage());
                return;
            }
        }

        if (actionInput.getPage().compareTo("logout") == 0) {
            currentPage.setCurrentUser(null);
            currentPage.setPageName("Homepage neautentificat");
            return;
        }

        if (actionInput.getPage().compareTo("movies") == 0) {
            currentPage.setPageName("movies");
            currentPage.clearCurrentMoviesList();
            currentPage.populateCurrentMoviesList(input);

            DisplayCommand.writeInOutput(output, currentPage);
            return;
        }

        if (actionInput.getPage().compareTo("upgrades") == 0) {
            if (currentPage.getPageName().compareTo("Homepage autentificat") == 0
                    || currentPage.getPageName().compareTo("see details") == 0) {

                currentPage.setPageName(actionInput.getPage());
            } else {
                ErrorDisplay.displayError(output);
            }
            return;
        }


        if (actionInput.getPage().compareTo("see details") == 0) {
            if (currentPage.getPageName().compareTo("movies") == 0) {

                if (currentPage.getCurrentMovieList().size() == 0) {
                    ErrorDisplay.displayError(output);
                } else {

                    MovieInput movie = currentPage.findMovie(actionInput.getMovie());
                    if (movie == null) {
                        ErrorDisplay.displayError(output);
                    } else {
                        currentPage.clearCurrentMoviesList();
                        currentPage.getCurrentMovieList().add(movie);
                        DisplayCommand.writeInOutput(output, currentPage);
                        currentPage.setPageName("see details");
                    }
                }
            } else {
                ErrorDisplay.displayError(output);
            }
        }
    }
}
