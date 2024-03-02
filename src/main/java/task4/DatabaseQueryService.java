package task4;

import task1.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static final String SQL_ERROR_MESSAGE = "Error occurred while executing SQL query: ";


    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(
                     new String(Files.readAllBytes(Paths.get("sql/find_max_projects_client.sql"))));
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int projectCount = resultSet.getInt("PROJECT_COUNT");
                result.add(new MaxProjectCountClient(name, projectCount));
            }


        } catch (SQLException | IOException ex) {
            System.out.println(SQL_ERROR_MESSAGE + ex.getMessage());
        }

        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> list = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/find_max_salary_worker.sql"))));
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                long salary = resultSet.getLong("SALARY");
                list.add(new MaxSalaryWorker(name, salary));

            }

        } catch (SQLException | IOException ex) {
            System.out.println(SQL_ERROR_MESSAGE + ex.getMessage());
        }

        return list;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> list = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/find_longest_project.sql"))));
             ResultSet resultSet = statement.executeQuery()) {


            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                long monthCount = resultSet.getLong("MONTH_COUNT");
                list.add(new LongestProject(id, monthCount));

            }

        } catch (SQLException | IOException ex) {
            System.out.println(SQL_ERROR_MESSAGE + ex.getMessage());
        }

        return list;
    }

    public List<YoungestAndEldestWorker> findYoungestEldestWorkers() {
        List<YoungestAndEldestWorker> list = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (
       PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/find_youngest_eldest_workers.sql"))));
            ResultSet resultSet = statement.executeQuery()){


            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String birthday = resultSet.getString("BIRTHDAY");
                int age = resultSet.getInt("AGE");
                list.add(new YoungestAndEldestWorker(name, birthday, age));

            }

        } catch (SQLException | IOException ex) {
            System.out.println(SQL_ERROR_MESSAGE + ex.getMessage());
        }

        return list;
    }

    public List<PrintProjectPrices> printProjectPrices() {
        List<PrintProjectPrices> list = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try (
        PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/print_project_prices.sql"))));
               ResultSet resultSet = statement.executeQuery()){


            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                long price = resultSet.getLong("PRICE");
                list.add(new PrintProjectPrices(name, price));

            }

        } catch (SQLException | IOException ex) {
            System.out.println(SQL_ERROR_MESSAGE + ex.getMessage());
        }

        return list;
    }


}