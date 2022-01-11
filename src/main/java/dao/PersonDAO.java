package dao;

import model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    Optional<Person> find(String firstName, String lastName) throws SQLException;

    void insert(Person person) throws SQLException;

    void update(int id, Person person) throws SQLException;

    void delete(
            int id) throws SQLException;

    List<Person> selectAll() throws SQLException;

}