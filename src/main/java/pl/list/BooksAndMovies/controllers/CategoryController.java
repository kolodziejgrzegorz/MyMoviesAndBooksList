package pl.list.BooksAndMovies.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import pl.list.BooksAndMovies.modelFx.CategoryFx;
import pl.list.BooksAndMovies.modelFx.CategoryModel;
import pl.list.BooksAndMovies.utils.DialogsUtils;
import pl.list.BooksAndMovies.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class CategoryController {

    @FXML
    private TextField addCategoryTextField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private Button editCategoryButton;

    @FXML
    private TreeView<String> categoryTreeView;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    private CategoryModel categoryModel;

    public void initialize(){
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        this.categoryTreeView.setRoot(this.categoryModel.getRoot());
        initBindings();
    }

    private void initBindings() {
        addCategoryButton.disableProperty().bind(addCategoryTextField.textProperty().isEmpty());
        deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    @FXML
    public void addCategoryAction() {
        try {
            categoryModel.saveCategoryInDatabase(addCategoryTextField.getText());
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
        addCategoryTextField.clear();
    }
    @FXML
    public void comboboxOnAction() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void deleteCategoryAction() {
        try {
            categoryModel.deleteCategoryById();
        } catch (ApplicationException | SQLException e) {
            DialogsUtils.errorAlert(e.getMessage());
        }
    }
    @FXML
    public void editCategoryAction() {
        String newCategoryName = DialogsUtils.editDialog(this.categoryModel.getCategory().getName());
        if(newCategoryName !=null){
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategoryInDatabase();
            } catch (ApplicationException e) {
                DialogsUtils.errorAlert(e.getMessage());
            }
        }
    }
}
