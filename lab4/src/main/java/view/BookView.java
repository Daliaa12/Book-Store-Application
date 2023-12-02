package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepositoryMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookView {
    private BookRepositoryMySQL bookRepository;
    private TableView<Book> table;

    public BookView() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Caramidacupatratele1");
            bookRepository = new BookRepositoryMySQL(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TableColumn<Book, Integer> idColumn = new TableColumn<>("id");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("author");
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("title");
        titleColumn.setMinWidth(100);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, LocalDate> publishedDateColumn = new TableColumn<>("publishedDate");
        publishedDateColumn.setMinWidth(100);
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("stock");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Book, Double> priceColumn = new TableColumn<>("price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table = new TableView<>();
        table.setItems(getBooks());
        table.getColumns().addAll(idColumn, authorColumn, titleColumn, publishedDateColumn, stockColumn, priceColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Stage stage = new Stage();
        stage.setTitle("View books");
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Book> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        return FXCollections.observableArrayList(bookList);
    }
}
