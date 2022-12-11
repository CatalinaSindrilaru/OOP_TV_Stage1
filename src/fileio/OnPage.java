package fileio;

import Displays.DisplayCommand;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class OnPage {

    private static OnPage instance = null;

    private OnPage() {

    }

    public static OnPage getInstance() {
        if (instance == null) {
            instance = new OnPage();
        }
        return instance;
    }

    public int resolveCommand(Input input, CurrentPage currentPage, ActionInput actionInput, ArrayNode output, String prevAction) {

        if (actionInput.getFeature().compareTo("register") == 0) {
            if (currentPage.getPageName().compareTo("register") == 0) {
                Credentials credentials = actionInput.getCredentials();

                UserInput user = new UserInput();
                user.setCredentials(credentials);

                input.addUser(user);

                currentPage.setCurrentUser(user);
                currentPage.setPageName("Homepage autentificat");
                currentPage.clearCurrentMoviesList();
                DisplayCommand.writeInOutput(output, 1, currentPage);
            }
            // posibil else return 0
        }

        if (actionInput.getFeature().compareTo("login") == 0) {
            if (currentPage.getPageName().compareTo("login") == 0) {

                if (input.findUser(actionInput.getCredentials()) != null) {

                    currentPage.setCurrentUser(input.findUser(actionInput.getCredentials()));
                    currentPage.setPageName("Homepage autentificat");
                    currentPage.clearCurrentMoviesList();
                    DisplayCommand.writeInOutput(output, 1, currentPage);
                } else {

                    // eroare la login, userul nu exista in baza de date
                    currentPage.setPageName("Homepage neautentificat");
                    return 0;
                }
            } else {
//                currentPage.setCurrentUser(null);
                return 0;
            }
        }

        if (actionInput.getFeature().compareTo("filter") == 0) {
            if (currentPage.getPageName().compareTo("movies") == 0) {

                currentPage.clearCurrentMoviesList();
                currentPage.populateCurrentMoviesList(input);
                // filtrez
                currentPage.filterMoviesList(actionInput);

                DisplayCommand.writeInOutput(output, 1, currentPage);

            } else {
                currentPage.setCurrentUser(null); // posibil doar returnare eroare
                return 0;
            }
        }

        if (actionInput.getFeature().compareTo("search") == 0) {
            if (currentPage.getPageName().compareTo("movies") == 0) {

                MovieInput movie = currentPage.findMovie(actionInput.getStartsWith());
                currentPage.clearCurrentMoviesList();
                if (movie != null) {
                    currentPage.getCurrentMovieList().add(movie);
                }

                DisplayCommand.writeInOutput(output, 1, currentPage);
            }
        }

        if (actionInput.getFeature().compareTo("buy tokens") == 0) {
            if (currentPage.getPageName().compareTo("upgrades") == 0)  { // posibil si egal cu movies page

                BuyFeatures.buyTokens(currentPage, actionInput.getCount());
            }
            // posibil alta actiune in caz de eroare
        }

        if (actionInput.getFeature().compareTo("buy premium account") == 0) {
            if (currentPage.getPageName().compareTo("upgrades") == 0)  { // posibil si egal cu movies page

                // valoarea unui cont premium este egala cu 10 tokens
                BuyFeatures.buyPremiumAccount(currentPage);
            }
        }

        if (actionInput.getFeature().compareTo("purchase") == 0) {
            if (currentPage.getPageName().compareTo("see details") == 0) {

                MovieInput movie = currentPage.findMovie(actionInput.getMovie());

                UserInput currentUser = currentPage.getCurrentUser();
                if (currentUser.getCredentials().getAccountType().compareTo("premium") == 0 && currentUser.getNumFreePremiumMovies() > 1) {

                    int freeMovies = currentUser.getNumFreePremiumMovies();
                    currentUser.setNumFreePremiumMovies(freeMovies - 1);
                } else {
                    int tokens = currentUser.getTokensCount();
                    currentUser.setTokensCount(tokens - 2);
                }

                currentUser.addAtPurchasedMovies(movie);
                DisplayCommand.writeInOutput(output, 1, currentPage);
            } // posibil si aici sa pun pe else eroare
        }

        if (actionInput.getFeature().compareTo("watch") == 0) {
            if (currentPage.getPageName().compareTo("see details") == 0) {
                // verific daca filmul a fost cumparat mai intai de user

                MovieInput movie = currentPage.getCurrentMovieList().get(0);

                if (currentPage.getCurrentUser() != null && currentPage.getCurrentUser().purchasedMovie(movie)) {
                    currentPage.getCurrentUser().addAtWatchedMovies(movie);
                    DisplayCommand.writeInOutput(output, 1, currentPage);
                } else {
                    ErrorDisplay.displayError(output);
                }
            } else {
                ErrorDisplay.displayError(output);
            }
        }

        if (actionInput.getFeature().compareTo("like") == 0) {
            if (currentPage.getPageName().compareTo("see details") == 0) {
                // verific mai intai daca filmul a fost vizualizat
                MovieInput movie = currentPage.getCurrentMovieList().get(0);

                if (currentPage.getCurrentUser() != null && currentPage.getCurrentUser().watchedMovie(movie)) {

                    int numLikes = movie.getNumLikes();
                    movie.setNumLikes(numLikes + 1);
                    currentPage.getCurrentUser().addAtLikedMovies(movie);
                    DisplayCommand.writeInOutput(output, 1, currentPage);
                } else {
                    ErrorDisplay.displayError(output);
                }
            } else {
                ErrorDisplay.displayError(output);
            }
        }

        if (actionInput.getFeature().compareTo("rate") == 0) {
            if (currentPage.getPageName().compareTo("see details") == 0) {
                // verific mai intai daca filmul a fost vizuaalizat
                MovieInput movie = currentPage.getCurrentMovieList().get(0);

                if (currentPage.getCurrentUser() != null && currentPage.getCurrentUser().watchedMovie(movie)) {

                    int rate = actionInput.getRate();

                    int oldSumRatings = movie.getSumRatings();
                    int oldNumRatings = movie.getNumRatings();

                    movie.setSumRatings(oldSumRatings + rate);
                    movie.setNumRatings(oldNumRatings + 1);

                    float newRating = (float)movie.getSumRatings() / movie.getNumRatings();
                    movie.setRating(newRating);

                    currentPage.getCurrentUser().addAtRatedMovies(movie);
                    DisplayCommand.writeInOutput(output, 1, currentPage);

                } else {
                    ErrorDisplay.displayError(output);
                }

            } else {
                ErrorDisplay.displayError(output);
            }
        }
        return 1;
    }
}
