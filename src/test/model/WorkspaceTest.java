package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceTest {
    private Workspace testWorkspace;
    private Matrix m1;
    private Matrix m2;
    private Vector v1;
    private Vector v2;

    @BeforeEach
    void runBefore() {
        testWorkspace = new Workspace("My VectorVision Workspace");
        
        m1 = new Matrix(2, 2);
        m1.setElement(0, 0, new Fraction(1, 1));
        
        m2 = new Matrix(3, 3);
        m2.setElement(1, 1, new Fraction(5, 1));

        Fraction[] arr1 = { new Fraction(1, 1),
             new Fraction(2, 1) };
        v1 = new Vector(arr1);

        Fraction[] arr2 = { new Fraction(3, 1), 
            new Fraction(4, 1), 
            new Fraction(5, 1) };
        v2 = new Vector(arr2);
    }

    @Test
    void testConstructor() {
        assertEquals("My VectorVision Workspace", testWorkspace.getName());
        assertTrue(testWorkspace.getMatrices().isEmpty());
        assertTrue(testWorkspace.getVectors().isEmpty());
        assertTrue(testWorkspace.isEmpty());
    }

    @Test
    void testAddMatrix() {
        testWorkspace.addMatrix("Matrix A", m1);
        
        assertFalse(testWorkspace.isEmpty());
        assertEquals(1, testWorkspace.getMatrices().size());
        assertEquals(m1, testWorkspace.getMatrices().get("Matrix A"));

        
        testWorkspace.addMatrix("Matrix A", m2);
        assertEquals(1, testWorkspace.getMatrices().size());
        assertEquals(m2, testWorkspace.getMatrices().get("Matrix A"));
    }

    @Test
    void testAddVector() {
        testWorkspace.addVector("Vector U", v1);
        
        assertFalse(testWorkspace.isEmpty());
        assertEquals(1, testWorkspace.getVectors().size());
        assertEquals(v1, testWorkspace.getVectors().get("Vector U"));


        testWorkspace.addVector("Vector V", v2);
        assertEquals(2, testWorkspace.getVectors().size());

        testWorkspace.addVector("Vector U", v2);
        assertEquals(v2, testWorkspace.getVectors().get("Vector U"));
    }

    @Test
    void testAddBoth() {
        testWorkspace.addMatrix("A", m1);
        testWorkspace.addVector("v", v1);
        
        assertFalse(testWorkspace.isEmpty());
        assertEquals(1, testWorkspace.getMatrices().size());
        assertEquals(1, testWorkspace.getVectors().size());
    }

    @Test
    void testRemoveMatrix() {
        testWorkspace.addMatrix("Matrix A", m1);
        testWorkspace.addMatrix("Matrix B", m2);
        
        Matrix removed = testWorkspace.removeMatrix("Matrix A");
        
        assertEquals(m1, removed);
        assertEquals(1, testWorkspace.getMatrices().size());
        assertNull(testWorkspace.getMatrices().get("Matrix A"));
        

        Matrix notFound = testWorkspace.removeMatrix("Matrix C");
        assertNull(notFound);
    }

    @Test
    void testRemoveVector() {
        testWorkspace.addVector("Vec 1", v1);
        
        Vector removed = testWorkspace.removeVector("Vec 1");
        
        assertEquals(v1, removed);
        assertTrue(testWorkspace.getVectors().isEmpty());
        assertTrue(testWorkspace.isEmpty()); 

        Vector notFound = testWorkspace.removeVector("Vec 2");
        assertNull(notFound);
    }

    @Test
    void testIsEmptyEdgeCases() {
        testWorkspace.addMatrix("M1", m1);
        assertFalse(testWorkspace.isEmpty());

        testWorkspace.removeMatrix("M1"); 
        assertTrue(testWorkspace.isEmpty()); 
        
        testWorkspace.addVector("V1", v1);
        assertFalse(testWorkspace.isEmpty());
    }

    @Test
    void testToJson() {
        // TODO WORK IN PROGRESS
    }
}