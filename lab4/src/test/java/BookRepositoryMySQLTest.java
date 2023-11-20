import model.Book;
import org.junit.jupiter.api.*;
import repository.book.BookRepositoryMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryMySQLTest {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String TEST_SCHEMA = "test_library";
    private static final String USER = "root";
    private static final String PASSWORD = "Caramidacupatratele1";


    private static Connection connection;
    private BookRepositoryMySQL bookRepository;

    @BeforeAll
    static void setUp() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL+TEST_SCHEMA, USER, PASSWORD);


        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @BeforeEach
    void init() {
        bookRepository = new BookRepositoryMySQL(connection);
    }

    @AfterEach
    void tearDown() {
        bookRepository.removeAll();
    }

    @AfterAll
    static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setPublishedDate(LocalDate.now());
        assertTrue(bookRepository.save(book));
    }

    @Test
    void testFindAll() {
        Book book1 = new Book();
        book1.setAuthor("Author1");
        book1.setTitle("Title1");
        book1.setPublishedDate(LocalDate.now());
        Book book2 = new Book();
        book2.setAuthor("Author2");
        book2.setTitle("Title2");
        book2.setPublishedDate(LocalDate.now());
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    void testRemoveAll() {
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setPublishedDate(LocalDate.now());
        bookRepository.save(book);
        bookRepository.removeAll();
        List<Book> books = bookRepository.findAll();
        assertTrue(books.isEmpty());
    }
}
