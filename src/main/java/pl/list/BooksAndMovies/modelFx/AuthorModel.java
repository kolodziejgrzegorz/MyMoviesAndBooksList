package pl.list.BooksAndMovies.modelFx;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.list.BooksAndMovies.database.dao.AuthorDao;
import pl.list.BooksAndMovies.database.dao.BookDao;
import pl.list.BooksAndMovies.database.models.Author;
import pl.list.BooksAndMovies.database.models.Book;
import pl.list.BooksAndMovies.utils.conventers.AuthorConverter;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObject = new SimpleObjectProperty<>(new AuthorFx());
    private ObjectProperty<AuthorFx> authorFxObjectEdit = new SimpleObjectProperty<>(new AuthorFx());

    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();

    public void saveAuthorInDatabase() throws ApplicationException {
        saveOrUpdate(this.authorFxObject.get());
    }

    public void saveAuthorEditInDatabase() throws ApplicationException {
        saveOrUpdate(this.authorFxObjectEdit.get());
    }

    private void saveOrUpdate(AuthorFx authorFx) throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        Author author = AuthorConverter.converterToAuthor(authorFx);
        authorDao.createOrUpdate(author);
        this.init();
    }

    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = AuthorConverter.convertToAutorFx(author);
            this.authorFxObservableList.add(authorFx);
        });
    }

    public void deleteAuthorInDatabase() throws ApplicationException, SQLException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.deleteById(Author.class, getAuthorFxObjectEdit().getId());
        BookDao bookDao = new BookDao();
        bookDao.deleteByColumnName(Book.AUTHOR_ID, getAuthorFxObjectEdit().getId());
        this.init();

    }
    public AuthorFx getAuthorFxObject() {
        return authorFxObject.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectProperty() {
        return authorFxObject;
    }

    public void setAuthorFxObject(AuthorFx authorFxObject) {
        this.authorFxObject.set(authorFxObject);
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public AuthorFx getAuthorFxObjectEdit() {
        return authorFxObjectEdit.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectEditProperty() {
        return authorFxObjectEdit;
    }

    public void setAuthorFxObjectEdit(AuthorFx authorFxObjectEdit) {
        this.authorFxObjectEdit.set(authorFxObjectEdit);
    }
}
