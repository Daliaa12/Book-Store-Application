package launcher;

import controller.CustomerController;
import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.book.BookRepositoryMySQL;
import view.BookView;

public class Launcher extends Application {
    private BookRepositoryMySQL bookRepository;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ComponentFactory componentFactory = ComponentFactory.getInstance(false, primaryStage);

        // Ensure CustomerController and BookView are created only once
        //CustomerController customerController = componentFactory.getCustomerController();
        //BookView bookView = componentFactory.getBookView();

        // Set BookView in CustomerController
       // customerController.setBookView(bookView);

        // If you have additional setup logic for CustomerController, you can call it here
        // customerController.setupAdditionalLogic();

       // LoginController loginController = componentFactory.getLoginController();
    }
}
