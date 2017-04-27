import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sander on 1.05.2016.
 */
public class MainTest {

    private Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void shouldRotate(){
        int[][] matrix = {{7, 9, 8}, {10, 15, 20}, {1, 2, 3}};

        main.rotate(matrix, 3);
        int[][] expectedResult = {{1, 10, 7}, {2, 15, 9}, {3, 20, 8}};
        assertArrayEquals(expectedResult, matrix);
    }
}