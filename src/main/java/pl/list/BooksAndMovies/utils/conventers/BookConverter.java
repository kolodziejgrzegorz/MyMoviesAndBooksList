package pl.list.BooksAndMovies.utils.conventers;

import pl.list.BooksAndMovies.database.models.Book;
import pl.list.BooksAndMovies.modelFx.BookFx;
import pl.list.BooksAndMovies.utils.Utils;

public class BookConverter {
    public static Book convertToBook(BookFx bookFx){
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setRead(bookFx.getRead());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getRating());
        book.setReleaseDate(Utils.convertToDate(bookFx.getReleaseDate()));
        return book;
    }

    public static BookFx convertToBookFx(Book book){
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setRead(book.getRead());
        bookFx.setDescription(book.getDescription());
        bookFx.setRating(book.getRating());
        bookFx.setReleaseDate(Utils.convertToLocalDate(book.getReleaseDate()));
        bookFx.setAuthorFx(AuthorConverter.convertToAutorFx(book.getAuthor()));
        bookFx.setCategoryFx(CategoryConverter.convertToCategoryFx(book.getCategory()));
        return bookFx;
    }
}
