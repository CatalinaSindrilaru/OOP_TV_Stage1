package approach.actionPage;

import Displays.DisplayCommand;
import approach.CurrentPage;
import approach.pages.Errors;
import approach.pages.ErrorsPagesFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import Displays.ErrorDisplay;
import fileio.Input;
import fileio.MovieInput;

public class ChangePage implements ActionPage {

    @Override
    public void resolveCommand(CurrentPage currentPage, ActionInput actionInput,
                               Input input, ArrayNode output) {

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

        if(actionInput.getPage().compareTo("movies") == 0) {
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
