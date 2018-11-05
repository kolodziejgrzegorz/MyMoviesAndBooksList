package pl.list.BooksAndMovies.database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "BOOKS")
public class Book implements BaseModel {

    public static final String AUTHOR_ID = "AUTHOR_ID";
    public static final String CATEGORY_ID = "CATEGORY_ID";

    public Book() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = AUTHOR_ID, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Author author;

    @DatabaseField(columnName = CATEGORY_ID, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(columnName = "TITLE", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "DESCRIPTION", dataType = DataType.LONG_STRING)
    private String description;

    @DatabaseField(columnName = "RATING", width = 1)
    private Integer rating;

    @DatabaseField(columnName = "DATE_RELEASE", dataType = DataType.DATE_STRING, format = "yyyy-MM-DD")
    private Date releaseDate;

    @DatabaseField(columnName = "READ", defaultValue = "false")
    private boolean isRead;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", category=" + category +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", releaseDate=" + releaseDate +
                ", getRead=" + isRead +
                '}';
    }
}
