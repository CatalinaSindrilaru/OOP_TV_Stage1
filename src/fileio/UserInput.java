package fileio;

import java.rmi.MarshalledObject;
import java.util.ArrayList;

public class UserInput {
    private Credentials credentials;

    private int tokensCount = 0; // numărul de tokeni pe care îl are utilizatorul la momentul curent
    private int numFreePremiumMovies = 15; // numărul de filme gratuite disponibile în caz de cont premium pe care le mai are utilizatorul la dispoziție
    private ArrayList<MovieInput> purchasedMovies = new ArrayList<>(); // filmele cumpărate de utilizator
    private ArrayList<MovieInput> watchedMovies = new ArrayList<>(); // filmele vizualizate de către utilizator
    private ArrayList<MovieInput> likedMovies = new ArrayList<>();  // filmele apreciate de către utilizator
    private ArrayList<MovieInput> ratedMovies = new ArrayList<>(); // filmele cărora utilizatorul curent le-a oferit o notă

    public UserInput() {

    }
    public UserInput(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<MovieInput> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<MovieInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MovieInput> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<MovieInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MovieInput> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<MovieInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MovieInput> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<MovieInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public void addAtPurchasedMovies(MovieInput movie) {
        this.purchasedMovies.add(movie);
    }

    public void addAtWatchedMovies(MovieInput movie) {
        this.watchedMovies.add(movie);
    }

    public void addAtLikedMovies(MovieInput movie) {
        this.likedMovies.add(movie);
    }

    public void addAtRatedMovies(MovieInput movie) {
        this.ratedMovies.add(movie);
    }


//    public MovieInput findMovie(String prefix) {
//
//        if (currentMovieList.size() != 0) {
//            for (MovieInput movie : currentMovieList) {
//                if (movie.getName().startsWith(prefix)) {
//                    return movie;
//                }
//            }
//        }
//        return null;
//    }

    public boolean purchasedMovie(MovieInput purchasedMovie) {

        for (MovieInput movie : purchasedMovies) {
            if (movie.getName().compareTo(purchasedMovie.getName()) == 0)
                return true;
        }

        return false;
    }

    public boolean watchedMovie(MovieInput watchedMovie) {

        for (MovieInput movie : watchedMovies) {
            if (movie.getName().compareTo(watchedMovie.getName()) == 0)
                return true;
        }

        return false;
    }
}
