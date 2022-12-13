package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import approach.CurrentPage;

public class DisplayCommand {

    static public void writeInOutput(ArrayNode output, CurrentPage currentPage) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        ArrayNode currentMovieList
                = FormMoviesList.moviesListFormed(currentPage.getCurrentMovieList());

        ObjectNode currentUserFormed
                = FormCurrentUser.currentUserFormed(currentPage.getCurrentUser());

        outputCommand.set("error", null);
        outputCommand.set("currentMoviesList", currentMovieList);
        outputCommand.set("currentUser", currentUserFormed);

        output.add(outputCommand);
    }
}
