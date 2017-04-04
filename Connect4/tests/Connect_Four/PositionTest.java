package Connect_Four;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 3/25/17.
 */
public class PositionTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void equals() throws Exception {
        Position position1 = new Position(3,3);
        Position position2 = new Position(3,3);
        assertEquals(position1, position2);
        position2.setV(2);
        assertNotEquals(position1, position2);
        position2.setH(4);
        assertNotEquals(position1, position2);

    }

}