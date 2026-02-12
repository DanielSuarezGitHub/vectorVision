package model;

public class AddRowMultiple implements LogElement {
    private Fraction DeterminantEffect = new Fraction(1, 1);
    private String operationString;

    public AddRowMultiple(int sourceRow, int destRow, Fraction scalar) {
        this.operationString = (scalar.toString() + " * R" + sourceRow + " + R" + destRow + " -> R" + destRow);
    }

    public Fraction getDeterminantEffect() {
        return this.DeterminantEffect;
    }
    
    public String getString() {
        return this.operationString;
    }
    
}
