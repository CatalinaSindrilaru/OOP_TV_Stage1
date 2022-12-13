package approach.actionPage;

import Displays.DisplayCommand;
import Displays.ErrorDisplay;
import approach.BuyFeatures;
import approach.CurrentPage;
import approach.events.Event;
import approach.events.EventsFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;

public class OnPage implements ActionPage {


    @Override
    public void resolveCommand(CurrentPage currentPage, ActionInput actionInput, Input input,
                               ArrayNode output) {

        EventsFactory eventsFactory = new EventsFactory();
        Event event = null;

        if (actionInput.getFeature().compareTo("register") == 0) {
            event = eventsFactory.createEvent("register");

        } else if (actionInput.getFeature().compareTo("login") == 0) {
            event = eventsFactory.createEvent("login");

        } else if (actionInput.getFeature().compareTo("filter") == 0) {
            event = eventsFactory.createEvent("filter");

        } else if (actionInput.getFeature().compareTo("search") == 0) {
            event = eventsFactory.createEvent("search");

        } else if (actionInput.getFeature().compareTo("buy tokens") == 0) {
            event = eventsFactory.createEvent("buy tokens");

        } else if (actionInput.getFeature().compareTo("buy premium account") == 0) {
            event = eventsFactory.createEvent("buy premium account");

        } else if (actionInput.getFeature().compareTo("purchase") == 0) {
            event = eventsFactory.createEvent("purchase");

        } else if (actionInput.getFeature().compareTo("watch") == 0) {
            event = eventsFactory.createEvent("watch");

        } else if (actionInput.getFeature().compareTo("like") == 0) {
            event = eventsFactory.createEvent("like");

        } else if (actionInput.getFeature().compareTo("rate") == 0) {
            event = eventsFactory.createEvent("rate");

        }

        assert event != null;
        event.makeEvent(currentPage, actionInput, input, output);
    }
}
