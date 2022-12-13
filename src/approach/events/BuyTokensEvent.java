package approach.events;

import Displays.ErrorDisplay;
import approach.BuyFeatures;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public class BuyTokensEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                          ArrayNode output) {

        if (currentPage.getPageName().compareTo("upgrades") == 0)  {
            BuyFeatures.buyTokens(currentPage, actionInput.getCount());
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
