package approach;

import approach.filters.Filter;
import approach.filters.FiltersFactory;
import approach.filters.Sort;
import fileio.UserInput;
import fileio.MovieInput;
import fileio.Input;
import fileio.ActionInput;

import java.util.ArrayList;

/**
 * Class that contains information about the current page like name, user that is logged
 * at the moment, and an array list with the movies that the actual user has
 */
public final class CurrentPage {
    private String pageName;
    private UserInput currentUser;

    private ArrayList<MovieInput> currentMovieList = new ArrayList<>();

    private static CurrentPage instance = null;

    private CurrentPage() {

    }

    /**
     * Returns the instance of a CurrentPage class (Singleton)
     * @return CurrentPage instance
     */
    public static CurrentPage getInstance() {
        if (instance == null) {

            instance = new CurrentPage();
        }

        return instance;
    }

    /**
     * @return pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName new value
     */
    public void setPageName(final String pageName) {
        this.pageName = pageName;
    }

    /**
     * @return currentUser
     */
    public UserInput getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser new value
     */
    public void setCurrentUser(final UserInput currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return currentMovieList
     */
    public ArrayList<MovieInput> getCurrentMovieList() {
        return currentMovieList;
    }

    /**
     * @param currentMovieList new value
     */
    public void setCurrentMovieList(final ArrayList<MovieInput> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    /**
     * Add all the movies that are not banned for the currentUser in the
     * currentMovieList
     * @param input input of the program
     */
    public void populateCurrentMoviesList(final Input input) {

        if (currentUser != null) {
            String countryUser = currentUser.getCredentials().getCountry();

            for (MovieInput movie : input.getMovies()) {
                ArrayList<String> countriesBanned = movie.getCountriesBanned();

                if (!countriesBanned.contains(countryUser)) {
                    currentMovieList.add(movie);
                }
            }
        }
    }

    /**
     * Deletes all the elements from the currentMovieList
     */
    public void clearCurrentMoviesList() {
        currentMovieList.clear();
    }

    /**
     * Filters the movies list by actors, genres, rating, duration
     * @param actionInput current action
     */
    public void filterMoviesList(final ActionInput actionInput) {

        if (actionInput.getFilters().getContains() != null) {

            FiltersFactory filtersFactory = new FiltersFactory();
            Filter filter;

            /* Delete the movies that do not contain these actors */
            if (actionInput.getFilters().getContains().getActors() != null) {
                filter = filtersFactory.createFilter("actors");
                filter.filter(currentMovieList, actionInput);
            }

            /* Delete the movies that do not have these genres */
            if (actionInput.getFilters().getContains().getGenre() != null) {
                filter = filtersFactory.createFilter("genres");
                filter.filter(currentMovieList, actionInput);
            }
        }

        /* Sort the movies */
        if (actionInput.getFilters().getSort() != null) {
            Sort.sortMovies(currentMovieList, actionInput.getFilters().getSort());
        }

    }

    /**
     * Find and return the movie that starts with the given word
     * @param prefix String
     * @return movie
     */
    public MovieInput findMovie(final String prefix) {

        if (currentMovieList.size() != 0) {
            for (MovieInput movie : currentMovieList) {
                if (movie.getName().startsWith(prefix)) {
                    return movie;
                }
            }
        }
        return null;
    }
}
