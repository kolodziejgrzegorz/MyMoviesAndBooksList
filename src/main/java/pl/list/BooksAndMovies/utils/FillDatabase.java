package pl.list.BooksAndMovies.utils;

import pl.list.BooksAndMovies.database.dao.AuthorDao;
import pl.list.BooksAndMovies.database.dao.BookDao;
import pl.list.BooksAndMovies.database.dao.CategoryDao;
import pl.list.BooksAndMovies.database.dbutils.DbManager;
import pl.list.BooksAndMovies.database.models.Author;
import pl.list.BooksAndMovies.database.models.Book;
import pl.list.BooksAndMovies.database.models.Category;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.util.Date;

public class FillDatabase {

    public FillDatabase() {
    }

    public static void fillDataBase(){
        Category category1 = new Category();
        Author author1 = new Author();
        Book book1 = new Book();
        BookDao bookDao = new BookDao();
        CategoryDao categoryDao = new CategoryDao();
        AuthorDao  authorDao = new AuthorDao();

        category1.setName("Fantastyka");
        author1.setName("John Ronald Reuel");
        author1.setSurname("Tolkien");
        book1.setAuthor(author1);
        book1.setCategory(category1);
        book1.setTitle("Władca pierścieni");
        book1.setReleaseDate(new Date());
        book1.setDescription("Jakiś opis1");
        book1.setRead(true);
        book1.setRating(5);
        try {
            authorDao.createOrUpdate(author1);
            categoryDao.createOrUpdate(category1);
            bookDao.createOrUpdate(book1);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();

        Author author2 = new Author();
        Book book2 = new Book();
//        category2.setName("Fantastyka");
        author2.setName("Peter");
        author2.setSurname("Brett");
        book2.setAuthor(author2);
        book2.setCategory(category1);
        book2.setTitle("Otchłań");
        book2.setReleaseDate(new Date());
        book2.setDescription("Jakiś opis2");
        book2.setRead(true);
        book2.setRating(5);
        try {
            authorDao.createOrUpdate(author2);
            categoryDao.createOrUpdate(category1);
            bookDao.createOrUpdate(book2);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();

        Category category3 = new Category();
        Author author3 = new Author();
        Book book3 = new Book();
        category3.setName("Sensacja");
        author3.setName("John");
        author3.setSurname("Grisham");
        book3.setAuthor(author3);
        book3.setCategory(category3);
        book3.setTitle("Apelacja");
        book3.setReleaseDate(new Date());
        book3.setDescription("Jakiś opis3");
        book3.setRead(true);
        book3.setRating(5);
        try {
            authorDao.createOrUpdate(author3);
            categoryDao.createOrUpdate(category3);
            bookDao.createOrUpdate(book3);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();

        Category category = new Category();
        Author author = new Author();
        Book book = new Book();
        category.setName("Dramat");
        author.setName("William");
        author.setSurname("Shakespeare");
        book.setAuthor(author);
        book.setCategory(category);
        book.setTitle("Hamlet");
        book.setReleaseDate(new Date());
        book.setDescription("Jakiś opis4");
        book.setRead(true);
        book.setRating(5);
        try {
            authorDao.createOrUpdate(author);
            categoryDao.createOrUpdate(category);
            bookDao.createOrUpdate(book);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }
}
