package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Credentials;
import fileio.UserInput;

public class FormCurrentUser {

    static public ObjectNode currentUserFormed(UserInput user) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode userObjectNode = mapper.createObjectNode();

        ObjectNode credentials = credentialsFormed(user.getCredentials());

        ArrayNode purchasedMovies = FormMoviesList.moviesListFormed(user.getPurchasedMovies());
        ArrayNode watchedMovies = FormMoviesList.moviesListFormed(user.getWatchedMovies());
        ArrayNode likedMovie = FormMoviesList.moviesListFormed(user.getLikedMovies());
        ArrayNode ratedMovie = FormMoviesList.moviesListFormed(user.getRatedMovies());

        userObjectNode.set("credentials", credentials);
        userObjectNode.put("tokensCount", user.getTokensCount());
        userObjectNode.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        userObjectNode.set("purchasedMovies", purchasedMovies);
        userObjectNode.set("watchedMovies", watchedMovies);
        userObjectNode.set("likedMovies", likedMovie);
        userObjectNode.set("ratedMovies", ratedMovie);

        return userObjectNode;
    }

    static private ObjectNode credentialsFormed(Credentials credentials) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode credentialsObjectNode = mapper.createObjectNode();

        credentialsObjectNode.put("name", credentials.getName());
        credentialsObjectNode.put("password", credentials.getPassword());
        credentialsObjectNode.put("accountType", credentials.getAccountType());
        credentialsObjectNode.put("country", credentials.getCountry());
        credentialsObjectNode.put("balance", credentials.getBalance());

        return credentialsObjectNode;
    }
}
