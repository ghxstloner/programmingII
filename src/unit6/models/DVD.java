package unit6.models;

public class DVD extends LibraryItem {
    private String director;

    public DVD(String title, String author, String itemID, String director) {
        super(title, author, itemID);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return super.toString() + ", Director: " + director;
    }
}
