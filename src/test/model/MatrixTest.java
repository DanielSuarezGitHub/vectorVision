package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MatrixTest {
    private Matrix m2x2;
    private Matrix m3x3;
    private Fraction f1;
    private Fraction f0;

    @BeforeEach
    void runBefore() {
        f1 = new Fraction(1, 1);
        f0 = new Fraction(0, 1);

        m2x2 = new Matrix(2, 2);
        m3x3 = new Matrix(3, 3);
    }

    @Test
    void testConstructorAndGetSet() {
        assertTrue(m2x2.isSquare());
        m2x2.setElement(0, 1, new Fraction(5, 1));
        assertEquals(5, m2x2.getElement(0, 1).getNumerator());
        assertEquals(0, m2x2.getElement(0, 0).getNumerator());
    }

    @Test
    void testAdd() {
        Matrix a = new Matrix(2, 2);
        a.setElement(0, 0, f1);
        Matrix b = new Matrix(2, 2);
        b.setElement(0, 0, f1);

        Matrix sum = a.add(b);
        assertEquals(2, sum.getElement(0, 0).getNumerator());
        assertEquals(0, sum.getElement(1, 1).getNumerator());
    }

    @Test
    void testMultiply() {
        Matrix a = new Matrix(2, 2);
        a.setElement(0, 0, new Fraction(1, 1));
        a.setElement(0, 1, new Fraction(2, 1));
        a.setElement(1, 0, new Fraction(3, 1));
        a.setElement(1, 1, new Fraction(4, 1));

        Matrix identity = new Matrix(2, 2);
        identity.setElement(0, 0, f1);
        identity.setElement(1, 1, f1);

        Matrix result = a.multiply(identity);
        assertEquals(1, result.getElement(0, 0).getNumerator());
        assertEquals(4, result.getElement(1, 1).getNumerator());
        assertEquals(1, result.getElement(0, 0).getNumerator());
        assertEquals(4, result.getElement(1, 1).getNumerator());

        result = a.multiply(a);

        assertEquals(7, result.getElement(0, 0).getNumerator());
        assertEquals(10, result.getElement(0, 1).getNumerator());
        assertEquals(15, result.getElement(1, 0).getNumerator());
        assertEquals(22, result.getElement(1, 1).getNumerator());

    }

    @Test
    void testMultiplyVector() {
        Matrix a = new Matrix(2, 2);
        a.setElement(0, 0, new Fraction(2, 1));
        a.setElement(1, 1, new Fraction(3, 1));

        Fraction[] arrV = { new Fraction(1, 1), new Fraction(1, 1) };
        Vector v = new Vector(arrV);

        Vector result = a.multiplyVector(v);

        assertEquals(2, result.size());
        assertEquals(2, result.getComponent(0).getNumerator());
        assertEquals(1, result.getComponent(0).getDenominator());
        assertEquals(3, result.getComponent(1).getNumerator());
        assertEquals(1, result.getComponent(1).getDenominator());
    }

    @Test
    void testTranspose() {
        Matrix a = new Matrix(2, 3);
        a.setElement(0, 2, new Fraction(7, 1));

        Matrix at = a.transpose();
        assertTrue(at.isSquare());
        assertEquals(7, at.getElement(2, 0).getNumerator());
    }

    @Test
    void testCalculaterref() {
        Matrix m = new Matrix(2, 2);
        m.setElement(0, 0, new Fraction(2, 1));
        m.setElement(0, 1, new Fraction(4, 1));
        m.setElement(1, 1, f1);

        m.calculaterref();

        assertEquals(1, m.getElement(0, 0).getNumerator());
        assertEquals(0, m.getElement(0, 1).getNumerator());
        assertEquals(1, m.getElement(1, 1).getNumerator());

        List<String> log = m.getLog();
        assertFalse(log.isEmpty());
        assertTrue(log.get(0).contains("R"));
    }

    @Test
    void testGetDeterminant() {
        Matrix m = new Matrix(2, 2);
        m.setElement(0, 0, new Fraction(1, 1));
        m.setElement(0, 1, new Fraction(2, 1));
        m.setElement(1, 0, new Fraction(3, 1));
        m.setElement(1, 1, new Fraction(4, 1));

        assertEquals(-2, m.getDeterminant().getNumerator());
    }

    @Test
    void testSolveUnique() {
        Matrix identity = new Matrix(2, 2);
        identity.setElement(0, 0, f1);
        identity.setElement(1, 1, f1);

        Fraction[] arrB = { new Fraction(5, 1), new Fraction(10, 1) };
        Vector b = new Vector(arrB);

        Vector x = identity.solve(b);
        assertNotNull(x);
        assertEquals(5, x.getComponent(0).getNumerator());
        assertEquals(10, x.getComponent(1).getNumerator());
    }

    @Test
    void testInconsistentSystem() {
        Matrix zero = new Matrix(1, 1);
        Fraction[] arrB = { new Fraction(5, 1) };
        Vector b = new Vector(arrB);

        assertNull(zero.solve(b));
        assertFalse(zero.isConsistent(b));
    }

}
