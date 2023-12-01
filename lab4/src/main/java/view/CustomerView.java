package view;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.book.BookRepository;

import java.util.List;

public class CustomerView {
    private Button viewBooksButton;
    private Button buyBookButton;
    private TextField quantityTextField;
    private TextField idBookTextField;
    private BookRepository bookRepository;
    private Text actiontarget2;

    public CustomerView(Stage primaryStage) {
        primaryStage.setTitle("Customer");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        initializeFields(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initializeFields(GridPane gridPane) {
        Label welcome = new Label("Welcome, dear customer!");
        GridPane.setHalignment(welcome, HPos.CENTER);
        gridPane.add(welcome, 0, 0, 4, 1);
        actiontarget2 = new Text();
        gridPane.add(actiontarget2, 0, 4, 4, 1);
        Label message = new Label("If you want to buy a book, please enter the quantity and the id:");
        gridPane.add(message, 0, 1, 4, 1);

        Label quantityLabel = new Label("Quantity:");
        Label idLabel = new Label("Book ID:");

        quantityTextField = new TextField();
        gridPane.add(quantityLabel, 0, 2);
        gridPane.add(quantityTextField, 1, 2);

        idBookTextField = new TextField();
        gridPane.add(idLabel, 2, 2);
        gridPane.add(idBookTextField, 3, 2);

        viewBooksButton = new Button("View Books");
        GridPane.setHalignment(viewBooksButton, HPos.CENTER);
        gridPane.add(viewBooksButton, 0, 3, 2, 1);

        buyBookButton = new Button("Buy Books");
        GridPane.setHalignment(buyBookButton, HPos.CENTER);
        gridPane.add(buyBookButton, 2, 3, 2, 1);
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
    public void setActionTargetText(String text){ this.actiontarget2.setText(text);}
    public String getQuantityForBuy() {
        return quantityTextField.getText();
    }
    public String getIdBookForBuy(){
        return idBookTextField.getText();
    }

}

