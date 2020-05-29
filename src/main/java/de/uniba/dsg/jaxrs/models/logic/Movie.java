package de.uniba.dsg.jaxrs.models.logic;

public class Movie {
    private int movieId;
    private String name;
    private int year;

    public Movie(final int movieId, final String name, final int year) {
        this.movieId = movieId;
        this.name = name;
        this.year = year;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public void setMovieId(final int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * year is currently the only field, which needs an update
     *
     * @param m
     */
    public void update(final Movie m) {
        this.year = m.year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + this.movieId +
                ", name='" + this.name + '\'' +
                ", year=" + this.year +
                '}';
    }
}
