package de.uniba.dsg.jaxrs.db;

import de.uniba.dsg.jaxrs.models.logic.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MovieDB {

    private final List<Movie> movies;

    public MovieDB() {
        this.movies = this.initMovies();
    }

    /**
     * Initialize movies (mimic a database)
     *
     * @return
     */
    private List<Movie> initMovies() {
        return new ArrayList<>(Arrays.asList(
                new Movie(1, "The Lion King", 1994),
                new Movie(2, "Garfield", 1978),
                new Movie(3, "Looney Toons", -1),
                new Movie(4, "Winnie the Pooh", 1921),
                new Movie(5, "The Lion King II: Simba's Pride", 1998),
                new Movie(6, "Pinocchio", 2019),
                new Movie(7, "Austin Powers", -1)
        ));
    }

    // SUPPORTED QUERIES :)

    /**
     * Get movie by its name.
     *
     * @param name
     * @return
     */
    public Movie getMovieByName(final String name) {
        return this.movies.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public Movie getFamousMovieById(final int movieId) {
        return this.movies.stream().filter(m -> m.getMovieId() == movieId).findFirst().orElse(null);
    }


    public void addMovieToDb(final Movie m) {
        m.setMovieId(this.movies.stream().map(Movie::getMovieId).max(Comparator.naturalOrder()).orElse(0) + 1);
        System.out.println(m);
        this.movies.add(m);
    }
}
