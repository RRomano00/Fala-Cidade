package br.com.faitec.fala_cidade.implementation.dao.postgres;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;

import java.sql.*;
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
        String sql = "INSERT INTO user_model(password, fullname, email, role) ";
        sql += " VALUES(?, ?, ?, ?) ";

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

        String sql = "DELETE FROM user_model ";
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
        final String sql = "SELECT * FROM user_model WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery(sql);
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
        return List.of();
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        String sql = "UPDATE user_model SET fullname = ? ";
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
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        String sql = "UPDATE user_model SET password = ? ";
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
