package fileio;

import Displays.FormMoviesList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class ErrorDisplay {
    static public void displayError(ArrayNode output) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        String nullString = null;

        outputCommand.put("error", "Error");

        ArrayNode currentMovieList = FormMoviesList.moviesListFormed(new ArrayList<MovieInput>());
        outputCommand.set("currentMoviesList", currentMovieList);
        outputCommand.put("currentUser", nullString);
        output.add(outputCommand);
    }
}
