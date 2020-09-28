import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @Test
    public void testGetBmiScore() {
        BodyMassIndex a = new BodyMassIndex(70,180);
        assertEquals(25.8, a.getBmiScore(70, 180));
    }

    @Test
    public void testRoundingGetBmiScore() {
        BodyMassIndex b = new BodyMassIndex(85, 203);
        assertEquals(19.8, b.getBmiScore(85, 203));
    }

    @Test
    public void testForUnderweightCategory() {
        BodyMassIndex c = new BodyMassIndex(80, 150);
        assertEquals("Underweight", c.getBmiCategory(80, 150));
    }

    @Test
    public void testForNormalWeightCategory() {
        BodyMassIndex d = new BodyMassIndex(75, 185);
        assertEquals("Normal Weight", d.getBmiCategory(75, 185));
    }

    @Test
    public void testForOverweightCategory() {
        BodyMassIndex e = new BodyMassIndex(75, 220);
        assertEquals("Overweight", e.getBmiCategory(75, 220));
    }

    @Test
    public void testForObesityCategory() {
        BodyMassIndex f = new BodyMassIndex(40, 300);
        assertEquals("Obesity", f.getBmiCategory(40, 300));
    }

}