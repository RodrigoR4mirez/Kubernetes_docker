package app.com.cineflux.service;

import app.com.cineflux.model.Movie;
import app.com.cineflux.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Mono<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Mono<Movie> createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Mono<Void> deleteMovie(Long id) {
        return movieRepository.deleteById(id);
    }

    public Mono<Movie> updateMovie(Long id, Movie movie) {
        return movieRepository.findById(id)
                .flatMap(existingMovie -> {
                    existingMovie.setTitle(movie.getTitle());
                    existingMovie.setDirector(movie.getDirector());
                    existingMovie.setGenre(movie.getGenre());
                    return movieRepository.save(existingMovie);
                });
    }
}