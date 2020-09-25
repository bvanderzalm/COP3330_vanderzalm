import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecrypterTest {

    @Test
    public void testDecrypt0189to1234() {
        Decrypter d = new Decrypter();
        assertEquals("1234", d.decrypt("0189"));
    }

}