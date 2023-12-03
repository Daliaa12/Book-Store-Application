package repository.user;
import model.Book;
import model.User;
import model.builder.BookBuilder;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.security.RightsRolesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;
import static java.util.Collections.singletonList;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user ;";
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                users.add(getUserFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public List <User> findAllEmployee(){
        String sql = "SELECT u.* FROM user u "+
                "INNER JOIN user_role ur ON u.id = ur.user_id "+
                "WHERE ur.role_id = 2";
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                users.add(getUserFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    // SQL Injection Attacks should not work after fixing functions
    // Be careful that the last character in sql injection payload is an empty space
    // alexandru.ghiurutan95@gmail.com' and 1=1; --
    // ' or username LIKE '%admin%'; --

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();

        try {
            String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username`=? AND `password`=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet userResultSet = preparedStatement.executeQuery();

                if (userResultSet.next()) {
                    User user = new UserBuilder()
                            .setUsername(userResultSet.getString("username"))
                            .setPassword(userResultSet.getString("password"))
                            .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                            .build();

                    findByUsernameAndPasswordNotification.setResult(user);
                } else {
                    findByUsernameAndPasswordNotification.addError("Invalid username or password!");
                    return findByUsernameAndPasswordNotification;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database!");
        }

        return findByUsernameAndPasswordNotification;
    }


    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Boolean deleteUser(Long id) {
        String sql = "DELETE FROM user WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("User not found with id: " + id);
                return Boolean.FALSE;
            } else {
                System.out.println("User deleted successfully with id: " + id);
                return Boolean.TRUE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByUsername(String email) {
        try {

            String fetchUserSql =
                    "Select * from `" + USER + "` where `username`=?";
            try (PreparedStatement preparedStatement=connection.prepareStatement(fetchUserSql))
            {
                preparedStatement.setString(1, email);
                ResultSet userResultSet = preparedStatement.executeQuery(fetchUserSql);
                return userResultSet.next();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updatePassword(Long userId, String password) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("User not found with id: " + userId);
            } else {
                System.out.println("Password updated successfully for user with id: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateUsername(Long userId, String username) {
        String sql = "UPDATE user SET username = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, userId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("User not found with id: " + username);
            } else {
                System.out.println("Username updated successfully for user with id: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getLong("id"))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .build();
    }



}