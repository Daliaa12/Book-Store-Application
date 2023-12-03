package controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Role;
import model.User;
import model.validator.Notification;
import service.book.BookService;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdminView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.List;

import static database.Constants.Roles.*;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final BookService bookService;
    private final UserService userService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService,BookService bookService,UserService userService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.bookService = bookService;
        this.userService=userService;
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                User user = loginNotification.getResult();
                List<Role> roles = user.getRoles();
                String role = roles.get(0).getRole();
                System.out.println(role);
                switch (role){
                    case ADMINISTRATOR: {
                        AdminView adminView=new AdminView(loginView.getStage());
                        AdminController adminController=new AdminController(adminView,userService,authenticationService);
                        break;
                    }

                    case EMPLOYEE: {
                        EmployeeView employeeView=new EmployeeView(loginView.getStage());
                        EmployeeController employeeController=new EmployeeController(employeeView,bookService);
                        break;
                    }
                    case CUSTOMER: {
                        CustomerView customerView = new CustomerView(loginView.getStage());
                        CustomerController customerController = new CustomerController(customerView, bookService);
                        break;
                    }
                }
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}