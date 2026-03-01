package model;

// Represents a log of the row operation describing swapping two rows.
public class SwapRow implements LogElement {
    private Fraction determinantEffect = new Fraction(-1, 1);
    private String operationString;

    // EFFECTS: constructs a log element representing swapping r1 and r2
    public SwapRow(int r1, int r2) {
        operationString = ("R" + r1 + " <-> " + "R" + r2);
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
