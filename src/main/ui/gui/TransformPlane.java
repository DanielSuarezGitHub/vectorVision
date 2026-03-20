package ui.gui;

import javax.swing.*;

import model.Matrix;

import java.awt.*;
import java.awt.geom.Line2D;

public class TransformPlane extends JPanel {

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
        g2.setColor(Color.LIGHT_GRAY);
        g2.draw(new Line2D.Double(-centerX, 0, centerX, 0)); 
        g2.draw(new Line2D.Double(0, -centerY, 0, centerY)); 
    }

    public void setActiveTransform(Matrix m) {

    }

    public void drawTransformedGridLine() {

    }

    private void drawArrow() {
        
    }
}