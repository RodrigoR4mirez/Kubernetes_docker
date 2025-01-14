package app.com.cineflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("movie")  // Define el nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id  // Define la clave primaria
    @Column("id")  // Mapea la columna 'id' de la tabla
    private Long id;

    @Column("title")  // Mapea la columna 'title'
    private String title;

    @Column("director")  // Mapea la columna 'director'
    private String director;

    @Column("genre")  // Mapea la columna 'genre'
    private String genre;

    @Column("release_date")  // Mapea la columna 'release_date'
    private LocalDateTime releaseDate;

    @Column("created_at")  // Mapea la columna 'created_at'
    private LocalDateTime createdAt;

    @Column("updated_at")  // Mapea la columna 'updated_at'
    private LocalDateTime updatedAt;
}