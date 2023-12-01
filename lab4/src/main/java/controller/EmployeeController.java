package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import service.book.BookService;
import view.BookView;
import view.CustomerView;
import view.EmployeeView;

public class EmployeeController {
private final EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        this.employeeView.addCreateButtonListener(new EmployeeController.CreateButtonListener());
        this.employeeView.addDeleteButtonListener(new EmployeeController.DeleteButtonListener());
        this.employeeView.addReadButtonListener(new EmployeeController.ReadButtonListener());
        this.employeeView.addUpdateButtonListener(new EmployeeController.UpdateButtonListener());
    }
    private class CreateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //openViewBookStage();
        }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //openViewBookStage();
        }
    }
    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //openViewBookStage();
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
