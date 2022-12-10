package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.MovieInput;

import java.text.Normalizer;
import java.util.ArrayList;

public class FormMoviesList {

    static public ArrayNode moviesListFormed(ArrayList<MovieInput> movies) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode listFormed = mapper.createArrayNode();

        for (MovieInput movie : movies) {
            ObjectNode movieFormed = mapper.createObjectNode();
            movieFormed = FormMovie.movieFormed(movie); // functie care intoarce un objectnode pt un film

            listFormed.add(movieFormed);
        }

        return listFormed;
    }
}
