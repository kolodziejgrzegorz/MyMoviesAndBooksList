package pl.list.BooksAndMovies;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.list.BooksAndMovies.database.dbutils.DbManager;
import pl.list.BooksAndMovies.utils.FillDatabase;
import pl.list.BooksAndMovies.utils.FxmlUtils;

import java.util.Locale;

public class Main extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("pl"));
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();


        DbManager.initDatabase();

        FillDatabase.fillDataBase();

    }
}
