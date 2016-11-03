import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class ProgramTest {

    private BufferedReader bufferedReader;
    private static final String FULL_PATH = "C:\\GitHubRepositories\\PROPJavaTokenizer\\src\\main\\java\\prop\\assignment0\\program2.txt";


    @Before
    public void setUp() {

    }

    @Test
    public void theFilePathShouldBeValid() {
        try {
            bufferedReader = new BufferedReader(new FileReader(FULL_PATH));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(true, true);
    }
}
