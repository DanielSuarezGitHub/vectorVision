package ui.gui;

import javax.swing.*;

import model.Fraction;
import model.Matrix;
import model.Vector;
import model.Workspace;

import java.awt.*;
import java.awt.geom.Line2D;

// Represents a Cartesian plane that can draw vectors and have matrix transforms applied to it.
public class TransformPlane extends JPanel {

    private Workspace workspace;
    private Matrix activeTransform; 
    
    private final int scale = 30; 
    private final int gridlimit = 10; 

    // EFFECTS: initializes the drawing panel, sets the background color and border, 
    // and adds workspace as a field.
    public TransformPlane(Workspace ws) {
        this.workspace = ws;
        setBackground(new Color(255, 255, 224)); 
        setBorder(BorderFactory.createTitledBorder("Cartesian Plane"));
    }


    @Override
    @SuppressWarnings("methodlength")
    // MODIFIES: g
    // EFFECTS: clears the previous frame and renders the updated coordinate system. 
    //  Draws the standard grid and axes. If a matrix is active, overlays 
    // the warped grid. Finally, draws all vectors in the workspace 
    // showing original shadows and red transformed vectors if applicable.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2.translate(centerX, centerY);
        g2.scale(1, -1);
        
        g2.setColor(new Color(230, 230, 230));
        g2.setStroke(new BasicStroke(1.0f));
        for (int i = -gridlimit; i <= gridlimit; i++) {
            g2.draw(new Line2D.Double(i * scale, -gridlimit * scale, i * scale, gridlimit * scale));
            g2.draw(new Line2D.Double(-gridlimit * scale, i * scale, gridlimit * scale, i * scale));
        }

        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(-centerX, 0, centerX, 0)); 
        g2.draw(new Line2D.Double(0, -centerY, 0, centerY)); 

        if (activeTransform != null) {
            g2.setColor(new Color(150, 200, 255, 120)); 
            g2.setStroke(new BasicStroke(1.5f));
            for (int i = -gridlimit; i <= gridlimit; i++) {
                drawTransformedGridLine(g2, activeTransform, i, -gridlimit, i, gridlimit);
                drawTransformedGridLine(g2, activeTransform, -gridlimit, i, gridlimit, i);
            }
        }

        for (Vector v : workspace.getVectors().values()) {
            double vx = v.getComponent(0).toDecimal() * scale;
            double vy = v.getComponent(1).toDecimal() * scale;

            if (activeTransform != null) {
                drawArrow(g2, vx, vy, new Color(180, 180, 180, 150), 2.0f);
                
                Vector transformed = activeTransform.multiplyVector(v);
                double tx = transformed.getComponent(0).toDecimal() * scale;
                double ty = transformed.getComponent(1).toDecimal() * scale;
                drawArrow(g2, tx, ty, new Color(220, 50, 50), 4.0f); 
            } else {
                drawArrow(g2, vx, vy, new Color(50, 100, 220), 4.0f); 
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Sets an active transform to the plane and repaints
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

        double tx1 = tv1.getComponent(0).toDecimal() * scale;
        double ty1 = tv1.getComponent(1).toDecimal() * scale;
        double tx2 = tv2.getComponent(0).toDecimal() * scale;
        double ty2 = tv2.getComponent(1).toDecimal() * scale;

        g2.draw(new Line2D.Double(tx1, ty1, tx2, ty2));
    }


    // MODIFIES: g2
    // EFFECTS: draws a vector line from the origin to the specified scaled (x,y) coordinates 
    private void drawArrow(Graphics2D g2, double x, double y, Color color, float thickness) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.draw(new Line2D.Double(0, 0, x, y));
       
    }    
}