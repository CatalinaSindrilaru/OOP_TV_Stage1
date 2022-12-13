package approach.pages;

import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;

public class AuthenticatedHomepage implements Errors{
    @Override
    public boolean checkErrors(CurrentPage currentPage, ActionInput actionInput, ArrayNode output) {
        if (actionInput.getPage().compareTo("movies") != 0
                && actionInput.getPage().compareTo("upgrades") != 0
                && actionInput.getPage().compareTo("logout") != 0) {

            ErrorDisplay.displayError(output);
            return true;
        }
        return false;
    }
}
