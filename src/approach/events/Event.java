package approach.events;

import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public interface Event {

    void makeEvent(CurrentPage currentPage, ActionInput actionInput, Input input,
                   ArrayNode output);
}
