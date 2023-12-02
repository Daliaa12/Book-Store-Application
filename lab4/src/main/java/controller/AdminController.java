package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.User;
import service.book.BookService;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.BookView;
import view.EmployeeView;
import view.UserView;

import java.time.LocalDate;

public class AdminController {
    private final AdminView adminView;
    private final UserService userService;
    public AdminController(AdminView adminView,UserService userService) {
        this.adminView = adminView;
        this.adminView.addCreateButtonListener(new AdminController.CreateButtonListener());
        this.adminView.addDeleteButtonListener(new AdminController.DeleteButtonListener());
        this.adminView.addReadButtonListener(new AdminController.ReadButtonListener());
        this.adminView.addUpdateButtonListener(new AdminController.UpdateButtonListener());
        this.userService=userService;
    }
    private class CreateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String password = adminView.getPasswordField();
            String username = adminView.getUsernameTextField();

            User newUser = new User();
            newUser.setPassword(password);
            newUser.setUsername(username);

            boolean isUserSaved=userService.save(newUser);
            if (isUserSaved){
                adminView.setActionTargetText("User created successfully!");
            } else {
                adminView.setActionTargetText("Failed to create the user, check if it's not the same username or password!");
            }
       }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        }
    }
    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        }
    }
    private void openViewUserStage() {
        UserView userView = new UserView();
    }
    private class ReadButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            openViewUserStage();
        }
    }

}
