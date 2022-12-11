package fileio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Sort {

    static public void sortMovies(ArrayList<MovieInput> movies, FiltersSort sort) {

        // valori cu descrescator crescator
        String rating = sort.getRating();
        String duration = sort.getDuration();

        // sunt sortate dupa rating, iar in caz de egalitate sunt sortare dupa durata

        movies.sort(new Comparator<MovieInput>() {
            @Override
            public int compare(MovieInput o1, MovieInput o2) {

                if (rating.compareTo("increasing") == 0) {
                    if (Float.compare(o1.getRating(), o2.getRating()) != 0) {
                        return Float.compare(o1.getRating(), o2.getRating());  // o1 - o2
                    } else {

                        if (duration.compareTo("increasing") == 0) {
                            return Integer.compare(o1.getDuration(), o2.getDuration());
                        } else {
                            return Integer.compare(o2.getDuration(), o1.getDuration());
                        }
                    }
                } else {
                    if (Float.compare(o2.getRating(), o1.getRating()) != 0) {
                        return Float.compare(o2.getRating(), o1.getRating());
                    } else {

                        if (duration.compareTo("increasing") == 0) {
                            return Integer.compare(o1.getDuration(), o2.getDuration());
                        } else {
                            return Integer.compare(o2.getDuration(), o1.getDuration());
                        }
                    }
                }
            }
        });
    }
}
