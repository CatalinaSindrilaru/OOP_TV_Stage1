package approach.pages;

public class ErrorsPagesFactory {

    public Errors createPage(String type) {

        if (type.compareTo("Homepage neautentificat") == 0) {
            return new UnauthenticatedHomepage();
        } else if (type.compareTo("Homepage autentificat") == 0) {
            return new AuthenticatedHomepage();
        } else {
            return new MoviesPage();
        }
    }
}
