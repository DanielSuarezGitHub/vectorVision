package ui.gui;

import javax.swing.*;
import model.Workspace;

import java.awt.*;

// Represents the main application window that ties the data model and UI panels together
public class VectorVisionGui2d extends JFrame {

    // EFFECTS: constructs the main application window, initializes the central
    // workspace session,
    // and arranges the subpanels using a BorderLayout
    public VectorVisionGui2d() {
        super("VectorVision");
        Workspace myWorkspace = new Workspace("My Session");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TransformPlane plane = new TransformPlane(myWorkspace);
        WorkspaceListPanel listPanel = new WorkspaceListPanel(myWorkspace, plane);
        InputPanel inputPanel = new InputPanel(myWorkspace, listPanel, plane);

        add(listPanel, BorderLayout.WEST);
        add(plane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: launches the VectorVision application
    public static void main(String[] args) {
        new VectorVisionGui2d();
    }
}