package fileio;

import java.util.ArrayList;

public class CurrentPage {
    private String pageName;
    private UserInput currentUser;

    private ArrayList<MovieInput> currentMovieList = new ArrayList<>();

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public UserInput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInput currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<MovieInput> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(ArrayList<MovieInput> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public void populateCurrentMoviesList(Input input) {

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

    public void clearCurrentMoviesList() {
        currentMovieList.clear();
    }

    public void filterMoviesList(ActionInput actionInput) {

        if (actionInput.getFilters().getContains() != null) {

            // sterg filmele care nu au acei actori
            if (actionInput.getFilters().getContains().getActors() != null) {
                for (int i = 0; i < currentMovieList.size(); i++) {
                    MovieInput movie = currentMovieList.get(i);
                    ArrayList<String> actorsMovie = movie.getActors();
                    ArrayList<String> actorsNeeded = actionInput.getFilters().getContains().getActors();

                    for (String actor : actorsNeeded) {
                        if (!actorsMovie.contains(actor)) {
                            currentMovieList.remove(movie);
                            i--;
                            break;
                        }
                    }
                }
            }

            // sterg filmele care nu au acele genuri
            if (actionInput.getFilters().getContains().getGenre() != null) {
                for (int i = 0; i < currentMovieList.size(); i++) {
                    MovieInput movie = currentMovieList.get(i);
                    ArrayList<String> genresMovie = movie.getGenres();
                    ArrayList<String> genresNeeded = actionInput.getFilters().getContains().getGenre();

                    for (String genre : genresNeeded) {
                        if (!genresMovie.contains(genre)) {
                            currentMovieList.remove(movie);
                            i--;
                            break;
                        }
                    }
                }
            }
        }

        // sortare
        if (actionInput.getFilters().getSort() != null) {
            Sort.sortMovies(currentMovieList, actionInput.getFilters().getSort());
        }

    }

    public MovieInput findMovie(String prefix) {

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
