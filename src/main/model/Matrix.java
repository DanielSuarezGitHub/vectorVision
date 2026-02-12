package model;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private Fraction[][] data;
    private int rows;
    private int cols;
    private List<String> log;

    // REQUIRES: numRows > 0, numCols > 0
    // MODIFIES: this
    // EFFECTS: constructs a matrix with given dimensions; all entries initialized
    // to 0
    public Matrix(int numRows, int numCols) {
        this.rows = numRows;
        this.cols = numCols;
        this.log = new ArrayList<String>();
        this.data = new Fraction[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                setElement(i, j, new Fraction(0, 1));
            }
        }
    }

    // REQUIRES: row < rows, col < cols
    // MODIFIES: this
    // EFFECTS: sets the element at [row][col] to f
    public void setElement(int row, int col, Fraction f) {
        data[row][col] = f;
    }

    // REQUIRES: row < rows, col < cols
    // EFFECTS: returns the Fraction at [row][col]
    public Fraction getElement(int row, int col) {
        return data[row][col];
    }

    // REQUIRES: m2 != null, this.rows == m2.rows, this.cols == m2.cols
    // EFFECTS: returns a new Matrix that is the sum of this and m2
    public Matrix add(Matrix m2) {
        Matrix m3 = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Fraction sum = getElement(i, j).add(m2.getElement(i, j));
                m3.setElement(i, j, sum);
            }
        }
        return m3;
    }

    // REQUIRES: m2 != null, this.cols == m2.rows
    // EFFECTS: returns a new Matrix that is the product (this * m2)
    public Matrix multiply(Matrix m2) {
        Matrix m3 = new Matrix(this.getNumRows(), m2.getNumCols());
        for (int i = 0; i < m3.getNumRows(); i++) {
            Vector aik = this.getRowVector(i);
            for (int j = 0; j < m3.getNumCols(); j++) {
                Vector bkj = m2.getColumnVector(j);
                Fraction product = aik.dot(bkj);
                m3.setElement(i, j, product);
            }
        }
        return m3;
    }

    // REQUIRES: v != null, this.cols == v.size()
    // EFFECTS: returns a new Vector that is the result of (this * v)
    public Vector multiplyVector(Vector v) {
        Fraction[] resultComponents = new Fraction[this.rows];
        for (int i = 0; i < this.rows; i++) {
            Vector rowVec = this.getRowVector(i);
            resultComponents[i] = rowVec.dot(v);
        }
        return new Vector(resultComponents);
    }

    // EFFECTS: returns a new Matrix that is the transpose (A^T)
    public Matrix transpose() {
        Matrix m2 = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Fraction current = getElement(i, j);
                m2.setElement(j, i, current);
            }
        }
        return m2;
    }

    // MODIFIES: this, log
    // EFFECTS: transforms matrix into Reduced Row Echelon Form;
    // records standard row operations in log
    public void calculaterref() {
        Fraction one = new Fraction(1, 1);

        int currentRow = 0;

        for (int j = 0; j < cols && currentRow < rows; j++) {
            int pivotRow = findPivotRow(j, currentRow);
            Fraction pivotValue = getElement(pivotRow, j);

            if (pivotValue.getNumerator() == 0) {
                continue;
            }
            if (pivotRow != currentRow) {
                swapRows(pivotRow, currentRow);
            }

            if (!pivotValue.equals(one)) {
                Fraction scalar = one.divide(pivotValue);
                scaleRow(currentRow, scalar);
            }
            elimanateColumn(j, currentRow);
            currentRow++;
        }
    }

    // REQUIRES: b != null, b.size() == this.rows
    // EFFECTS: returns the unique solution vector x for Ax=b;
    // returns null if the system is inconsistent or has infinite solutions
    public Vector solve(Vector b) {
        Matrix augmented = augment(b);
        augmented.calculaterref();
        for (int i = 0; i < augmented.getNumRows(); i++) {
            if (augmented.isRowContradictory(i)) {
                return null;
            }
        }

        if (this.getRank() < this.cols) {
            return null;
        }

        Fraction[] solution = new Fraction[this.cols];
        for (int i = 0; i < this.cols; i++) {
            solution[i] = augmented.getElement(i, this.cols);
        }
        return new Vector(solution);
    }

    // REQUIRES: this must be augmented and in RREF;
    // EFFECTS returns true of row at index i is contradictory;
    private boolean isRowContradictory(int i) {
        Fraction b = this.data[i][cols - 1];
        if (isRowAllZeros(i) && b.getNumerator() != 0) {
            return true;
        }
        return false;
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
        Fraction[] row1 = data[r1];
        Fraction[] row2 = data[r2];
        data[r2] = row1;
        data[r1] = row2;
        log.add("R" + r1 + " <-> " + "R" + r2);
    }

    // REQUIRES: row < rows, scalar != null
    // MODIFIES: this
    // EFFECTS: multiplies row by scalar; adds "[scalar] * R[row] -> R[row]" to log
    private void scaleRow(int row, Fraction scalar) {
        for (int i = 0; i < cols; i++) {
            data[row][i] = data[row][i].multiply(scalar);
        }
        log.add(scalar.toString() + " * R" + row + " -> R" + row);
    }

    // REQUIRES: sourceRow < rows, destRow < rows, scalar != null
    // MODIFIES: this
    // EFFECTS: adds (scalar * sourceRow) to destRow;
    // adds "[scalar] * R[sourceRow] + R[destRow] -> R[destRow]" to log
    private void addRowMultiple(int sourceRow, int destRow, Fraction scalar) {
        for (int i = 0; i < cols; i++) {
            Fraction termToAdd = data[sourceRow][i].multiply(scalar);
            data[destRow][i] = data[destRow][i].add(termToAdd);
        }
        log.add(scalar.toString() + " * R" + sourceRow + " + R" + destRow + " -> R" + destRow);
    }

    // MODIFIES: this
    // EFFECTS: Returns Matrix rank,
    public int getRank() {
        Matrix temp = this.copy();
        temp.calculaterref();
        int rank = 0;
        for (int i = 0; i < temp.getNumRows(); i++) {
            if (!temp.isRowAllZeros(i)) {
                rank++;
            }
        }
        return rank;
    }

    // EFFECTS: returns true if every element in the specified row is zero
    private boolean isRowAllZeros(int rowIndex) {
        for (int j = 0; j < cols; j++) {
            if (data[rowIndex][j].getNumerator() != 0) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: returns true if the system Ax = b has at least one solution
    public boolean isConsistent(Vector b) {
        int rankA = this.getRank();

        Matrix augmented = this.augment(b);
        int rankAugmented = augmented.getRank();

        return rankA == rankAugmented;
    }

    // EFFECTS: returns true if rows == cols
    public boolean isSquare() {
        return rows == cols;
    }

    // EFFECTS: returns the history of row operations performed during RREF
    public List<String> getLog() {
        return this.log;
    }

    // EFFECTS: Returns the ith row as a Vector
    private Vector getRowVector(int rowIndex) {
        return new Vector(data[rowIndex]);
    }

    // EFFECTS: Returns the j-th column as a Vector
    private Vector getColumnVector(int colIndex) {
        Fraction[] colData = new Fraction[rows];
        for (int i = 0; i < rows; i++) {
            colData[i] = data[i][colIndex];
        }
        return new Vector(colData);
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    // REQUIRES: col < cols, startRow < rows
    // EFFECTS: returns the index of the row with the largest absolute value in col
    // j starting from search row;
    private int findPivotRow(int col, int startRow) {
        int maxRow = startRow;
        double maxVal = Math.abs(data[startRow][col].toDecimal());
        for (int i = startRow + 1; i < rows; i++) {
            double currentVal = Math.abs(data[i][col].toDecimal());
            if (currentVal > maxVal) {
                maxVal = currentVal;
                maxRow = i;
            }
        }
        return maxRow;
    }

    // REQUIRES: j < cols, pivotRow < rows, getElement(pivotRow, j) == 1;
    // MODIFIES: this
    // EFFECTS: performs the row operations to eleminate above and below a pivot
    // value;
    private void elimanateColumn(int j, int pivotRow) {
        Fraction pivotValue = getElement(pivotRow, j);
        for (int i = 0; i < this.getNumRows(); i++) {
            if (i == pivotRow) {
                continue;
            }
            Fraction valueToEliminate = getElement(i, j);
            if (valueToEliminate.getNumerator() == 0) {
                continue;
            }
            Fraction multiplier = new Fraction(-valueToEliminate.getNumerator(),
                    valueToEliminate.getDenominator());
            addRowMultiple(pivotRow, i, multiplier);
        }
    }

    // EFFECTS: returns a copy of this matrix
    public Matrix copy() {
        Matrix copy = new Matrix(this.rows, this.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy.setElement(i, j, this.getElement(i, j));
            }
        }
        return copy;
    }

    // EFFECTS creates and returns an augmented Matrix version of this
    public Matrix augment(Vector b) {
        Matrix augmented = new Matrix(this.rows, this.cols + 1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                augmented.setElement(i, j, this.getElement(i, j));
            }
            augmented.setElement(i, this.cols, b.getComponent(i));
        }
        return augmented;
    }

}