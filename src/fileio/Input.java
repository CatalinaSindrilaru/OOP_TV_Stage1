package fileio;

import java.util.ArrayList;
import java.util.HashMap;

public class Input {
    private ArrayList<UserInput> users;
    private ArrayList<MovieInput> movies;
    private ArrayList<ActionInput> actions;

    public Input() {

    }

    public Input(ArrayList<UserInput> users, ArrayList<MovieInput> movies, ArrayList<ActionInput> actions) {
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserInput> users) {
        this.users = users;
    }

    public ArrayList<MovieInput> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionInput> actions) {
        this.actions = actions;
    }

    public void addUser(UserInput user) {
        users.add(user);
    }


    public UserInput findUser(Credentials credentials) {

        for (UserInput user : users) {

            if (user.getCredentials().getName().compareTo(credentials.getName()) == 0) {
                if (user.getCredentials().getPassword().compareTo(credentials.getPassword()) == 0) {
                    return user;
                }
            }
        }

        return null;
    }
}
