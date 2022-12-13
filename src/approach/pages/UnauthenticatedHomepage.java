package approach.pages;

import Displays.ErrorDisplay;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;

public class UnauthenticatedHomepage implements Errors{
    @Override
    public boolean checkErrors(CurrentPage currentPage, ActionInput actionInput, ArrayNode output) {

        if (actionInput.getPage().compareTo("login") != 0 && actionInput.getPage().compareTo("register") != 0) {
            ErrorDisplay.displayError(output);
            return true;
        }
        return false;
    }
}
