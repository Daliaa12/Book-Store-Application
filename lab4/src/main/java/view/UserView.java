package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepositoryMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserView {
    private UserRepositoryMySQL userRepository;
    private TableView<User> table;

    public UserView() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
            userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
            TableColumn<User, Integer> idColumn = new TableColumn<>("id");
            idColumn.setMinWidth(200);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<User, String> usernameColumn = new TableColumn<>("username");
            usernameColumn.setMinWidth(200);
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

            TableColumn<User, String> passwordColumn = new TableColumn<>("password");
            passwordColumn.setMinWidth(100);
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));


            table = new TableView<>();
            table.setItems(getUsers());
            table.getColumns().addAll(idColumn, usernameColumn, passwordColumn);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(table);

            Stage stage = new Stage();
            stage.setTitle("View users");
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<User> getUsers() {
        List<User> bookList = userRepository.findAllEmployee();
        return FXCollections.observableArrayList(bookList);
    }
}
