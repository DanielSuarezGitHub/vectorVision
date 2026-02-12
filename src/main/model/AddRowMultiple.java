package model;

public class AddRowMultiple implements LogElement {
    private Fraction DeterminantEffect = new Fraction(0, 1);
    private String operationString;

    public AddRowMultiple(int sourceRow, int destRow, Fraction scalar) {
        this.operationString = (scalar.toString() + " * R" + sourceRow + " + R" + destRow + " -> R" + destRow);
        this.DeterminantEffect = scalar;
    }

    public Fraction getDeterminantEffect() {
        return this.DeterminantEffect;
    }
    
    public String getString() {
        return this.operationString;
    }
    
}
