package approach.events;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public class FilterEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("movies") == 0) {

            currentPage.clearCurrentMoviesList();
            currentPage.populateCurrentMoviesList(input);
            currentPage.filterMoviesList(actionInput);
            DisplayCommand.writeInOutput(output, currentPage);

        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
