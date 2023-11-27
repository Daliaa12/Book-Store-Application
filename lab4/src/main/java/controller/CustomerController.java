package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.User;
import model.validator.Notification;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import view.CustomerView;
import view.LoginView;

public class CustomerController {
    private final CustomerView customerView;
    public CustomerController(CustomerView customerView) {
        this.customerView=customerView;

        //this.customerView.buyBooksButtonListener(new )
        //this.customerView.addRegisterButtonListener(new LoginController.RegisterButtonListener());
    }


    //}
    /*private class ViewBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {


        }
    }
    private class SellBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {

        }
    }*/
}
