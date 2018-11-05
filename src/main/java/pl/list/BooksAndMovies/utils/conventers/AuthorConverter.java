package pl.list.BooksAndMovies.utils.conventers;

import pl.list.BooksAndMovies.database.models.Author;
import pl.list.BooksAndMovies.modelFx.AuthorFx;

public class AuthorConverter {

    public static Author converterToAuthor(AuthorFx authorFx){
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }

    public static AuthorFx convertToAutorFx(Author author){
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        authorFx.setSurname(author.getSurname());
        return authorFx;
    }
}
