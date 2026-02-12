package model;

import java.util.List;

public class Matrix {
    private Fraction[][] data;
    private int rows;
    private int cols;
    private List<String> log;

    // REQUIRES: numRows > 0, numCols > 0
    // MODIFIES: this
    // EFFECTS: constructs a matrix with given dimensions; all entries initialized to 0
    public Matrix(int numRows, int numCols) {
    }

    // REQUIRES: row < rows, col < cols
    // MODIFIES: this
    // EFFECTS: sets the element at [row][col] to f 
    public void setElement(int row, int col, Fraction f) {
    }

    // REQUIRES: row < rows, col < cols
    // EFFECTS: returns the Fraction at [row][col]
    public Fraction getElement(int row, int col) {
        return null;
    }

    // REQUIRES: m2 != null, this.rows == m2.rows, this.cols == m2.cols
    // EFFECTS: returns a new Matrix that is the sum of this and m2
    public Matrix add(Matrix m2) {
        return null;
    }

    // REQUIRES: m2 != null, this.cols == m2.rows
    // EFFECTS: returns a new Matrix that is the product (this * m2)
    public Matrix multiply(Matrix m2) {
        return null;
    }

    // REQUIRES: v != null, this.cols == v.size()
    // EFFECTS: returns a new Vector that is the result of (this * v)
    public Vector multiplyVector(Vector v) {
        return null;
    }

    // EFFECTS: returns a new Matrix that is the transpose (A^T)
    public Matrix transpose() {
        return null;
    }


    // MODIFIES: this, log
    // EFFECTS: transforms matrix into Reduced Row Echelon Form;
    //          records standard row operations in log
    public void calculaterref() {
    }

    // REQUIRES: b != null, b.size() == this.rows
    // EFFECTS: returns the unique solution vector x for Ax=b;
    //          returns null if the system is inconsistent or has infinite solutions
    public Vector solve(Vector b) {
        return null;
    }

    // REQUIRES: this.isSquare() == true
    // EFFECTS: returns the determinant of this matrix
    public Fraction getDeterminant() {
        return null;
    }

    // REQUIRES: this.isSquare() == true, this.getDeterminant().getNumerator() != 0
    // EFFECTS: returns a new Matrix that is the inverse (A^-1)
    public Matrix invert() {
        return null;
    }


    // REQUIRES: r1 < rows, r2 < rows
    // MODIFIES: this
    // EFFECTS: swaps row r1 and r2; adds "R[r1] <-> R[r2]" to log
    private void swapRows(int r1, int r2) {
    }

    // REQUIRES: row < rows, scalar != null
    // MODIFIES: this
    // EFFECTS: multiplies row by scalar; adds "[scalar] * R[row] -> R[row]" to log
    private void scaleRow(int row, Fraction scalar) {
    }

    // REQUIRES: sourceRow < rows, destRow < rows, scalar != null
    // MODIFIES: this
    // EFFECTS: adds (scalar * sourceRow) to destRow; 
    //          adds "[scalar] * R[sourceRow] + R[destRow] -> R[destRow]" to log
    private void addRowMultiple(int sourceRow, int destRow, Fraction scalar) {
    }

    // EFFECTS: returns the rank of the matrix 
    public int getRank() {
        return 0;
    }

    // EFFECTS: returns true if the system Ax = b has at least one solution
    public boolean isConsistent(Vector b) {
        return false;
    }

    // EFFECTS: returns true if rows == cols
    public boolean isSquare() {
        return rows == cols;
    }

    // EFFECTS: returns the history of row operations performed during RREF
    public List<String> getLog() {
        return null;
    }
}