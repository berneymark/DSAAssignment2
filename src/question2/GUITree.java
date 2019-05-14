package question2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUITree {
	private BinarySearchTree bst;
	
	private JFrame mainFrame;
	private JPanel mainPanel, drawPanel, controlPanel;
	private JButton addButton, removeButton;
	private JButton leftRotationButton, rightRotationButton;
	private JButton levelOrderButton, inOrderButton;
	
	public GUITree() {
		bst = new BinarySearchTree();
		GUIInit();
		setDrawPanel();
		setControlPanel();
		mainFrame.setVisible(true);
	}
	
	public void GUIInit() {
		mainFrame = new JFrame("GUI Tree");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane();
        mainFrame.setPreferredSize(new Dimension(600, 600));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        mainFrame.pack();
        mainFrame.setLocation(new Point((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
	}
	
	public void setDrawPanel() {
		drawPanel = new JPanel();
		drawPanel.setBackground(Color.YELLOW);
		
		mainPanel.add(drawPanel, BorderLayout.CENTER);
	}
	
	public void setControlPanel() {
		controlPanel = new JPanel();
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListeners());
		controlPanel.add(addButton);
		
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListeners());
		controlPanel.add(removeButton);
		
		leftRotationButton = new JButton("Left Rotation");
		leftRotationButton.addActionListener(new ActionListeners());
		controlPanel.add(leftRotationButton);
		
		rightRotationButton = new JButton("Right Rotation");
		rightRotationButton.addActionListener(new ActionListeners());
		controlPanel.add(rightRotationButton);
		
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
	}
	
	public class ActionListeners implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addButton) {
				String input = JOptionPane.showInputDialog("Add Text");
				
				if (input != null) {
					JOptionPane.showMessageDialog(null, input);

					bst.add(input);
					bst.drawTree(g, 600);
				} else if (input.equals("")) {
					JOptionPane.showMessageDialog(null, "You have submitted empty text.");
				} else {
					JOptionPane.showMessageDialog(null, "You have cancelled submission.");
				}
			} else if (e.getSource() == removeButton) {
				JOptionPane.showMessageDialog(null, "You have removed node.");
			} else if (e.getSource() == leftRotationButton) {
				bst.leftRotation();
			} else if (e.getSource() == rightRotationButton) {
				bst.rightRotation();
			}
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUITree main = new GUITree();
	}
}
