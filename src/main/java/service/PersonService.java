package service;

import dao.JDBCPersonDAO;
import dao.PersonDAO;
import model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonService {

    private PersonDAO personDao = new JDBCPersonDAO("person");

    public PersonService(){
    }

    public Optional<Person> findPerson(String firstName, String lastName) throws SQLException {
        return personDao.find(firstName, lastName);
    }

    public void insertPerson(Person person) throws SQLException {
        personDao.insert(person);
    }

    public void updatePerson(int id, Person person) throws SQLException {
        personDao.update(id, person);
    }

    public void deletePerson(int id) throws SQLException{
        personDao.delete(id);
    }

    public List<Person> selectAllPersons() throws SQLException {
        return personDao.selectAll();
    }


}
