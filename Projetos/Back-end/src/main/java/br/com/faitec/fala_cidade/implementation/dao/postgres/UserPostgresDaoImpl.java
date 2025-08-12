package br.com.faitec.fala_cidade.implementation.dao.postgres;

import br.com.faitec.fala_cidade.domain.UserModel;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;

import java.sql.*;
import java.util.List;

public class UserPostgresDaoImpl implements UserDao {

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

    }

    @Override
    public UserModel readById(int id) {
        return null;
    }

    @Override
    public List<UserModel> readall() {
        return List.of();
    }

    @Override
    public void updateInformation(int id, UserModel entity) {

    }

    @Override
    public UserModel readByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        return false;
    }
}
