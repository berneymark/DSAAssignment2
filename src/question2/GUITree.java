package question2;

import javax.swing.*;
import java.awt.*;

public class GUITree {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JButton add, remove;

    public GUITree() {
        GUIInit();

        mainFrame.setVisible(true);
    }

    public void GUIInit() {
        mainFrame = new JFrame("GUI Tree");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane();
        mainFrame.setPreferredSize(new Dimension(600, 600));
        mainFrame.setLayout(new BorderLayout());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        mainFrame.pack();

        mainFrame.setLocation(new Point((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)));
    }

    public void controlPanel() {
        controlPanel = new JPanel();
        //controlPanel.setLayout(BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GUITree main = new GUITree();
    }
}
