package dao;

import model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    Optional<Person> find(String table, String firstName, String lastName) throws SQLException;

    void insert(String table, Person person) throws SQLException;

    void update(String table, int id, Person person) throws SQLException;

    void delete(String table, int id) throws SQLException;

    List<Person> selectAll(String table) throws SQLException;

}