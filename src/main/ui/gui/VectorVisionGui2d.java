package ui.gui;
import javax.swing.*;
import java.awt.*;

public class VectorVisionGui2d extends JFrame {

    public VectorVisionGui2d() {
        super("VectorVision");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new WorkspaceListPanel(), BorderLayout.WEST);
        add(new TransformPlane(), BorderLayout.CENTER);
        add(new InputPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
       new VectorVisionGui2d();
    }
}