package pl.list.BooksAndMovies.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.list.BooksAndMovies.modelFx.AuthorFx;
import pl.list.BooksAndMovies.modelFx.BookModel;
import pl.list.BooksAndMovies.modelFx.CategoryFx;
import pl.list.BooksAndMovies.utils.DialogsUtils;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

public class BookController {

    @FXML
    private ComboBox<AuthorFx> authorCombobox;
    @FXML
    private ComboBox<CategoryFx> categoryCombobox;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private CheckBox readCombobox;
    @FXML
    private Button addBookButton;

    private BookModel bookModel;

    @FXML
    public void initialize(){
        this.bookModel = new BookModel();
        try {
            this.bookModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }

        bindings();
        validation();
    }

    private void validation() {
        this.addBookButton.disableProperty().bind(this.categoryCombobox.valueProperty().isNull()
                .or(this.authorCombobox.valueProperty().isNull())
                .or(this.titleTextField.textProperty().isEmpty())
                .or(this.descriptionTextArea.textProperty().isEmpty())
                .or(this.releaseDatePicker.valueProperty().isNull()));
    }

    public void bindings() {
        this.categoryCombobox.setItems(this.bookModel.getCategoryFxObservableList());
        this.authorCombobox.setItems(this.bookModel.getAuthorFxObservableList());

        this.authorCombobox.valueProperty().bindBidirectional(this.bookModel.getBookFxObject().authorFxProperty());
        this.categoryCombobox.valueProperty().bindBidirectional(this.bookModel.getBookFxObject().categoryFxProperty());
        this.titleTextField.textProperty().bindBidirectional(this.bookModel.getBookFxObject().titleProperty());
        this.descriptionTextArea.textProperty().bindBidirectional(this.bookModel.getBookFxObject().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.bookModel.getBookFxObject().ratingProperty());
        this.releaseDatePicker.valueProperty().bindBidirectional(this.bookModel.getBookFxObject().releaseDateProperty());
        this.readCombobox.selectedProperty().bindBidirectional(this.bookModel.getBookFxObject().readProperty());

    }

    public void addBookAction( ) {
        try {
            this.bookModel.saveToDatabase();
            clearFields();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
    }

    private void clearFields() {
        this.authorCombobox.getSelectionModel().clearSelection();
        this.categoryCombobox.getSelectionModel().clearSelection();
        this.titleTextField.clear();
        this.descriptionTextArea.clear();
        this.ratingSlider.setValue(1.0);
        this.releaseDatePicker.getEditor().clear();
        this.readCombobox.selectedProperty().setValue(false);
    }

    public BookModel getBookModel() {
        return bookModel;
    }
}
