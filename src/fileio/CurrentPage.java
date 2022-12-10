package fileio;

import java.util.ArrayList;

public class CurrentPage {
    private String pageName;
    private UserInput currentUser;

    private ArrayList<MovieInput> currentMovieList = new ArrayList<>();

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public UserInput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInput currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<MovieInput> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(ArrayList<MovieInput> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public void populateCurrentMoviesList() {

    }
}
