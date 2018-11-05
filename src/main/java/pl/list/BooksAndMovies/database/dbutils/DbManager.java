package pl.list.BooksAndMovies.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.list.BooksAndMovies.database.models.Author;
import pl.list.BooksAndMovies.database.models.Book;
import pl.list.BooksAndMovies.database.models.Category;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    public static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./booksAndMoviesListDB";
//    public static final String USER = "admin";
//    public static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }
    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }
    public static void closeConnectionSource(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Author.class);
            TableUtils.createTableIfNotExists(connectionSource, Book.class);
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource,Author.class, true);
            TableUtils.dropTable(connectionSource,Book.class, true);
            TableUtils.dropTable(connectionSource,Category.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }
}
