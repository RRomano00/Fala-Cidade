package br.com.faitec.fala_cidade.implementation.dao.postgres;

import br.com.faitec.fala_cidade.domain.Report;
import br.com.faitec.fala_cidade.domain.dto.GetReport;
import br.com.faitec.fala_cidade.port.dao.report.ReportDao;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "INSERT INTO report (description, number, street, neighborhood, city, type, status, complainant_id) ";
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
            preparedStatement.setString(6, entity.getType().name());
            preparedStatement.setString(7, entity.getStatus().name());
            preparedStatement.setString(8, entity.getComplainant_id());

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
    public GetReport readById(int id) {
        String sql = "SELECT r.*, u.fullname, u.email FROM report r ";
        sql += "JOIN complainant c ON r.complainant_id = c.id ";
        sql += "JOIN users u ON c.users_id = u.id ";
        sql += "WHERE r.id = ?; ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String description = resultSet.getString("description");
                final int number = resultSet.getInt("number");
                final String street = resultSet.getString("street");
                final String neighborhood = resultSet.getString("neighborhood");
                final String city = resultSet.getString("city");
                final String email = resultSet.getString("email");
                final String fullname = resultSet.getString("fullname");

                final String auxType = resultSet.getString("type");
                final Report.ReportType type = Report.ReportType.valueOf(auxType);

                final String auxStatus = resultSet.getString("status");
                final Report.ReportStatus status = Report.ReportStatus.valueOf(auxStatus);

                final GetReport report = new GetReport();

                report.setId(entityId);
                report.setDescription(description);
                report.setNumber(number);
                report.setStreet(street);
                report.setNeighborhood(neighborhood);
                report.setCity(city);
                report.setType(type);
                report.setStatus(status);
                report.setEmail(email);
                report.setFullname(fullname);

                return report;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<GetReport> readall() {
        final List<GetReport> reports = new ArrayList<>();

        String sql = "SELECT r.*, u.fullname, u.email FROM report r ";
        sql += "JOIN complainant c ON r.complainant_id = c.id ";
        sql += "JOIN users u ON c.users_id = u.id ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                final int entityId = resultSet.getInt("id");
                final String description = resultSet.getString("description");
                final int number = resultSet.getInt("number");
                final String street = resultSet.getString("street");
                final String neighborhood = resultSet.getString("neighborhood");
                final String city = resultSet.getString("city");
                final String email = resultSet.getString("email");
                final String fullname = resultSet.getString("fullname");

                final String auxType = resultSet.getString("type");
                final Report.ReportType type = Report.ReportType.valueOf(auxType);

                final String auxStatus = resultSet.getString("status");
                final Report.ReportStatus status = Report.ReportStatus.valueOf(auxStatus);

                final GetReport report = new GetReport();

                report.setId(entityId);
                report.setDescription(description);
                report.setNumber(number);
                report.setStreet(street);
                report.setNeighborhood(neighborhood);
                report.setCity(city);
                report.setType(type);
                report.setStatus(status);
                report.setEmail(email);
                report.setFullname(fullname);

                reports.add(report);
            }
            preparedStatement.close();
            resultSet.close();

            return reports;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateReportStatusToInProgress(int id) {
        String sql = "UPDATE report SET status = 'EM_ANDAMENTO' WHERE id = ?; ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateReportStatusToConclude(int id) {
        String sql = "UPDATE report SET status = 'ATENDIDA' WHERE id = ?; ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
