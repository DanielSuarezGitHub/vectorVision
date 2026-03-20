package ui.gui;

import javax.swing.*;
import java.awt.*;

import model.Fraction;
import model.Matrix;
import model.Vector;
import model.Workspace;

// Represents the data entry UI panel where users can input parameters to create new vectors and matrices
public class InputPanel extends JPanel {
    
    private Workspace workspace;
    private WorkspaceListPanel listPanel;

    // EFFECTS: constructs the input panel, initializes text fields and buttons, 
    //          and binds the action listeners to the provided workspace and list panel
    public InputPanel(Workspace ws, WorkspaceListPanel listPanel) {
        this.workspace = ws;
        this.listPanel = listPanel;

        setBackground(new Color(144, 238, 144)); 
        setPreferredSize(new Dimension(0, 150));
        setBorder(BorderFactory.createTitledBorder("Inputs"));
        setLayout(new GridLayout(1, 2));

        JPanel vectorPanel = buildVectorPanel();
        JPanel matrixPanel = buildMatrixPanel();

        add(vectorPanel);
        add(matrixPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates and returns the UI sub-panel containing vector input fields and its submit button
    private JPanel buildVectorPanel() {
        JPanel vectorPanel = new JPanel();
        vectorPanel.setBorder(BorderFactory.createTitledBorder("New Vector"));
        
        JTextField xField = new JTextField("1");
        JTextField yField = new JTextField("1");
        JButton btnAddVector = new JButton("Add Vector");

        btnAddVector.addActionListener(e -> {
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());
            
            Fraction[] comps = { new Fraction(x, 1), new Fraction(y, 1) };
            Vector v = new Vector(comps);
            
            String name = "V" + (workspace.getVectors().size() + 1);
            workspace.addVector(name, v);
            listPanel.refreshList(); 
        });

        vectorPanel.add(new JLabel("X:"));
        vectorPanel.add(xField);
        vectorPanel.add(new JLabel("Y:"));
        vectorPanel.add(yField);
        vectorPanel.add(btnAddVector);

        return vectorPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns the UI subpanel containing a 2x2 grid of matrix input fields and its submit button
    private JPanel buildMatrixPanel() {
        JPanel matrixPanel = new JPanel();
        matrixPanel.setBorder(BorderFactory.createTitledBorder("New 2x2 Matrix"));
        
        JTextField m00 = new JTextField("1"); 
        JTextField m01 = new JTextField("0");
        JTextField m10 = new JTextField("0"); 
        JTextField m11 = new JTextField("1");

        JPanel matrixGrid = new JPanel(new GridLayout(2, 2));
        matrixGrid.add(m00); matrixGrid.add(m01);
        matrixGrid.add(m10); matrixGrid.add(m11);

        JButton btnAddMatrix = new JButton("Add Matrix");

        btnAddMatrix.addActionListener(e -> {
            Matrix m = new Matrix(2, 2);
            m.setElement(0, 0, new Fraction(Integer.parseInt(m00.getText().trim()), 1));
            m.setElement(0, 1, new Fraction(Integer.parseInt(m01.getText().trim()), 1));
            m.setElement(1, 0, new Fraction(Integer.parseInt(m10.getText().trim()), 1));
            m.setElement(1, 1, new Fraction(Integer.parseInt(m11.getText().trim()), 1));
            
            String name = "M" + (workspace.getMatrices().size() + 1);
            workspace.addMatrix(name, m);
            listPanel.refreshList(); 
        });

        matrixPanel.add(matrixGrid);
        matrixPanel.add(btnAddMatrix);

        return matrixPanel;
    }
}