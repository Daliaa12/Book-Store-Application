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

public class AdminView {
    private Button createButton;
    private Button updateButton;
    private Button readButton;
    private Button deleteButton;
    private TextField idTextField;
    private TextField usernameTextField;
    private PasswordField passwordField;
    private Text actiontarget;

    public AdminView(Stage primaryStage) {
        primaryStage.setTitle("Admin");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        initializeFields(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeFields(GridPane gridPane) {

        Text deleteInfoLabel = new Text("If you want to delete a user, enter only the ID.");
        Text createInfoLabel = new Text("If you want to create a user, please enter Username and password.");
        Text updateInfoLabel = new Text("If you want to update a user, enter the ID of the user you want to update and the Username or Password.");

        deleteInfoLabel.setStyle("-fx-font-size: 12; -fx-fill: #e74c3c;");
        createInfoLabel.setStyle("-fx-font-size: 12; -fx-fill: #2ecc71;");
        updateInfoLabel.setStyle("-fx-font-size: 12; -fx-fill: #3498db;");

        gridPane.add(deleteInfoLabel, 0, 6, 2, 1);
        gridPane.add(createInfoLabel, 0, 9, 2, 1);
        gridPane.add(updateInfoLabel, 0, 10, 2, 1);

        Label idLabel = new Label("ID:");
        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");


        idTextField = new TextField();
        usernameTextField = new TextField();
        passwordField = new PasswordField();


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
        gridPane.add(userNameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);


        gridPane.add(idTextField, 1, 0);
        gridPane.add(usernameTextField, 1, 1);
        gridPane.add(passwordField, 1, 2);

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
    public String getUsernameTextField(){
        return usernameTextField.getText();
    }
    public String getPasswordField(){return passwordField.getText();}
}
