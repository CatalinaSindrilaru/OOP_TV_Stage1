package fileio;

import Displays.DisplayCommand;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class ChangePage {
    private static ChangePage instance = null;

    private ChangePage() {

    }

    public static ChangePage getInstance() {
        if (instance == null) {
            instance = new ChangePage();
        }
        return instance;
    }

    public void resolveCommand(CurrentPage currentPage, Input input,  ActionInput actionInput, ArrayNode output) {

        if (currentPage.getPageName().compareTo("Homepage neautentificat") == 0) {
            if (actionInput.getPage().compareTo("register") == 0 || actionInput.getPage().compareTo("login") == 0) {
                currentPage.setPageName(actionInput.getPage());
            } else {
                // eroare daca se incearca alta comanda
                DisplayCommand.writeInOutput(output, 0, currentPage);
            }
        }

        if (currentPage.getPageName().compareTo("Homepage autentificat") == 0) {

            if (actionInput.getPage().compareTo("logout") == 0) {
                currentPage.setCurrentUser(null);
                currentPage.setPageName("Homepage neautentificat");

            }else if(actionInput.getPage().compareTo("movies") == 0) {

                currentPage.setPageName("movies");
                // populez lista currentMovies din current page cu filmele ce nu sunt banate pentru tara utilizatorului curent


            } else {
                currentPage.setCurrentUser(null);
                currentPage.setPageName("Homepage neautentificat");
            }


        }
    }
}
