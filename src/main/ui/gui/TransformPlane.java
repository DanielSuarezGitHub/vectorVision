package ui.gui;

import javax.swing.*;

import model.Fraction;
import model.Matrix;
import model.Vector;
import model.Workspace;

import java.awt.*;
import java.awt.geom.Line2D;

public class TransformPlane extends JPanel {

    private Workspace workspace;
    private Matrix activeTransform; 
    
    private final int SCALE = 30; 
    private final int GRID_LIMIT = 10; 

    public TransformPlane() {
        setBackground(new Color(255, 255, 224)); 
        setBorder(BorderFactory.createTitledBorder("Cartesian Plane"));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2.translate(centerX, centerY);
        g2.scale(1, -1);
        
        g2.setColor(new Color(230, 230, 230));
        g2.setStroke(new BasicStroke(1.0f));
        for (int i = -GRID_LIMIT; i <= GRID_LIMIT; i++) {
            g2.draw(new Line2D.Double(i * SCALE, -GRID_LIMIT * SCALE, i * SCALE, GRID_LIMIT * SCALE));
            g2.draw(new Line2D.Double(-GRID_LIMIT * SCALE, i * SCALE, GRID_LIMIT * SCALE, i * SCALE));
        }

        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(-centerX, 0, centerX, 0)); 
        g2.draw(new Line2D.Double(0, -centerY, 0, centerY)); 

        if (activeTransform != null) {
            g2.setColor(new Color(150, 200, 255, 120)); 
            g2.setStroke(new BasicStroke(1.5f));
            for (int i = -GRID_LIMIT; i <= GRID_LIMIT; i++) {
                drawTransformedGridLine(g2, activeTransform, i, -GRID_LIMIT, i, GRID_LIMIT);
                drawTransformedGridLine(g2, activeTransform, -GRID_LIMIT, i, GRID_LIMIT, i);
            }
        }


    }

    public void setActiveTransform(Matrix m) {
        this.activeTransform = m;
        repaint();
    }

    // MODIFIES: g2
    // EFFECTS: converts math coordinates to Vectors, applies the matrix transformation, 
    //          and draws the resulting warped line segment on the screen
    public void drawTransformedGridLine(Graphics2D g2, Matrix m, int mathX1, int mathY1, int mathX2, int mathY2) {
        Fraction[] comps1 = {new Fraction(mathX1, 1), new Fraction(mathY1, 1)};
        Fraction[] comps2 = {new Fraction(mathX2, 1), new Fraction(mathY2, 1)};

        Vector tv1 = m.multiplyVector(new Vector(comps1));
        Vector tv2 = m.multiplyVector(new Vector(comps2));

        double tx1 = tv1.getComponent(0).toDecimal() * SCALE;
        double ty1 = tv1.getComponent(1).toDecimal() * SCALE;
        double tx2 = tv2.getComponent(0).toDecimal() * SCALE;
        double ty2 = tv2.getComponent(1).toDecimal() * SCALE;

        g2.draw(new Line2D.Double(tx1, ty1, tx2, ty2));
    }

    private void drawArrow() {

    }


    // EFFECTS Temporary Test Functiop for Transform plane
    public static void main(String[] args) {
        JFrame testFrame = new JFrame("Grid Warp Test");
        testFrame.setSize(600, 600);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TransformPlane plane = new TransformPlane();
        Matrix testMatrix = new Matrix(2, 2);
        testMatrix.setElement(0, 0, new Fraction(1, 1));
        testMatrix.setElement(0, 1, new Fraction(1, 1));
        testMatrix.setElement(1, 0, new Fraction(0, 1));
        testMatrix.setElement(1, 1, new Fraction(1, 1));

        plane.setActiveTransform(testMatrix);
        testFrame.add(plane);
        testFrame.setVisible(true);
    }
    
}