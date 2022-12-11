package fileio;

import Displays.DisplayCommand;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ChangePage {
    private static ChangePage instance = null;

    private ChangePage() {

    }

    public static ChangePage getInstance() {
        if (instance == null) {
            instance = new ChangePage();
        }
        return instance;
    }

    public void resolveCommand(CurrentPage currentPage, Input input,  ActionInput actionInput, ArrayNode output) {

        if (currentPage.getPageName().compareTo("Homepage neautentificat") == 0) {
            if (actionInput.getPage().compareTo("register") == 0 || actionInput.getPage().compareTo("login") == 0) {
                currentPage.setPageName(actionInput.getPage());
            } else {
                // eroare daca se incearca alta comanda
                currentPage.clearCurrentMoviesList();
                DisplayCommand.writeInOutput(output, 0, currentPage);
            }
            return;
        }

        if (currentPage.getPageName().compareTo("Homepage autentificat") == 0) {

            if (actionInput.getPage().compareTo("logout") == 0) {
                currentPage.setCurrentUser(null);
                currentPage.setPageName("Homepage neautentificat");

            }else if(actionInput.getPage().compareTo("movies") == 0) {

                currentPage.setPageName("movies");
                // populez lista currentMovies din current page cu filmele ce nu sunt banate pentru tara utilizatorului curent
                currentPage.clearCurrentMoviesList();
                currentPage.populateCurrentMoviesList(input);

                DisplayCommand.writeInOutput(output, 1, currentPage);

                currentPage.clearCurrentMoviesList();


            } else if (actionInput.getPage().compareTo("upgrades") == 0) {
                currentPage.setPageName(actionInput.getPage());
            } else {

                // doar afisare eroare
                ErrorDisplay.displayError(output);
//                currentPage.setCurrentUser(null);
//                currentPage.setPageName("Homepage neautentificat");
            }
        }

        if (currentPage.getPageName().compareTo("movies") == 0) {
            if (actionInput.getPage().compareTo("see details") == 0) {

//                currentPage.setPageName("see details");
                currentPage.clearCurrentMoviesList();
                currentPage.populateCurrentMoviesList(input);

                if (currentPage.getCurrentMovieList().size() == 0) {
//                    currentPage.setCurrentUser(null);
//                    DisplayCommand.writeInOutput(output, 0, currentPage);
                    ErrorDisplay.displayError(output);
                } else {
                    // urmeaza un else in care ma leg si de campul movie
                    MovieInput movie = currentPage.findMovie(actionInput.getMovie());
                    if (movie == null) {
                        ErrorDisplay.displayError(output);
                    } else {
                        currentPage.clearCurrentMoviesList();
                        currentPage.getCurrentMovieList().add(movie);
                        DisplayCommand.writeInOutput(output, 1, currentPage);
                        currentPage.setPageName("see details");
                    }
                }
            }
        }

        // se poate da logout din multe parti dar nu din neautentificat
        if (actionInput.getPage().compareTo("logout") == 0) {
            currentPage.setCurrentUser(null);
            currentPage.setPageName("Homepage neautentificat");
        }

        if (actionInput.getPage().compareTo("movies") == 0) {
            if (currentPage.getPageName().compareTo("upgrades") == 0 || currentPage.getPageName().compareTo("see details") == 0) {

                currentPage.setPageName("movies");
                // populez lista currentMovies din current page cu filmele ce nu sunt banate pentru tara utilizatorului curent
                currentPage.clearCurrentMoviesList();
                currentPage.populateCurrentMoviesList(input);

                DisplayCommand.writeInOutput(output, 1, currentPage);

                currentPage.clearCurrentMoviesList();

            }
        }

    }
}
