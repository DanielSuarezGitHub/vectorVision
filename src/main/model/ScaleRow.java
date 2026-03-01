package model;

// Represents a log of the row operation describing a scale of a row.
public class ScaleRow implements LogElement {
    private Fraction determinantEffect;
    private String operationString;

    // EFFECTS: constructs a log element representing scaling row by scalar
    public ScaleRow(int row, Fraction scalar) {
        operationString = (scalar.toString() + " * R" + row + " -> R" + row);
        this.determinantEffect = scalar;
    }

    // EFFECTS: returns the multiplicative effect this operation has on the
    // determinant
    public Fraction getDeterminantEffect() {
        return this.determinantEffect;
    }

    // EFFECTS: returns the formatted string description of the operation
    public String getString() {
        return this.operationString;
    }

}
