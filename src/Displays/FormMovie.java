package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.MovieInput;

import java.util.ArrayList;

public class FormMovie {

    static public ObjectNode movieFormed(MovieInput movie) {

        ObjectMapper mapper = new ObjectMapper();

        ArrayNode genres = stringListFormed(movie.getGenres());
        ArrayNode actors = stringListFormed(movie.getActors());
        ArrayNode countriesBanned = stringListFormed(movie.getCountriesBanned());

        ObjectNode movieObjectNode = mapper.createObjectNode();

        movieObjectNode.put("name", movie.getName());
        movieObjectNode.put("year", movie.getYear());
        movieObjectNode.put("duration", movie.getDuration());
        movieObjectNode.set("genres", genres);
        movieObjectNode.set("actors", actors);
        movieObjectNode.set("countriesBanned", countriesBanned);
        movieObjectNode.put("numLikes", movie.getNumLikes());
        movieObjectNode.put("rating", movie.getRating());
        movieObjectNode.put("numRatings", movie.getNumRatings());


        return movieObjectNode;
    }

    static private ArrayNode stringListFormed(ArrayList<String> list) {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode listArrayNode = mapper.createArrayNode();

        for (String s : list) {
            listArrayNode.add(s);
        }

        return listArrayNode;
    }
}
