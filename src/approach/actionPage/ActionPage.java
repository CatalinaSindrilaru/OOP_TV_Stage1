package approach.actionPage;

import approach.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInput;
import fileio.Input;

public interface ActionPage {
    public void resolveCommand(CurrentPage currentPage, ActionInput actionInput,
                               Input input, ArrayNode output);
}
