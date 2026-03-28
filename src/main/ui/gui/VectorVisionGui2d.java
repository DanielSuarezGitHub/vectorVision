package ui.gui;

import javax.swing.*;
import model.Workspace;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Event;
import model.EventLog;

// Represents the main application window that ties the data model and UI panels together
public class VectorVisionGui2d extends JFrame {
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workspace2d.json";
    private Workspace myWorkspace;
    
    // EFFECTS: constructs the main application window, initializes the central
    // workspace session,
    // and arranges the subpanels using a BorderLayout
    public VectorVisionGui2d() {
        super("VectorVision");
        myWorkspace = new Workspace("My Session");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        
        loadWorkspacePrompt();

        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TransformPlane plane = new TransformPlane(myWorkspace);
        WorkspaceListPanel listPanel = new WorkspaceListPanel(myWorkspace, plane);
        InputPanel inputPanel = new InputPanel(myWorkspace, listPanel, plane);

        add(listPanel, BorderLayout.WEST);
        add(plane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setupSaveOnClose();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to load data. If yes, reads from file. Otherwise, creates a new session.
    private void loadWorkspacePrompt() {
        int choice = JOptionPane.showConfirmDialog(null, "Load previous VectorVision save?", 
                                                   "Load", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                myWorkspace = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE + "creating new session");
                myWorkspace = new Workspace("My Session"); 
            }
        } else {
            myWorkspace = new Workspace("My Session");
        }
    }


    // MODIFIES: this
    // EFFECTS: stops the default window close, prompts the user to save, writes to file if yes, then exits
    private void setupSaveOnClose() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Save workspace before exiting?", 
                                                           "Save", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(myWorkspace);
                        jsonWriter.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Failed to save file.");
                    }
                }

                System.out.println("Event Log: \n");
                for (Event el : EventLog.getInstance()) {
                    System.out.println(el.toString());
                }

                System.exit(0); 
            }
        });
    }

    // EFFECTS: launches the VectorVision application
    public static void main(String[] args) {
        new VectorVisionGui2d();
    }
}