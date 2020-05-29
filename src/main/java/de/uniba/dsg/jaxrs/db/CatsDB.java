package de.uniba.dsg.jaxrs.db;

import de.uniba.dsg.jaxrs.models.logic.Cat;
import de.uniba.dsg.jaxrs.models.logic.Movie;
import de.uniba.dsg.jaxrs.models.logic.Popularity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CatsDB {

    private final MovieDB movies;
    private final List<Cat> cats;

    public CatsDB(final MovieDB movies) {
        this.movies = movies;
        this.cats = this.initCats();
    }

    /**
     * Initialize cats and relate movies (mimic a database)
     *
     * @return
     */
    private List<Cat> initCats() {

        return new ArrayList<>(Arrays.asList(
                new Cat(1, Popularity.A, "Garfield", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-garfield-300x179.jpg", Arrays.asList(this.movies.getMovieByName("Garfield"))),
                new Cat(2, Popularity.C, "Hello Kitty", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-hellokitty-300x261.jpg", new ArrayList<>()),
                new Cat(3, Popularity.B, "Sylvester", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-sylvester-1-200x300.jpg", Arrays.asList(this.movies.getMovieByName("Looney Toons"))),
                new Cat(4, Popularity.B, "Tigger", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-tigger-300x238.jpg", Arrays.asList(this.movies.getMovieByName("Winnie the Pooh"))),
                new Cat(5, Popularity.A, "Simba", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-simba-300x211.jpg", Arrays.asList(this.movies.getMovieByName("The Lion King"), this.movies.getMovieByName("The Lion King II: Simba's Pride"))),
                new Cat(6, Popularity.A, "Nala", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-nala-2.jpg", Arrays.asList(this.movies.getMovieByName("The Lion King"))),
                new Cat(7, Popularity.C, "Nermal", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-nermal-300x286.jpg", Arrays.asList(this.movies.getMovieByName("Garfield"))),
                new Cat(8, Popularity.B, "Figaro", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-figaro-300x233.jpg", Arrays.asList(this.movies.getMovieByName("Pinocchio"))),
                new Cat(9, Popularity.A, "Grumpy Cat", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-grumpy-cat-300x158.jpg", new ArrayList<>()),
                new Cat(10, Popularity.B, "Mr. Bigglesworth", "http://www.findcatnames.com/wp-content/uploads/2016/06/famous-cat-names-mrbigglesworth-300x198.jpg", Arrays.asList(this.movies.getMovieByName("Austin Powers")))
        ));
    }

    /**
     * Get all cats os a specific movie, identified by the movie name.
     *
     * @param movieName
     * @return
     */
    public List<Cat> getFamousCats(final String movieName) {
        final List<Cat> movieCats = new ArrayList<>();
        for (final Cat cat : this.cats) {
            for (final Movie movie : cat.getMovies()) {
                if (movie.getName().equalsIgnoreCase(movieName)) {
                    movieCats.add(cat);
                }
            }
        }
        return movieCats;
    }

    /**
     * Get a cat by id.
     *
     * @param id
     * @return
     */
    public Cat getFamousCat(final int id) {
        return this.cats.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public boolean deleteCat(final int id) {
        final Cat cat = this.getFamousCat(id);
        return this.cats.remove(cat);
    }

    public List<Cat> getAllFamousCats() {
        return this.cats;
    }

    public void add(final Cat newCat) {
        newCat.setId(this.cats.stream().map(Cat::getId).max(Comparator.naturalOrder()).orElse(0) + 1);
        this.cats.add(newCat);
    }
}
