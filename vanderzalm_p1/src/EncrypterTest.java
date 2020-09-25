import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncrypterTest {

    @Test
    public void testEncrypt1234to0189() {
        Encrypter e = new Encrypter();
        assertEquals("0189", e.encrypt("1234"));
    }

    @Test
    public void testEncrypt0913() {
        Encrypter e = new Encrypter();
        assertEquals("8076", e.encrypt("0913"));
    }

}