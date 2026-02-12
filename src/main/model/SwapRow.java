package model;

public class SwapRow implements LogElement {
    private Fraction DeterminantEffect = new Fraction(-1, 1);
    private String operationString;

    public SwapRow(int r1, int r2) {
        operationString = ("R" + r1 + " <-> " + "R" + r2);
    }

    public Fraction getDeterminantEffect() {
        return this.DeterminantEffect;
    }
    
    public String getString() {
        return this.operationString;
    }
}
