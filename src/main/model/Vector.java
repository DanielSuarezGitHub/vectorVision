package model;

public class Vector {
    private Fraction[] components;
    private int size;

    // REQUIRES: components != null, components.size() > 0;
    // EFFECTS constructs a vector with the given fraction components
    public Vector(Fraction[] components) {
        this.size = 0;
        this.components = components;
    }

    // REQUIRES: V2 != null, this.size() == v2.size()
    //EFFECTS: returns a new Vector that is the sum of this and v2
    public Vector add(Vector v2) {
        return null;
    }

    // REQUIRES: V2 != null, this.size() == v2.size()
    //EFFECTS: returns a new Vector that is the difference of this and v2
    public Vector subtract(Vector v2) {
        return null;
    }

    // REQUIRES: scalar != null
    // EFFECTS: returns a new Vector where each component is multiplied bt scalar
    public Vector scalarMultiply(Fraction scalar) {
        return null;
    }

    // REQUIRES: V2 != null, this.size() == v2.size()
    // EFFECTS: returns the dot product of this vector and v2
    public Fraction dot(Vector v2) {
        return null;
    }

    // REQUIRES: V2 != null, this.size() == v2.size() == 3
    // EFFECRS: returns the cross product of this and v2 (R3 Vectors only)
    public Vector cross(Vector v2) {
        return null;
    }

    // EFFECTS: returns the magnitude of this vector as as a double
    public double getMagnitude() {
        return 0.0;
    }

    // REQUIRES: this.getMagnitude() != 0
    // EFFECTS: returns a new Vector with a magnitude of 1 in the same direction
    public Vector normalize() {
        return null;
    }

    // REQUIRES: v2 != null, this.size() == v2.size()
    // EFFECTS: returns true if the dot product of this and v2 is 0
    public boolean isOrthogonal(Vector v2) {
        return false;
    }

    //EFFECTS: gets the number of components in the vector
    public int size() {
        return 0;
    }

    // REQUIRES index >= 0, index < this.size()
    // EFFECTS: returns the component at the specified index
    public Fraction getComponent(int index) {
        return null;
    }

    // REQUIRES: v2 != null
    // EFFECTS: returns true if this vector and the other have the same size and identical components
    public boolean equals(Vector v2) {
        return false;
    }

    // EFFECTS: returns a string representation of the fraction example "[1/2, 2/3, -1/1]"
    public String toString() {
        return "";
    }

}
