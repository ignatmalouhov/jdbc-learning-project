package dao;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utility.JdbcUtility.connect;

public class JDBCPersonDAO implements PersonDAO {

    public JDBCPersonDAO(String table) {
        this.table = table;
    }

    private String table;

    private int nextIndex(String table) throws SQLException {
        String sqlQuery = "SELECT MAX(ID) FROM " + table;

        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();
        int lastIndex = resultSet.getInt(1);

        resultSet.close();
        statement.close();
        connection.close();

        return ++lastIndex;
    }

    public Optional<Person> find(String firstName, String lastName) throws SQLException {
        String sqlQuery = "SELECT * FROM " + table + " WHERE FIRST_NAME=?";

        Person person = null;

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, firstName);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String tempFirstName = resultSet.getString(2);
            String tempLastName = resultSet.getString(3);
            person = new Person(id, firstName, lastName);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return person == null ? Optional.empty() : Optional.of(person);
    }

    public void insert(Person person) throws SQLException {
        String sqlQuery = "INSERT INTO " + table + " (ID, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?)";

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, nextIndex(table));
        statement.setString(2, person.getFirstName());
        statement.setString(3, person.getLastName());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void update(int id, Person person) throws SQLException {
        String sqlQuery = "UPDATE " + table + " SET FIRST_NAME=?, LAST_NAME=? WHERE ID=" + id;

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, person.getFirstName());
        statement.setString(2, person.getLastName());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM " + table + " WHERE ID=" + id;

        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }


    public List<Person> selectAll() throws SQLException {
        String sqlQuery = "SELECT * FROM " + table;
        List<Person> persons = new ArrayList<>();

        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);

            Person person = new Person(id, firstName, lastName);
            persons.add(person);
        }
        return persons;
    }
}