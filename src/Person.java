import java.time.LocalDate;

/**
 * This Class creates Person objects.Here we have 3 private fields, firstName,lastName and birthDate theyr respective
 * getters, and overloaded constructor wich uses those respective fields and the overridden toString() method.
 *
 * @author R.Tudor Andrei
 */

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;


    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String toString() {
        return String.format(firstName + " " + lastName + " " + birthDate);
    }


}
