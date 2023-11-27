package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerView {
    private Button viewBooksButton;
    private Button buyBookButton;
    public CustomerView(Stage primaryStage) {
        primaryStage.setTitle("Customer");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        initializeFields(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
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


    public void viewBooksButtonListener(EventHandler<ActionEvent> loginButtonListener) {
        viewBooksButton.setOnAction(loginButtonListener);
    }

    public void buyBooksButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        buyBookButton.setOnAction(signInButtonListener);
    }
}
