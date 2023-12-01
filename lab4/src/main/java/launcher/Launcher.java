package launcher;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import view.BookView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Launcher extends Application {
    private BookRepositoryMySQL bookRepository;


    public static void main(String[] args) {
        launch(args);

    }

    // Iterative Programming
    @Override
    public void start(Stage primaryStage) throws Exception {

        ComponentFactory componentFactory = ComponentFactory.getInstance(false, primaryStage);
    }

}
