package pl.list.BooksAndMovies.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.list.BooksAndMovies.database.dao.AuthorDao;
import pl.list.BooksAndMovies.database.dao.BookDao;
import pl.list.BooksAndMovies.database.dao.CategoryDao;
import pl.list.BooksAndMovies.database.models.Author;
import pl.list.BooksAndMovies.database.models.Book;
import pl.list.BooksAndMovies.database.models.Category;
import pl.list.BooksAndMovies.utils.conventers.AuthorConverter;
import pl.list.BooksAndMovies.utils.conventers.BookConverter;
import pl.list.BooksAndMovies.utils.conventers.CategoryConverter;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.util.List;

public class BookModel {

    private ObjectProperty<BookFx> bookFxObject = new SimpleObjectProperty<>(new BookFx());
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        initAuthorList();
        initCategoryList();
    }

    private void initCategoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categoryList.forEach(c->{
            CategoryFx categoryFx = CategoryConverter.convertToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);

        });
    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(a->{
           AuthorFx authorFx = AuthorConverter.convertToAutorFx(a);
           authorFxObservableList.add(authorFx);

        });
    }

    public void saveToDatabase() throws ApplicationException {
        Book book = BookConverter.convertToBook(this.getBookFxObject());

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getBookFxObject().getCategoryFx().getId());
        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findById(Author.class, this.getBookFxObject().getAuthorFx().getId());

        book.setCategory(category);
        book.setAuthor(author);

        BookDao bookDao= new BookDao();
        bookDao.createOrUpdate(book);
    }

    public BookFx getBookFxObject() {
        return bookFxObject.get();
    }

    public ObjectProperty<BookFx> bookFxObjectProperty() {
        return bookFxObject;
    }

    public void setBookFxObject(BookFx bookFxObject) {
        this.bookFxObject.set(bookFxObject);
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }
}
