package model;

public class ScaleRow implements LogElement {
    private Fraction DeterminantEffect;
    private String operationString;

    public ScaleRow(int row, Fraction scalar) {
       operationString = (scalar.toString() + " * R" + row + " -> R" + row);
       this.DeterminantEffect = scalar;
    }

    public Fraction getDeterminantEffect() {
        return this.DeterminantEffect;
    }
    
    public String getString() {
        return this.operationString;
    }

}
