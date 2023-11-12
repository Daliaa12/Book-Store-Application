package repository.book;



import model.EBook;
import model.builder.EBookBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EBookRepositoryMySQL implements EBookRepository {
    private final Connection connection;

    public EBookRepositoryMySQL(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<EBook> findAll() {
        String sql = "SELECT * FROM ebook;";
        List<EBook> ebooks = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                ebooks.add(getBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ebooks;
    }

    @Override
    public Optional<EBook> findById(Long id) {
        String sql = "SELECT * FROM audiobook WHERE id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                EBook ebook = getBookFromResultSet(resultSet);
                return Optional.of(ebook);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Optional.empty();
    }


    @Override
    public boolean save(EBook ebook) {
        String sql = "INSERT INTO ebook VALUES(null, ?, ?, ?);";

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ebook.getAuthor());
            preparedStatement.setString(2, ebook.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(ebook.getPublishedDate()));

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM ebook";
        try{
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    private EBook getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new EBookBuilder()
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setTitle(resultSet.getString("title"))
                .setPublishedDate(new java.sql.Date((resultSet.getDate("publishedDate")).getTime()).toLocalDate())
                .setFormat("format")
                .build();
    }
}