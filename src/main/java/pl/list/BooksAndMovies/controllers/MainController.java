package pl.list.BooksAndMovies.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.list.BooksAndMovies.utils.DialogsUtils;
import pl.list.BooksAndMovies.utils.FxmlUtils;

public class MainController {

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private BorderPane borderPaneMain;

    public void initialize(){
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlPath){
        borderPaneMain.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }

    @FXML
    public void handleCaspianStyle( ) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    @FXML
    public void handleMadenaStyle( ) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void handleAlwaysOnTop(ActionEvent event ) {
        Stage stage = (Stage) borderPaneMain.getScene().getWindow();
        boolean isSelected = ((CheckMenuItem) event.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(isSelected);
    }

    @FXML
    public void handleAbout( ) {
       DialogsUtils.dialogAboutApp();
    }
}
