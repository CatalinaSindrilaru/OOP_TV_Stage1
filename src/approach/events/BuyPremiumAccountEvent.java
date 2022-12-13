package approach.events;

import Displays.ErrorDisplay;
import approach.BuyFeatures;
import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public class BuyPremiumAccountEvent implements Event{
    @Override
    public void makeEvent(CurrentPage currentPage, ActionInput actionInput,
                          Input input, ArrayNode output) {

        if (currentPage.getPageName().compareTo("upgrades") == 0) {
            BuyFeatures.buyPremiumAccount(currentPage);
        } else {
            ErrorDisplay.displayError(output);
        }
    }
}
