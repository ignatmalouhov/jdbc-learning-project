import model.Person;
import service.PersonService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        PersonService personService = new PersonService();
        Person person = new Person("John", "Snow");

        try {
            personService.insertPerson(person);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            personService.updatePerson(1, person);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            personService.deletePerson(20);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            Optional<Person> result = personService.findPerson("Test", "Test");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        List<Person> persons = new ArrayList<>();
        try {
            persons = personService.selectAllPersons();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
