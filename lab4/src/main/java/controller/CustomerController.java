package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.validator.Notification;
import repository.book.BookRepository;
import service.book.BookService;
import view.BookView;
import view.CustomerView;


public class CustomerController {


    private final CustomerView customerView;
    private final BookService bookService;
    //private final BookRepository bookRepository;

    public CustomerController(CustomerView customerView,BookService bookService) {
        this.customerView = customerView;
        this.customerView.addviewBooksButtonListener(new ViewBooksButtonListener());
        this.customerView.addbuyBooksButtonListener(new BuyBooksButtonListener());
        this.bookService=bookService;
        //this.bookRepository=bookRepository;
    }


    private void openViewBookStage() {
        BookView bookView1 = new BookView();
    }

    private class ViewBooksButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            openViewBookStage();
        }


    }

    private class BuyBooksButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            String quantityStr = customerView.getQuantityForBuy();
            String bookIdStr = customerView.getIdBookForBuy();

            try {
                int quantity = Integer.parseInt(quantityStr);
                Long bookId = Long.parseLong(bookIdStr);

                boolean purchaseResult = bookService.buyBook(bookId, quantity);

                 if (purchaseResult) {
                    customerView.setActionTargetText("Book purchased successfully!");
                    //bookRepository.updateStock(bookId,quantity);

                } else {
                    customerView.setActionTargetText("Failed to purchase the book. Check stock or try again.");
                }
            } catch (NumberFormatException e) {
                customerView.setActionTargetText("Invalid input. Please enter valid quantity and book ID.");
            }
        }
    }
}


