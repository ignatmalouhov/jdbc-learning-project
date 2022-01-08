import dao.JDBCPersonDAO;
import dao.PersonDAO;
import model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        PersonDAO personDAO = new JDBCPersonDAO();
        String table = "person";
        Person person = new Person("John", "Snow");

        try {
            personDAO.insert(table, person);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            personDAO.update(table, 1, person);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            personDAO.delete(table, 2);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        try {
            Optional<Person> result = personDAO.find(table, "Test", "Test");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        List<Person> persons = new ArrayList<>();
        try {
            persons = personDAO.selectAll(table);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
