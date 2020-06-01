import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


class UtilsTest {

    @Test
    void doCoolStuff() {
        //Given
        try {
            Utils.doCoolStuff("Tests/input.txt", 10, "Tests/output.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //When
        List<String> result = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        try {
            result = Utils.READ_A_FILE.readFile("Tests/output.txt");
            expected = Utils.READ_A_FILE.readFile("Tests/test.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Then
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }
}
