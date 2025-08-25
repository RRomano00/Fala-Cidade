package br.com.faitec.fala_cidade.implementation.dao.postgres;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.port.dao.report.ReportDao;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class ReportPostgresDaoImpl implements ReportDao {

    private static final Logger logger = Logger.getLogger(ReportPostgresDaoImpl.class.getName());

    private final Connection connection;

    public ReportPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Report entity) {
        String sql = "INSERT INTO denuncia (desciption, number, street, neighborhood, city, cep, type, situation) ";
                sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ; ";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setInt(2, entity.getNumber());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getNeighborhood());
            preparedStatement.setString(5, entity.getCity());
            preparedStatement.setString(6, entity.getCep());
            preparedStatement.setString(7, entity.getType().name());
            preparedStatement.setString(8, entity.getSituation().name());

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
    public Report readById(int id) {
        final String sql = "SELECT * FROM denuncia WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String description = resultSet.getString("description");
                final int number = resultSet.getInt("number");
                final String street = resultSet.getString("street");
                final String neighborhood = resultSet.getString("neighborhood");
                final String city = resultSet.getString("city");
                final String cep = resultSet.getString("cep");

                final String auxType = resultSet.getString("type");
                final Report.ReportType type = Report.ReportType.valueOf(auxType);

                final String auxSituation = resultSet.getString("situation");
                final Report.ReportSituation situation = Report.ReportSituation.valueOf(auxSituation);

                final Report report = new Report();

                report.setId(entityId);
                report.setDescription(description);
                report.setNumber(number);
                report.setStreet(street);
                report.setNeighborhood(neighborhood);
                report.setCity(city);
                report.setCep(cep);
                report.setType(type);
                report.setSituation(situation);

                return report;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Report> readall() {
        return List.of();
    }
}
