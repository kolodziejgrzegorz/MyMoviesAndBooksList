package pl.list.BooksAndMovies.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.list.BooksAndMovies.modelFx.AuthorFx;
import pl.list.BooksAndMovies.modelFx.BookFx;
import pl.list.BooksAndMovies.modelFx.CategoryFx;
import pl.list.BooksAndMovies.modelFx.ListBooksModel;
import pl.list.BooksAndMovies.utils.DialogsUtils;
import pl.list.BooksAndMovies.utils.FxmlUtils;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;

public class ListBooksController {

    @FXML
    private TableView<BookFx> bookTableView;
    @FXML
    private TableColumn<BookFx, String> titleColumn;
    @FXML
    private TableColumn<BookFx, String> descriptionColumn;
    @FXML
    private TableColumn<BookFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<BookFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn <BookFx, Number>ratingColumn;
    @FXML
    private TableColumn<BookFx, Boolean> readColumn;
    @FXML
    private TableColumn<BookFx, LocalDate> releaseDateColumn;
    @FXML
    private TableColumn<BookFx, BookFx> deleteColumn;
    @FXML
    private TableColumn<BookFx, BookFx> editColumn;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;

    private ListBooksModel listBooksModel;

    public void initialize(){
        this.listBooksModel = new ListBooksModel();
        try {
            this.listBooksModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
        this.categoryComboBox.setItems(this.listBooksModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.listBooksModel.getAuthorFxObservableList());

        this.listBooksModel.categoryFxObjectProperty().bind(this.categoryComboBox.valueProperty());
        this.listBooksModel.authorFxObjectProperty().bind(this.authorComboBox.valueProperty());

        this.bookTableView.setItems(this.listBooksModel.getBookFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.readColumn.setCellValueFactory(cellData -> cellData.getValue().readProperty());
        this.releaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.deleteColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>(){
            Button button = createButton("/icons/delete.png");
            @Override
            protected void updateItem(BookFx item, boolean empty){
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                if(!empty){
                    setGraphic(button);
                    setAlignment(Pos.CENTER);
                    button.setOnAction(event -> {
                        try {
                            listBooksModel.deleteBook(item);
                        } catch (ApplicationException e) {
                            DialogsUtils.errorAlert(e.getMessage());
                        }
                    });
                }
            }
        });

        this.editColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>(){
            Button button = createButton("/icons/edit.png");
            @Override
            protected void updateItem(BookFx item, boolean empty){
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                if(!empty){
                    setGraphic(button);
                    setAlignment(Pos.CENTER);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddBook.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BookController controller= loader.getController();
                        controller.getBookModel().setBookFxObject(item);
                        controller.bindings();
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    });
                }
            }
        });
    }

    private Button createButton(String path){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource(path).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    public void filterOnActionComboBox() {
        this.listBooksModel.filteredBookList();
    }

    public void clearCategoryComboBox() {
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    public void clearAuthorComboBox() {
        this.authorComboBox.getSelectionModel().clearSelection();
    }
}
