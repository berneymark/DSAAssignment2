package question3;

import question2.GUITree;

import javax.swing.*;
import java.awt.*;

public class VetGUI {
    public VetGUI() {
        GUIInit();
    }

    public void GUIInit() {
        JFrame frame = new JFrame("Mark's Vet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.setPreferredSize(new Dimension(600, 600));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        frame.pack();

        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        VetGUI main = new VetGUI();
    }
}
