package Connect_Four;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 3/25/17.
 */
public class PathTest {
    @Test
    public void equals() throws Exception {
        Path path1 = new Path();
        Path path2 = new Path();
        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);
        Position position3 = new Position(2,2);
        Position position4 = new Position(0,0);
        Position position5 = new Position(1,1);
        Position position6 = new Position(2,2);
        path1.addPosition(position1);
        path1.addPosition(position2);
        path1.addPosition(position3);
        path2.addPosition(position4);
        path2.addPosition(position5);
        path2.addPosition(position6);
        assertEquals(path1,path2);
        position6.setH(3);
        position6.setV(3);
        assertNotEquals(path1,path2);
        position6.setH(2);
        position6.setV(2);
        assertEquals(path1,path2);
        path1.removePosition(position3);
        assertNotEquals(path1,path2);
    }

}