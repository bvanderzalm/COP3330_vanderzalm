import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @Test
    public void testGetBmiScore() {
        BodyMassIndex a = new BodyMassIndex(70,180);
        assertEquals(25.82448979591837, a.getBmiScore(70, 180));
    }

    @Test
    public void testForUnderweightCategory() {
        BodyMassIndex b = new BodyMassIndex(80, 150);
        assertEquals("Underweight", b.getBmiCategory(80, 150));
    }

    @Test
    public void testForNormalWeightCategory() {
        BodyMassIndex c = new BodyMassIndex(75, 185);
        assertEquals("Normal Weight", c.getBmiCategory(75, 185));
    }

    @Test
    public void testForOverweightCategory() {
        BodyMassIndex d = new BodyMassIndex(75, 220);
        assertEquals("Overweight", d.getBmiCategory(75, 220));
    }

    @Test
    public void testForObesityCategory() {
        BodyMassIndex e = new BodyMassIndex(40, 300);
        assertEquals("Obesity", e.getBmiCategory(40, 300));
    }

}