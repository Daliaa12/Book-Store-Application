package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.validator.Notification;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdminView;
import view.UserView;

public class AdminController {
    private final AdminView adminView;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    public AdminController(AdminView adminView,UserService userService,AuthenticationService authenticationService) {
        this.adminView = adminView;
        this.adminView.addCreateButtonListener(new AdminController.CreateButtonListener());
        this.adminView.addDeleteButtonListener(new AdminController.DeleteButtonListener());
        this.adminView.addReadButtonListener(new AdminController.ReadButtonListener());
        this.adminView.addUpdateButtonListener(new AdminController.UpdateButtonListener());
        this.userService=userService;
        this.authenticationService=authenticationService;
    }
    private class CreateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = adminView.getUsernameTextField();
            String password = adminView.getPasswordField();

            Notification<Boolean> registerNotification = authenticationService.registerEmployee(username, password);

            if (registerNotification.hasErrors()) {
                adminView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                adminView.setActionTargetText("Employee added successfully!");
            }
       }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String bookIdStr = adminView.getIdTextField();
            try {
                Long bookId = Long.parseLong(bookIdStr);

                boolean deleteResult = userService.deleteUser(bookId);
                if (deleteResult) {
                    adminView.setActionTargetText("User deleted successfully!");
                } else {
                    adminView.setActionTargetText("Failed to delete the user. Check id or try again.");
                }
            } catch (NumberFormatException e) {
                adminView.setActionTargetText("Invalid input. Please enter valid user ID.");
            }

        }
    }
    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String bookIdStr = adminView.getIdTextField();
            try {
                Long bookId = Long.parseLong(bookIdStr);

                String usernameStr = adminView.getUsernameTextField();
                String passwordString = adminView.getPasswordField();

                if (!usernameStr.isEmpty() && !passwordString.isEmpty()) {
                    adminView.setActionTargetText("Please enter either Username or Password, not both.");
                    return;
                }

                if (!usernameStr.isEmpty()) {
                    userService.updateUsername(bookId, usernameStr);
                    adminView.setActionTargetText("Employee username updated successfully!");
                } else if (!passwordString.isEmpty()) {
                    String hashedPassword = authenticationService.hashPassword(passwordString);
                    userService.updatePassword(bookId, hashedPassword);
                    adminView.setActionTargetText("Employee password updated successfully!");
                } else {
                    adminView.setActionTargetText("Please enter either Username or Password.");
                }
            } catch (NumberFormatException e) {
                adminView.setActionTargetText("Invalid input. Please enter valid employee ID.");
            }

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
