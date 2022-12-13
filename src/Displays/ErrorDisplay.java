package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class ErrorDisplay {
    static public void displayError(ArrayNode output) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        ArrayNode currentMovieList
                = FormMoviesList.moviesListFormed(new ArrayList<>());

        outputCommand.put("error", "Error");
        outputCommand.set("currentMoviesList", currentMovieList);
        outputCommand.set("currentUser", null);

        output.add(outputCommand);
    }
}
