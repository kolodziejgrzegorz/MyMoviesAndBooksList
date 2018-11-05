package pl.list.BooksAndMovies.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.list.BooksAndMovies.modelFx.AuthorFx;
import pl.list.BooksAndMovies.modelFx.AuthorModel;
import pl.list.BooksAndMovies.utils.DialogsUtils;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.sql.SQLException;


public class AuthorController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Button addAuthorButton;
    @FXML
    private TableView<AuthorFx> authorTableView;
    @FXML
    private TableColumn<AuthorFx, String> nameColumn;
    @FXML
    private TableColumn<AuthorFx, String> surnameColumn;
    @FXML
    private MenuItem deleteMenuItem;

    private AuthorModel authorModel;

    public void initialize(){
        this.authorModel = new AuthorModel();
        try {
            this.authorModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
        bindings();

        bindingsTableView();
    }

    private void bindingsTableView() {
        authorTableView.setItems(this.authorModel.getAuthorFxObservableList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            this.authorModel.setAuthorFxObjectEdit(newValue);
        });
    }

    private void bindings() {
        this.authorModel.authorFxObjectProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.authorModel.authorFxObjectProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addAuthorButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty()
                                                .or(this.surnameTextField.textProperty().isEmpty()));
        this.deleteMenuItem.disableProperty().bind(this.authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    public void addAuthorAction( ) {
        try {
            this.authorModel.saveAuthorInDatabase();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }

    public void onEditCommitName(TableColumn.CellEditEvent<AuthorFx,String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectEdit().setName(authorFxStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    public void onEditCommitSurname(TableColumn.CellEditEvent<AuthorFx,String> authorFxStringCellEditEvent) {
        this.authorModel.getAuthorFxObjectEdit().setSurname(authorFxStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    private void updateInDatabase() {
        try {
            this.authorModel.saveAuthorEditInDatabase();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
    }

    public void deleteMenuItem( ) {
        try {
            this.authorModel.deleteAuthorInDatabase();
        } catch (ApplicationException | SQLException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
    }
}
