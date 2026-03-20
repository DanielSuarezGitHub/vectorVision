package ui.gui;

import javax.swing.*;

import model.Workspace;

import java.awt.*;

// Represents a UI panel that displays a visual list of all matrices and vectors currently stored in a workspace
public class WorkspaceListPanel extends JPanel {
    private Workspace workspace;

    private DefaultListModel<String> listModel;
    private JList<String> workspaceList;

    // MODIFIES: this
    // EFFECTS: constructs the list panel, initializes the list model and JList,
    // wraps the list in a scroll pane, and populates it with workspace data;
    public WorkspaceListPanel(Workspace ws) {
        this.workspace = ws;
        setBackground(new Color(173, 216, 230));
        setPreferredSize(new Dimension(200, 0));
        setBorder(BorderFactory.createTitledBorder("Workspace List"));

        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();

        workspaceList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(workspaceList);
        add(scrollPane, BorderLayout.CENTER);
        refreshList();
    }
    
    // MODIFIES: this
    // EFFECTS: clears the current visual list model and entirely reconstructs it by 
    // pulling the current matrix and vector keys from the workspace
    public void refreshList() {
        listModel.clear(); 
        
        for (String matrixName : workspace.getMatrices().keySet()) {
            listModel.addElement(matrixName + " (Matrix)");
        }
        
        for (String vectorName : workspace.getVectors().keySet()) {
            listModel.addElement(vectorName + " (Vector)");
        }
    }
}