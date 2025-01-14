package app.com.cineflux.repository;


import app.com.cineflux.model.Movie;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends R2dbcRepository<Movie, Long> {

}