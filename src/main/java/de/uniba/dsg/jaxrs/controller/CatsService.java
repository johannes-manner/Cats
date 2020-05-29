package de.uniba.dsg.jaxrs.controller;

import de.uniba.dsg.jaxrs.db.CatsDB;
import de.uniba.dsg.jaxrs.db.MovieDB;
import de.uniba.dsg.jaxrs.models.logic.Cat;
import de.uniba.dsg.jaxrs.models.logic.Movie;

import java.util.List;
import java.util.Optional;

public class CatsService {

    public static final CatsService instance = new CatsService();

    private final CatsDB cats;
    private final MovieDB movies;

    public CatsService() {
        this.movies = new MovieDB();
        this.cats = new CatsDB(this.movies);
    }


    // GET

    /**
     * Get all cats.
     *
     * @return
     */
    public List<Cat> getFamousCats() {
        return this.cats.getAllFamousCats();
    }

    public List<Movie> getFamousMovies() {
        return this.movies.getMovies();
    }

    public Movie getFamousMovieById(final int movieId) {
        return this.movies.getFamousMovieById(movieId);
    }

    public List<Cat> getFamousCats(final String movieName) {
        return this.cats.getFamousCats(movieName);
    }

    public Cat getFamousCat(final int id) {
        return this.cats.getFamousCat(id);
    }


    // POST

    /**
     * Add a cat to the in memory database :)
     *
     * @param newCat
     * @return
     */
    public Cat addCat(final Cat newCat) {
        if (newCat == null) {
            return null;
        }
        this.cats.add(newCat);

        for (final Movie m : newCat.getMovies()) {
            if (this.movies.getMovieByName(m.getName()) == null) {
                this.movies.addMovieToDb(m);
            } else {
                final Movie movieDb = this.movies.getMovieByName(m.getName());
                movieDb.update(m);
                m.setMovieId(movieDb.getMovieId());
            }
        }

        return newCat;
    }

    public Movie addMovie(final Movie newMovie) {
        if (newMovie == null) {
            return null;
        }

        this.movies.addMovieToDb(newMovie);
        return newMovie;
    }

    // PUT

    /**
     * Update an existing cat.
     *
     * @param id
     * @param updatedCat
     * @return
     */
    public Cat updateCat(final int id, final Cat updatedCat) {
        // db access :) - here we work on the reference, so no storing is needed at the end :)
        final Cat cat = this.getFamousCat(id);

        if (cat == null || updatedCat == null) {
            return null;
        }
        Optional.ofNullable(updatedCat.getPopularity()).ifPresent(d -> cat.setPopularity(d));
        Optional.ofNullable(updatedCat.getName()).ifPresent(d -> cat.setName(d));
        Optional.ofNullable(updatedCat.getImageUrl()).ifPresent(d -> cat.setImageUrl(d));
        if (updatedCat.getMovies() != null) {
            for (final Movie m : updatedCat.getMovies()) {
                // create a movie first and then add it to the cat
                if (this.movies.getMovieByName(m.getName()) == null) {
                    this.movies.addMovieToDb(m);
                    cat.getMovies().add(m);
                } else {
                    final Movie movieDb = this.movies.getMovieByName(m.getName());
                    movieDb.update(m);
                    m.setMovieId(movieDb.getMovieId());
                }
            }
        }

        return cat;
    }

    // DELETE
    public boolean deleteCat(final int id) {
        return this.cats.deleteCat(id);
    }
}
