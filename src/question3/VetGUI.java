package question3;

import question2.GUITree;

import javax.swing.*;
import java.awt.*;

public class VetGUI {
    final private AnimalProcessor processor = new AnimalProcessor();

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel listPanel;
    private JList patientList;

    private DefaultListModel animalPatients;

    public VetGUI() {
        GUIInit();

        processor.loadDocument();
        animalPatients = new DefaultListModel();
        for (int i = 0; i < processor.getWaitlist().size(); i++) {
            animalPatients.add(i, processor.getWaitlist().get(i).getName());
        }

        UIDesign();

        mainFrame.setVisible(true);
    }

    public void GUIInit() {
        mainFrame = new JFrame("Mark's Vet");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane();
        mainFrame.setPreferredSize(new Dimension(800, 600));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        mainFrame.pack();

        mainFrame.setLocation(new Point((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)));
    }

    public void UIDesign() {
        mainPanel = new JPanel(new BorderLayout());
        mainFrame.add(mainPanel);

        listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(200, 600));
        listPanel.setBackground(Color.CYAN);
        listPanel.setLayout(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.WEST);

        patientList = new JList(animalPatients);
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setLayoutOrientation(JList.VERTICAL);
        patientList.setVisibleRowCount(-1);
        listPanel.add(patientList, BorderLayout.CENTER);
    }

    public void checkPatientList(AnimalProcessor processor) {
        if (processor != null) {
            processor.loadDocument();

            if (processor.getWaitlist() != null) {

            }
        }
    }

    public static void main(String[] args) {
        VetGUI main = new VetGUI();
    }
}
