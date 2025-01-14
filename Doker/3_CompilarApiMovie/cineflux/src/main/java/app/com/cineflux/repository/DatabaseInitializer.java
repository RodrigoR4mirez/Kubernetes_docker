package app.com.cineflux.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * En proyectos que utilizan Spring Boot con R2DBC para bases de datos reactivas, como MySQL,
 * las tablas no se crean automáticamente a partir de las entidades definidas en el código.
 * A diferencia de JPA con Hibernate, donde se puede configurar la propiedad
 * <code>spring.jpa.hibernate.ddl-auto</code> para generar las tablas automáticamente,
 * R2DBC no ofrece esta funcionalidad de forma nativa.
 * </p>
 *
 * <p>
 * Por lo tanto, es necesario crear las tablas manualmente antes de ejecutar la aplicación.
 * Una forma común de hacerlo es mediante scripts SQL de inicialización que se ejecutan
 * al iniciar la aplicación. Para ello, puedes utilizar {@code DatabaseInitializer} para ejecutar un script SQL que cree
 * las tablas y registros necesarios.
 * </p>
 */
@Component
public class DatabaseInitializer {

    @Autowired
    private DatabaseClient databaseClient;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Script to create the 'movie' table
        String createTableScript = "-- Crear la tabla movies\n" +
                "CREATE TABLE IF NOT EXISTS `movie` (\n" +
                "    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Campo id que es clave primaria y autoincrementable\n" +
                "    `title` VARCHAR(255) NOT NULL,           -- Campo para el título de la película\n" +
                "    `director` VARCHAR(255) NOT NULL,        -- Campo para el director de la película\n" +
                "    `genre` VARCHAR(100),                    -- Campo para el género de la película (opcional)\n" +
                "    `release_date` DATETIME,                 -- Campo para la fecha de lanzamiento\n" +
                "    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,  -- Fecha de creación (por defecto la fecha actual)\n" +
                "    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- Fecha de actualización automática\n" +
                ");";

        // Execute the table creation script
        databaseClient.sql(createTableScript)
                .then()
                .block();

        // Script to insert three records into the 'movie' table
        String insertDataScript = "-- Insertar tres registros en la tabla movies\n" +
                "INSERT INTO `movie` (`title`, `director`, `genre`, `release_date`)\n" +
                "VALUES \n" +
                "    ('Inception', 'Christopher Nolan', 'Sci-Fi', '2010-07-16 00:00:00'),\n" +
                "    ('The Matrix', 'Lana Wachowski, Lilly Wachowski', 'Action', '1999-03-31 00:00:00'),\n" +
                "    ('The Shawshank Redemption', 'Frank Darabont', 'Drama', '1994-09-22 00:00:00');";

        // Execute the insert data script
        databaseClient.sql(insertDataScript)
                .then()
                .block();
    }

}
