package ui.gui;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

    public InputPanel() {
        setBackground(new Color(144, 238, 144)); 
        setPreferredSize(new Dimension(0, 150));
        setBorder(BorderFactory.createTitledBorder("Inputs"));

        setLayout(new GridLayout(1, 2));
        JPanel vectorPanel = new JPanel();
        vectorPanel.setBorder(BorderFactory.createTitledBorder("New Vector"));
        JTextField xField = new JTextField("1");
        JTextField yField = new JTextField("1");
        JButton btnAddVector = new JButton("Add Vector");

        btnAddVector.addActionListener(e -> {
            System.out.println("Vector added: [" + xField.getText() + ", " + yField.getText() + "]");
        });
        vectorPanel.add(new JLabel("X:"));
        vectorPanel.add(xField);
        vectorPanel.add(new JLabel("Y:"));
        vectorPanel.add(yField);
        vectorPanel.add(btnAddVector);
        
        JPanel matrixPanel = new JPanel();
        matrixPanel.setBorder(BorderFactory.createTitledBorder("New 2x2 Matrix"));
        JTextField m00 = new JTextField("1");
        JTextField m01 = new JTextField("1");
        JTextField m10 = new JTextField("1");
        JTextField m11 = new JTextField("1");

        JButton btnAddMatrix = new JButton("Add Matrix");

        JPanel matrixGrid = new JPanel(new GridLayout(2, 2));
        matrixGrid.add(m00);
        matrixGrid.add(m01);
        matrixGrid.add(m10);
        matrixGrid.add(m11);

        btnAddMatrix.addActionListener(e -> {
            System.out.println("Matrix added");
        });

        matrixPanel.add(matrixGrid);
        matrixPanel.add(btnAddMatrix);

        add(vectorPanel);
        add(matrixPanel);
    }
}