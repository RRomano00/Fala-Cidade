package br.com.faitec.fala_cidade.implementation.dao.postgres.configuration;

import br.com.faitec.fala_cidade.port.service.tools.ResourceFileService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

@Configuration
public class PostgresConnectionManagerConfiguration {

    @Value("${spring.datasource.base.url}")
    private String databaseBaseUrl;

    @Value("${spring.datasource.name}")
    private String databaseName;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    private ResourceFileService resourceFileService;

    private final Environment environment;

    public PostgresConnectionManagerConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        final DataSource build = DataSourceBuilder.create()
                .url(databaseBaseUrl).username(databaseUsername).password(databasePassword).build();

        final Connection connection = build.getConnection();

        createDatabaseIfNotExists(connection);

        return build;
    }

    private void createDatabaseIfNotExists(Connection connection) throws SQLException {
        final Statement statement = connection.createStatement();

        String sql = "SELECT COUNT(*) AS dbs ";
        sql += " FROM pg_catalog.pg_database ";
        sql += " WHERE lower(datname) = '" + databaseName + "' ;";

        ResultSet resultSet = statement.executeQuery(sql);

        boolean dbExists = resultSet.next();
        if (!dbExists || resultSet.getInt("dbs") == 0){
            String createDbSql = "CREATE DATABASE " + databaseName + " WITH ";
            createDbSql += " OWNER = postgres ENCODING = 'UTF8' ";
            createDbSql += " CONNECTION LIMIT = -1;";

            PreparedStatement preparedStatement = connection.prepareStatement(createDbSql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
        }


    }

    @Bean
    @DependsOn("dataSource")
    public Connection getConnection() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseUrl);
        hikariConfig.setUsername(databaseUsername);
        hikariConfig.setPassword(databasePassword);

        return new HikariDataSource(hikariConfig).getConnection();
    }

    @Bean
    @DependsOn("getConnection")
    public boolean createTableandInsertData() throws SQLException, IOException{
        Connection connection = getConnection();

        final String basePath = "fala-cidade-db-scripts";

        final String createTableSql = resourceFileService.read(basePath + "/PID_SCRIPT_CRIACAO-TABELAS.sql");

        PreparedStatement createStatement = connection.prepareStatement(createTableSql);
        createStatement.executeUpdate();
        createStatement.close();

        final String insertDataSql = resourceFileService.read(basePath + getInsertScript());

        final PreparedStatement insertStatement = connection.prepareStatement(insertDataSql);
        insertStatement.execute();
        insertStatement.close();

        return true;
    }

    public String getInsertScript(){
        boolean isBasicProfile = Arrays.asList(environment.getActiveProfiles()).contains("basic");
        if (isBasicProfile){
            return "/PID_SCRIPT_POPULAR-TABELAS-BASIC.sql";
        }
        return "/PID_SCRIPT_POPULAR-TABELAS-JWT.sql";
    }
}
