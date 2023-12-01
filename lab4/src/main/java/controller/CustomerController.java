package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.User;
import model.validator.Notification;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import view.BookView;
import view.CustomerView;
import view.LoginView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CustomerController {


    private final CustomerView customerView;
    private final BookView bookView;

    public CustomerController(CustomerView customerView, BookView bookView) {
        this.customerView = customerView;
        this.bookView = bookView;

        this.customerView.addviewBooksButtonListener(new ViewBooksButtonListener());
        this.customerView.addbuyBooksButtonListener(new BuyBooksButtonListener());
    }
    private void openViewBookStage() {
        bookView.showStage();
    }

    private class ViewBooksButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            openViewBookStage();
        }


    }

    private class BuyBooksButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        }
    }
}

