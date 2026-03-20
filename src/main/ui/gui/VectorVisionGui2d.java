package ui.gui;
import javax.swing.*;

import model.Fraction;
import model.Vector;
import model.Workspace;

import java.awt.*;

public class VectorVisionGui2d extends JFrame {
    
    public VectorVisionGui2d() {
        super("VectorVision");
        Workspace myWorkspace = new Workspace("My Session");

        // TEMPORARY
         Fraction[] arr1 = { new Fraction(1, 1),
                new Fraction(2, 1) };
        Vector v1 = new Vector(arr1);
        Vector v2 = new Vector(arr1);
        myWorkspace.addVector("v1", v1);
        myWorkspace.addVector("v2", v2);
        // TEMPORARY
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new WorkspaceListPanel(myWorkspace), BorderLayout.WEST);
        add(new TransformPlane(), BorderLayout.CENTER);
        add(new InputPanel(), BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
       new VectorVisionGui2d();
    }
}