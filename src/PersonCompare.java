import java.util.Comparator;

/**
 * This class is used only to implement a Comparator of Person objects by a persons firstName.
 *
 * @author R.Tudor Andrei
 */

public class PersonCompare implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return (p1.getFirstName().compareTo(p2.getFirstName()));
    }


}

