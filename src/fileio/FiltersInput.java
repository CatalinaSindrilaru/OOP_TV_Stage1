package fileio;

public class FiltersInput {
    private FiltersSort sort;
    private FiltersContains contains;

    public FiltersInput() {

    }

    public FiltersInput(FiltersSort sort, FiltersContains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public FiltersSort getSort() {
        return sort;
    }

    public void setSort(FiltersSort sort) {
        this.sort = sort;
    }

    public FiltersContains getContains() {
        return contains;
    }

    public void setContains(FiltersContains contains) {
        this.contains = contains;
    }
}
