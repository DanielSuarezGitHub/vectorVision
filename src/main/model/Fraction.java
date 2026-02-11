package model;

public class Fraction {
    private long numerator;
    private long denominator;

    // Requires: Denominator is not equal to 0
    //EFFECTS: constructs a fraction with given numerator and denominator fraction is reduced to simplest form.
    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Requires f2 is not null
    // EFFECTS: Returns a new fraction as the sum of this and a new fraction;
    public Fraction add(Fraction f2) {
        return new Fraction(1, 1);
    }
    // EFFECTS: Returns a new fraction as the difference of this and a new fraction;
    public Fraction subtract(Fraction f2) {
        return new Fraction(1, 1);
    }

    // EFFECTS: Returns a new fraction as the product of this and a new fraction;
    public Fraction multiply(Fraction f2) {
        return new Fraction(1, 1);
    }
    
    // Requires: F2 is not equal to zero;
    // EFFECTS: Returns a new fraction as the qoutient of this and a new fraction;
    public Fraction divide(Fraction f2) {
        return new Fraction(1, 1);
    }

    // MODIFIES: THIS
    // EFFECTS: simplifies the fraction (e.g. 2/4 -> 1/2) and ensures denominator is positive
    public void simplify() {
        
    }
    // REQUIRES: a >= 0, B >= 0
    // EFFECTS: computes the GCD of a b;
    private long gcd(long a, long b) {
        return 5;
    }

    // EFFECTS: returns the Numerator of this fraction;
    public long getNumerator() {
        return this.numerator;
    }

    // EFFECTS: returns the Denominator of this fraction;
    public long getDenominator() {
        return this.denominator;
    }

    // EFFECTS returns a decimal version of this;
    public double toDecimal() {
        return 1.0;
    }
    // EFFECTS: returns true if this fraction and f2 represent the same value
    public Boolean equals(Fraction f2) {
        return true;
    }

   // EFFECTS: returns a string representation of the fraction (e.g., "1/2")
    public String toString() {
        return "1/1";
    }
}