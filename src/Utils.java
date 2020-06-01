import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is the class wich contains all the important methods.Class Utils contains 4 functional interfaces , each containing
 * an abastract method.All the abastract methods are implemented with the use of a lambda expressions and on some occasions
 * streams are used also in the necessary computations.
 * <p>
 * First we have the functional Interface Reading wich we use to implement the readFile method.
 * Second we have the functional Interface Adding wich we use to implement the addPerson method.
 * Third we have the functional Interface Writing wich we use to implement the writeFirstLast method.
 * And Forth we have the functional Interface ActualWriting wich we use to implement the ActualWriteFirstLast method.
 * <p>
 * After all these methods have been implemented we combine them all into a larger method called doCoolStuff() wich contains
 * all the logic for the application and has the necessary parameters in order to fulfill the homework criteria.
 *
 * @author R.Tudor Andrei
 */

public class Utils {

    //Here i implement the functional interface Reading's method readFile() as static with the help of a lambda.It takes a String input
    //and returns a list of Strings.

    public static final Reading READ_A_FILE = (String file) -> {
        System.out.println("Reading from " + file + "\n");
        List<String> lines = Files.readAllLines(Paths.get(file));
        return lines;
    };

    //Here i implement the functional interface Adding's method addPersons() as static with the help of a lambda expression.It takes a List of
    //Strings as an input(From Read_A_FILE) and returns a list of Persons.This method splits the received Strings at comma, sepparates them
    //into different array index fields and based on them, creates Person objects.

    public static final Adding ADD_A_PERSON = (List<String> list) -> {
        List<Person> allThePersons = new ArrayList<>();
        for (String line : list) {
            String[] fields = line.split(",");
            String lastName = fields[0];
            String firstName = fields[1];
            int year = Integer.parseInt(fields[2].substring(0, 4));
            int month = Integer.parseInt(fields[2].substring(5, 7));
            int day = Integer.parseInt(fields[2].substring(8, 10));
            LocalDate birthDate = LocalDate.of(year, month, day);
            Person p = new Person(firstName, lastName, birthDate);
            allThePersons.add(p);
        }
        return allThePersons;

    };

    //Here i implement the functional interface Writing's method writeFirsLast() as static with the help of a lambda expression.It takes a List of
    //Persons as argument and also an integer wich represents the month.The method takes the persons first and last name and adds them
    //to a list of Strings only if it passes some conditions, and then returns this list.

    public static final Writing WRITE_IT = (List<Person> list, int month) -> {
        List<String> writeThis = new ArrayList<>();
        if (month >= 1 && month <= 12) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getBirthDate().getMonthValue() == month) {
                    writeThis.add(list.get(i).getFirstName() + " " + list.get(i).getLastName());
                }
            }

        } else {
            System.out.println("Wrong month number");
        }
        return writeThis;
    };

    //Here i implement the abstract method actualWriteFirstLast() wich belongs to the functional interface ActualWriting.


    public static final ActualWriting WRITE_THIS_NAMES = (List<String> list, String fileName) -> {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        list.sort(Comparator.comparing(String::toString));
        for (String s : list) {
            writer.write(s);
            writer.newLine();
        }
        writer.close();
    };

    //doCoolStuff() is the method that puts all the other methods together into a small application.
    //It uses a Stream to sort person objects by first name and print them out consecutively.
    //Takes the 3 arguments required for the homework.

    public static void doCoolStuff(String inFile, int month, String outFile) throws IOException {
        List<String> whatItHasRead = Utils.READ_A_FILE.readFile(inFile);
        List<Person> personObjects = Utils.ADD_A_PERSON.addPersons(whatItHasRead);
        PersonCompare compareByFirstName = new PersonCompare();
        personObjects.stream().sorted(compareByFirstName).forEach(System.out::println);
        System.out.println("\n" + "-------------------" + "\n");
        List<String> writeStringsToTxt = Utils.WRITE_IT.writeFirstLast(personObjects, month);
        Utils.WRITE_THIS_NAMES.actualWriteFirstLast(writeStringsToTxt, outFile);
        System.out.println("Your text file is ready !");
    }
}

//All the Functional interfaces

@FunctionalInterface
interface Reading {
    List<String> readFile(String file) throws IOException;
}

@FunctionalInterface
interface Adding {
    List<Person> addPersons(List<String> list);
}

@FunctionalInterface
interface Writing {
    List<String> writeFirstLast(List<Person> list, int month);
}

@FunctionalInterface
interface ActualWriting {
    void actualWriteFirstLast(List<String> list, String fileName) throws IOException;
}
