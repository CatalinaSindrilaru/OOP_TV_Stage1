package fileio;

/**
 * Contains all the actions
 */
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

    public ActionInput(final String type, final String page, final String feature,
                       final FiltersInput filters, final Credentials credentials,
                       final String startsWith, final int count, final int rate,
                       final String movie) {
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

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type new value
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @return page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page new value
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * @return feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * @param feature new value
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * @return credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * @param credentials new value
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @return startsWith
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * @param startsWith new value
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count new value
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * @return rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate new value
     */
    public void setRate(final int rate) {
        this.rate = rate;
    }

    /**
     * @return filters
     */
    public FiltersInput getFilters() {
        return filters;
    }

    /**
     * @param filters new value
     */
    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    /**
     * @return movie
     */
    public String getMovie() {
        return movie;
    }

    /**
     * @param movie new value
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }
}
