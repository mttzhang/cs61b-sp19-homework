import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        CharacterComparator offByN2 = new OffByN(2);

        assertFalse(offByN2.equalChars('a', 'a'));
        assertFalse(offByN2.equalChars(' ', ' '));

        assertTrue(offByN2.equalChars('a', 'c'));
        assertTrue(offByN2.equalChars('d', 'b'));
        assertTrue(offByN2.equalChars('r', 't'));

        CharacterComparator offByN1 = new OffByN(1);

        assertFalse(offByN1.equalChars('a', 'a'));
        assertFalse(offByN1.equalChars('c', 'x'));

        assertTrue(offByN1.equalChars('a', 'b'));
        assertTrue(offByN1.equalChars('y', 'x'));


    }
}
