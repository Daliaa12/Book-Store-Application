package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import service.book.BookService;
import view.BookView;
import view.CustomerView;
import view.EmployeeView;

import java.time.LocalDate;

public class EmployeeController {
private final EmployeeView employeeView;
private final BookService bookService;

    public EmployeeController(EmployeeView employeeView,BookService bookService) {
        this.employeeView = employeeView;
        this.bookService=bookService;
        this.employeeView.addCreateButtonListener(new EmployeeController.CreateButtonListener());
        this.employeeView.addDeleteButtonListener(new EmployeeController.DeleteButtonListener());
        this.employeeView.addReadButtonListener(new EmployeeController.ReadButtonListener());
        this.employeeView.addUpdateButtonListener(new EmployeeController.UpdateButtonListener());

    }
    private class CreateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String author = employeeView.getAuthorTextField();
            String title = employeeView.getTitleTextField();
            LocalDate publishedDate = LocalDate.parse(employeeView.getPublishedDateTextField());
            int stock = Integer.parseInt(employeeView.getStockTextField());
            double price = Double.parseDouble(employeeView.getPriceTextField());

            Book newBook = new Book();
            newBook.setAuthor(author);
            newBook.setTitle(title);
            newBook.setPublishedDate(publishedDate);
            newBook.setPrice(price);
            newBook.setStock(stock);

            boolean isBookSaved=bookService.save(newBook);
            if (isBookSaved){
                employeeView.setActionTargetText("Book created successfully!");
            } else {
                employeeView.setActionTargetText("Failed to create the book, check if doesn't already exist!");
            }
        }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String bookIdStr = employeeView.getIdTextField();
            try {
                Long bookId = Long.parseLong(bookIdStr);

                boolean deleteResult = bookService.deleteBook(bookId);
                if (deleteResult) {
                    employeeView.setActionTargetText("Book deleted successfully!");
                } else {
                    employeeView.setActionTargetText("Failed to delete the book. Check id or try again.");
                }
            } catch (NumberFormatException e) {
                employeeView.setActionTargetText("Invalid input. Please enter valid book ID.");
            }

        }
    }
    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String bookIdStr = employeeView.getIdTextField();
            try {
                Long bookId = Long.parseLong(bookIdStr);

                String priceStr = employeeView.getPriceTextField();
                String stockStr = employeeView.getStockTextField();

                if (!priceStr.isEmpty() && !stockStr.isEmpty()) {
                    employeeView.setActionTargetText("Please enter either Price or Stock, not both.");
                    return;
                }

                if (!priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    bookService.updatePrice(bookId, price);
                    employeeView.setActionTargetText("Book price updated successfully!");
                } else if (!stockStr.isEmpty()) {
                    int stock = Integer.parseInt(stockStr);
                    bookService.updateStock(bookId, stock);
                    employeeView.setActionTargetText("Book stock updated successfully!");
                } else {
                    employeeView.setActionTargetText("Please enter either Price or Stock.");
                }
            } catch (NumberFormatException e) {
                employeeView.setActionTargetText("Invalid input. Please enter valid book ID.");
            }

        }
    }
    private void openViewBookStage() {
        BookView bookView1 = new BookView();
    }
    private class ReadButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            openViewBookStage();
        }
    }

}
