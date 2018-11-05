package pl.list.BooksAndMovies.controllers;

import javafx.fxml.FXML;

public class TopMenuButtonsController {

    public static final String LIST_MOVIES_FXML = "/fxml/ListMovies.fxml";
    public static final String LIST_SERIES_FXML = "/fxml/ListSeries.fxml";
    public static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String ADD_BOOKS_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    public void openMoviesList() {
        mainController.setCenter(LIST_MOVIES_FXML);
    }
    @FXML
    public void openSeriesList() {
        mainController.setCenter(LIST_SERIES_FXML);
    }
    @FXML
    public void openBooksList() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }
    @FXML
    public void openAddCategory() {
        mainController.setCenter(ADD_CATEGORY_FXML);
    }
    @FXML
    public void openAddAuthor() {
        mainController.setCenter(ADD_AUTHOR_FXML);
    }
    @FXML
    public void addNew() {
        mainController.setCenter(ADD_BOOKS_FXML);
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
