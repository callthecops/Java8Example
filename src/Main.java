import java.io.IOException;

/**
 * This is the main class for our 13'th week homework.It contains just the main method where we have a try/catch block
 * for the method doCoolStuff();
 *
 * @author R.Tudor Andrei
 */

public class Main {

    public static void main(String[] args) {
        try {
            Utils.doCoolStuff("src/persons.txt", 2, "src/yourFile.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

