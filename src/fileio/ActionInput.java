package fileio;

public class ActionInput {
    private String type;
    private String page;
    private String feature;

    private FiltersInput filters;
    private Credentials credentials;
    private String startsWith;
    private int count;
    private int rate;

    private String movie;

    public ActionInput() {

    }

    public ActionInput(String type, String page, String feature, FiltersInput filters,
                       Credentials credentials, String startsWith, int count, int rate,
                       String movie) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.filters = filters;
        this.credentials = credentials;
        this.startsWith = startsWith;
        this.count = count;
        this.rate = rate;
        this.movie = movie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public FiltersInput getFilters() {
        return filters;
    }

    public void setFilters(FiltersInput filters) {
        this.filters = filters;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
