package approach;

import fileio.FiltersSort;
import fileio.MovieInput;

import java.util.ArrayList;

public class Sort {

    static public void sortMovies(ArrayList<MovieInput> movies, FiltersSort sort) {

        String rating = sort.getRating();
        String duration = sort.getDuration();

        movies.sort((o1, o2) -> {

            if (duration != null && rating != null) {
                if (duration.compareTo("increasing") == 0) {
                    return compareDuration(o1, o2, o1.getDuration(), o2.getDuration(), rating);
                } else {

                    return compareDuration(o1, o2, o2.getDuration(), o1.getDuration(), rating);
                }

            } else if (duration != null) {
                if (duration.compareTo("increasing") == 0) {
                    return Integer.compare(o1.getDuration(), o2.getDuration());
                } else {
                    return Integer.compare(o2.getDuration(), o1.getDuration());
                }

            } else {
                if (rating.compareTo("increasing") == 0) {
                    return Float.compare(o1.getRating(), o2.getRating());
                } else {
                    return Float.compare(o2.getRating(), o1.getRating());
                }
            }
        });
    }

    private static int compareDuration(MovieInput o1, MovieInput o2, int duration,
                                       int duration2, String rating) {

        if (o1.getDuration() != o2.getDuration()) {
            return Integer.compare(duration, duration2);
        } else {

            if (rating.compareTo("increasing") == 0) {
                return Float.compare(o1.getRating(), o2.getRating());
            } else {
                return Float.compare(o2.getRating(), o1.getRating());
            }
        }
    }
}
