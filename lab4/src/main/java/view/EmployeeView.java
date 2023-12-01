package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeeView {
    private Button createButton;
    private Button updateButton;
    private Button readButton;
    private Button deleteButton;
    private TextField idTextField;
    private TextField authorTextField;
    private TextField titleTextField;
    private TextField publishedDateTextField;
    private TextField stockTextField;
    private TextField priceTextField;
    private Text actiontarget;

    public EmployeeView(Stage primaryStage) {
        primaryStage.setTitle("Employee");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        initializeFields(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeFields(GridPane gridPane) {
        Label idLabel = new Label("ID:");
        Label authorLabel = new Label("Author:");
        Label titleLabel = new Label("Title:");
        Label publishedDateLabel = new Label("Published Date:");
        Label stockLabel = new Label("Stock:");
        Label priceLabel = new Label("Price:");

        idTextField = new TextField();
        authorTextField = new TextField();
        titleTextField = new TextField();
        publishedDateTextField = new TextField();
        stockTextField = new TextField();
        priceTextField = new TextField();

        createButton = new Button("Create");
        updateButton = new Button("Update");
        readButton = new Button("Read");
        deleteButton = new Button("Delete");

        createButton.setStyle("-fx-base: #2ecc71;");
        updateButton.setStyle("-fx-base: #3498db;");
        readButton.setStyle("-fx-base: #f39c12;");
        deleteButton.setStyle("-fx-base: #e74c3c;");


        createButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        updateButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        readButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        actiontarget = new Text();

        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(createButton, updateButton, readButton, deleteButton);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(authorLabel, 0, 1);
        gridPane.add(titleLabel, 0, 2);
        gridPane.add(publishedDateLabel, 0, 3);
        gridPane.add(stockLabel, 0, 4);
        gridPane.add(priceLabel, 0, 5);

        gridPane.add(idTextField, 1, 0);
        gridPane.add(authorTextField, 1, 1);
        gridPane.add(titleTextField, 1, 2);
        gridPane.add(publishedDateTextField, 1, 3);
        gridPane.add(stockTextField, 1, 4);
        gridPane.add(priceTextField, 1, 5);

        gridPane.add(buttonsHBox, 0, 7, 4, 1);

        gridPane.add(actiontarget, 0, 8, 4, 1);
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public void addReadButtonListener(EventHandler<ActionEvent> readButtonListener) {
        readButton.setOnAction(readButtonListener);
    }

    public void addUpdateButtonListener(EventHandler<ActionEvent> updateButtonListener) {
        updateButton.setOnAction(updateButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addCreateButtonListener(EventHandler<ActionEvent> createButtonListener) {
        createButton.setOnAction(createButtonListener);
    }

    public void setActionTargetText(String text) {
        this.actiontarget.setText(text);
    }
    public String getIdTextField() {
        return idTextField.getText();
    }
    public String getAuthorTextField(){
        return authorTextField.getText();
    }
    public String getPublishedDateTextField(){return publishedDateTextField.getText();}
    public String getTitleTextField(){
        return titleTextField.getText();
    }
    public String getPriceTextField(){
        return priceTextField.getText();
    }
    public String getStockTextField() {return stockTextField.getText(); }
}
