package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.BookView;
import view.CustomerView;


public class CustomerController {


    private final CustomerView customerView;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;

        this.customerView.addviewBooksButtonListener(new ViewBooksButtonListener());
        this.customerView.addbuyBooksButtonListener(new BuyBooksButtonListener());
    }
    private void openViewBookStage() {
        BookView bookView1=new BookView();
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

