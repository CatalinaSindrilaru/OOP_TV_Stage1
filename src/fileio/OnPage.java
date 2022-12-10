package fileio;

public class OnPage {

    private static OnPage instance = null;

    private OnPage() {

    }

    public static OnPage getInstance() {
        if (instance == null) {
            instance = new OnPage();
        }
        return instance;
    }

    public int resolveCommand(Input input, CurrentPage currentPage, ActionInput actionInput) {

        if (actionInput.getFeature().compareTo("register") == 0) {
            if (currentPage.getPageName().compareTo("register") == 0) {
                Credentials credentials = actionInput.getCredentials();

                UserInput user = new UserInput();
                user.setCredentials(credentials);

                input.addUser(user);

                currentPage.setCurrentUser(user);
                currentPage.setPageName("Homepage autentificat");
            }
        }

        if (actionInput.getFeature().compareTo("login") == 0) {
            if (currentPage.getPageName().compareTo("login") == 0) {

                if (input.findUser(actionInput.getCredentials()) != null) {

                    currentPage.setCurrentUser(input.findUser(actionInput.getCredentials()));
                    currentPage.setPageName("Homepage autentificat");
                } else {

                    // eroare la login, userul nu exista in baza de date
                    currentPage.setPageName("Homepage neautentificat");
                    return 0;
                }
            } else {
                return 0;
            }
        }

        return 1;
    }
}
