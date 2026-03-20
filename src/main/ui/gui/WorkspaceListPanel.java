package ui.gui;

import javax.swing.*;

import model.Workspace;

import java.awt.*;

public class WorkspaceListPanel extends JPanel {
    private Workspace workspace;

    private DefaultListModel<String> listModel;
    private JList<String> workspaceList;

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