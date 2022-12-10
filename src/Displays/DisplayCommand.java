package Displays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CurrentPage;
import fileio.Input;

public class DisplayCommand {

    static public void writeInOutput(ArrayNode output, int error, CurrentPage currentPage) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        String errorMessage = null;
        if (error == 0) {
            errorMessage = "Error";
        }

        String nullString = null;
        outputCommand.put("error", errorMessage);
        ArrayNode currentMovieList = FormMoviesList.moviesListFormed(currentPage.getCurrentMovieList());
        outputCommand.set("currentMoviesList", currentMovieList);

        if (currentPage.getCurrentUser() == null) {

            outputCommand.put("currentUser", nullString);

        } else {
            ObjectNode currentUserFormed = FormCurrentUser.currentUserFormed(currentPage.getCurrentUser());
            outputCommand.set("currentUser", currentUserFormed);
        }

        output.add(outputCommand);
    }
}
