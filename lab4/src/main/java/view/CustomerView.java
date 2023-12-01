package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;

import java.time.LocalDate;
import java.util.List;

public class CustomerView {
    private Button viewBooksButton;
    private Button buyBookButton;
    private TableView<Book> bookTableView;
    public CustomerView(Stage primaryStage) {
        primaryStage.setTitle("Customer");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        initializeFields(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        //initializeBookTableView(gridPane);
        primaryStage.show();

    }
    public void initializeBookTableView(GridPane gridPane){
        bookTableView=new TableView<>();
        gridPane.add(bookTableView,0,1,2,1);
        TableColumn<Book, Integer> idColumn=new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, LocalDate> publishedDateColumn = new TableColumn<>("Published Date");
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTableView.getColumns().addAll(idColumn, titleColumn, authorColumn, publishedDateColumn, stockColumn, priceColumn);
    }

    public void setBookList(List<Book> books) {
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);
        bookTableView.setItems(bookList);
    }
    private void initializeFields(GridPane gridPane){

        viewBooksButton = new Button("View books");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(viewBooksButton);
        gridPane.add(signInButtonHBox, 0, 30);

        buyBookButton = new Button("Buy books");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BASELINE_CENTER);
        logInButtonHBox.getChildren().add(buyBookButton);
        gridPane.add(logInButtonHBox, 1, 30);

    }
    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

    }


    public void addviewBooksButtonListener(EventHandler<ActionEvent> viewBooksButtonListener) {
        viewBooksButton.setOnAction(viewBooksButtonListener);
    }

    public void addbuyBooksButtonListener(EventHandler<ActionEvent> buyBooksButtonListener) {
        buyBookButton.setOnAction(buyBooksButtonListener);
    }
}
