package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FractionTest {
    private Fraction fHalf;
    private Fraction fTwoThirds;
    private Fraction fNegFiveSixths;

    @BeforeEach
    void runBefore() {
        fHalf = new Fraction(1, 2);
        fTwoThirds = new Fraction(2, 3);
        fNegFiveSixths = new Fraction(5, -6);
    }

    @Test
    void testConstructorSimplification() {
        Fraction f = new Fraction(10, 20);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());

        Fraction f2 = new Fraction(0, 5);
        assertEquals(0, f2.getNumerator());
        assertEquals(1, f2.getDenominator());
    }

    @Test
    void testConstructorSignManagement() {
        Fraction negBottom = new Fraction(1, -2);
        assertEquals(-1, negBottom.getNumerator());
        assertEquals(2, negBottom.getDenominator());

        Fraction doubleNeg = new Fraction(-1, -2);
        assertEquals(1, doubleNeg.getNumerator());
        assertEquals(2, doubleNeg.getDenominator());
    }

    @Test
    void testAdd() {
        Fraction res = fHalf.add(fTwoThirds);
        assertEquals(7, res.getNumerator());
        assertEquals(6, res.getDenominator());

        Fraction res2 = fHalf.add(fNegFiveSixths);
        assertEquals(-1, res2.getNumerator());
        assertEquals(3, res2.getDenominator());
    }

    @Test
    void testSubtract() {
        Fraction res = fTwoThirds.subtract(fHalf);
        assertEquals(1, res.getNumerator());
        assertEquals(6, res.getDenominator());

        Fraction res2 = fNegFiveSixths.subtract(fHalf);
        assertEquals(-4, res2.getNumerator());
        assertEquals(3, res2.getDenominator());
    }

    @Test
    void testMultiply() {
        Fraction res = fHalf.multiply(fTwoThirds);
        assertEquals(1, res.getNumerator());
        assertEquals(3, res.getDenominator());

        Fraction res2 = fTwoThirds.multiply(fNegFiveSixths);
        assertEquals(-5, res2.getNumerator());
        assertEquals(9, res2.getDenominator());
    }

    @Test
    void testDivide() {
        Fraction res = fHalf.divide(fTwoThirds);
        assertEquals(3, res.getNumerator());
        assertEquals(4, res.getDenominator());
    }

    @Test
    void testToDecimal() {
        assertEquals(0.5, fHalf.toDecimal(), 0.000001);
        assertEquals(-0.833333, fNegFiveSixths.toDecimal(), 0.000001);
    }

    @Test
    void testToString() {
        assertEquals("1/2", fHalf.toString());
        assertEquals("-5/6", fNegFiveSixths.toString());
        assertEquals("0/1", new Fraction(0, 10).toString());
    }

    @Test
    void testEquals() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(1, 3);

        assertTrue(f1.equals(f2));
        assertFalse(f1.equals(f3));
        assertFalse(f1.equals(null));
    }

    @Test
    public void testZeroNumerator() {
        Fraction zero = new Fraction(0, 5);
        assertEquals(0, zero.getNumerator());
        assertEquals(1, zero.getDenominator()); 
    }

    @Test
    public void testLargeNumbers() {
        Fraction large1 = new Fraction(1000000, 1);
        Fraction large2 = new Fraction(1000000, 1);
        Fraction result = large1.multiply(large2);
        assertEquals(1000000000000L, result.getNumerator());
        assertEquals(1, result.getDenominator());
    }

    @Test
    void testAddToZero() {
        Fraction res = fHalf.add(new Fraction(-1, 2));
        assertEquals(0, res.getNumerator());
        assertEquals(1, res.getDenominator());
    }

    @Test
    void testSimplifyNegativeOnBoth() {
        Fraction res = new Fraction(-10, -20);
        assertEquals(1, res.getNumerator());
        assertEquals(2, res.getDenominator());
    }

    @Test
    void testSubtractToNegative() {
        Fraction res = fHalf.subtract(fTwoThirds);
        assertEquals(-1, res.getNumerator());
        assertEquals(6, res.getDenominator());
    }
}

