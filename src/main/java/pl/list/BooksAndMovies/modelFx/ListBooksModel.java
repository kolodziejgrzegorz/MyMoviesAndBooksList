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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListBooksModel {
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFx> authorFxObject = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObject = new SimpleObjectProperty<>();

    private List<BookFx> bookFxList = new ArrayList<>();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.queryForAll(Book.class);
        bookFxList.clear();
        bookList.forEach(book->{
            this.bookFxList.add(BookConverter.convertToBookFx(book));
        });
        this.bookFxObservableList.setAll(bookFxList);
        initAutors();
        initCategories();
    }

    public void filteredBookList(){
        if(getAuthorFxObject() != null && getCategoryFxObject() != null){
            filterPredicate(predicateAuthor().and(predicateCategory()));
        }else if(getAuthorFxObject() != null){
            filterPredicate(predicateAuthor());
        }else if(getCategoryFxObject() != null){
            filterPredicate(predicateCategory());
        }else{
            this.bookFxObservableList.setAll(this.bookFxList);
        }
    }
    private void initCategories() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categoryList.forEach(c->{
            CategoryFx categoryFx = CategoryConverter.convertToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);

        });
    }

    private void initAutors() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(a->{
            AuthorFx authorFx = AuthorConverter.convertToAutorFx(a);
            authorFxObservableList.add(authorFx);

        });
    }

    private Predicate<BookFx> predicateCategory(){
        return bookFx -> bookFx.getCategoryFx().getId() == getCategoryFxObject().getId();
    }

    private Predicate<BookFx> predicateAuthor(){
        return bookFx -> bookFx.getAuthorFx().getId() == getAuthorFxObject().getId();
    }

    private void filterPredicate(Predicate<BookFx> predicate){
        List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());
        this.bookFxObservableList.setAll(newList);
    }

    public void deleteBook(BookFx bookFx) throws ApplicationException {
        BookDao bookDao = new BookDao();
        bookDao.deleteById(Book.class, bookFx.getId());
        init();
    }

    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
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

    public AuthorFx getAuthorFxObject() {
        return authorFxObject.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectProperty() {
        return authorFxObject;
    }

    public void setAuthorFxObject(AuthorFx authorFxObject) {
        this.authorFxObject.set(authorFxObject);
    }

    public CategoryFx getCategoryFxObject() {
        return categoryFxObject.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectProperty() {
        return categoryFxObject;
    }

    public void setCategoryFxObject(CategoryFx categoryFxObject) {
        this.categoryFxObject.set(categoryFxObject);
    }
}
