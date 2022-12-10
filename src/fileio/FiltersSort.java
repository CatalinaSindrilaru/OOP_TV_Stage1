package fileio;

public class FiltersSort {
    private String rating;
    private String duration;

    public FiltersSort() {

    }

    public FiltersSort(String rating, String duration) {
        this.rating = rating;
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
