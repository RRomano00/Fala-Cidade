package br.com.faitec.fala_cidade.implementation.dao.postgres;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPostgresDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserPostgresDaoImpl.class.getName());

    private final Connection connection;

    public UserPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(UserModel entity) {
        String sql = "INSERT INTO users(password, fullname, email, role) ";
        sql += " VALUES(crypt(?, gen_salt('bf')), ?, ?, ?) ";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getFullname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getRole().name());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }

            connection.commit();
            resultSet.close();
            preparedStatement.close();

            return id;
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(int id) {
        logger.log(Level.INFO, "Preparando para remover usuário.");

        String sql = "DELETE FROM users ";
        sql += " WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            logger.log(Level.INFO, "Usuário removido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel readById(int id) {
        final String sql = "SELECT * FROM users WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String fullname = resultSet.getString("fullname");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");

                final String auxRole = resultSet.getString("role");
                final UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);

                final UserModel user = new UserModel();
                user.setId(entityId);
                user.setFullname(fullname);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);

                preparedStatement.close();
                resultSet.close();

                return  user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<UserModel> readall() {
        final List<UserModel> users = new ArrayList<>();

        final String sql = "SELECT * FROM users; ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String fullname = resultSet.getString("fullname");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");

                final String auxRole = resultSet.getString("role");
                final UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);

                final UserModel user = new UserModel();
                user.setId(entityId);
                user.setFullname(fullname);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);

                users.add(user);
            }
            preparedStatement.close();
            resultSet.close();

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        String sql = "UPDATE users SET fullname = ? ";
        sql += " WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getFullname());

            preparedStatement.setInt(2, entity.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel readByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ? ; ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String fullname = resultSet.getString("fullname");
                final String password = resultSet.getString("password");

                final String auxRole = resultSet.getString("role");
                final UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);

                final UserModel user = new UserModel();
                user.setId(entityId);
                user.setFullname(fullname);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);

                preparedStatement.close();
                resultSet.close();

                return  user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        String sql = "UPDATE users SET password = crypt(?, gen_salt('bf')) ";
        sql += " WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
