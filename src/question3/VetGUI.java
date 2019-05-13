package question3;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class VetGUI {
    final private AnimalProcessor processor = new AnimalProcessor();

    private JFrame mainFrame;
    private JPanel mainPanel, controlPanel, drawPanel;
    private JButton newPatient, seeLater, release, loadXML, saveXML, updatePic;

    private CardLayout cl = new CardLayout();
    private JLabel noPatients = new JLabel("No Patients");

    public VetGUI() {
        GUIInit();
        setDrawPanel();

        setControlPanel();
        mainFrame.setVisible(true);
    }

    public void GUIInit() {
        mainFrame = new JFrame("Mark's Vet");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane();
        mainFrame.setPreferredSize(new Dimension(630, 600));

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
        drawPanel.setLayout(cl);

        if (drawPanel.getComponentCount() == 0)
            drawPanel.add(noPatients);

        mainPanel.add(drawPanel, BorderLayout.CENTER);
    }

    public void setControlPanel() {
        controlPanel = new JPanel();

        newPatient = new JButton("New Patient");
        newPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newP = new JFrame("Add Patient");
                newP.getContentPane();
                newP.setSize(new Dimension(300, 350));

                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dimension = toolkit.getScreenSize();
                int screenHeight = dimension.height;
                int screenWidth = dimension.width;
                newP.setLocation(new Point((screenWidth / 2) - (newP.getWidth() / 2), (screenHeight / 2) - (newP.getHeight() / 2)));

                JPanel addPatientPanel = new JPanel();
                addPatientPanel.setLayout(new BoxLayout(addPatientPanel, BoxLayout.Y_AXIS));
                newP.add(addPatientPanel);

                JPanel flowNamePanel = new JPanel(new FlowLayout());
                JLabel nameLabel = new JLabel("Name : ");
                flowNamePanel.add(nameLabel);
                JTextField newName = new JTextField();
                newName.setPreferredSize(new Dimension(200, 25));
                flowNamePanel.add(newName);
                addPatientPanel.add(flowNamePanel);

                JPanel flowSpeciesPanel = new JPanel(new FlowLayout());
                JLabel speciesLabel = new JLabel("Species : ");
                JTextField newSpecies = new JTextField();
                newSpecies.setPreferredSize(new Dimension(180, 25));
                flowSpeciesPanel.add(speciesLabel);
                flowSpeciesPanel.add(newSpecies);
                addPatientPanel.add(flowSpeciesPanel);

                JPanel flowDatesPanel = new JPanel(new FlowLayout());

                Integer[] possibleDates = new Integer[31];
                for (int i = 0; i < possibleDates.length; i++)
                    possibleDates[i] = i + 1;
                Integer[] possibleMonths = new Integer[12];
                for (int i = 0; i < possibleMonths.length; i++)
                    possibleMonths[i] = i + 1;
                Integer[] possibleYears = new Integer[20];
                for (int i = 0; i < possibleYears.length; i++)
                    possibleYears[i] = i + Calendar.getInstance().get(Calendar.YEAR);

                JLabel dateLabel = new JLabel("Date : ");
                flowDatesPanel.add(dateLabel);

                JComboBox datePicker = new JComboBox(possibleDates);
                datePicker.setEditable(true);
                datePicker.setPreferredSize(new Dimension(70, 25));
                datePicker.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE));
                datePicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                flowDatesPanel.add(datePicker);

                JComboBox monthPicker = new JComboBox(possibleMonths);
                monthPicker.setEditable(true);
                monthPicker.setPreferredSize(new Dimension(70, 25));
                monthPicker.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
                monthPicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                flowDatesPanel.add(monthPicker);

                JComboBox yearPicker = new JComboBox(possibleYears);
                yearPicker.setEditable(true);
                yearPicker.setPreferredSize(new Dimension(70, 25));
                yearPicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                flowDatesPanel.add(yearPicker);
                addPatientPanel.add(flowDatesPanel);

                JPanel flowTimePanel = new JPanel(new FlowLayout());
                Integer[] possibleHours = new Integer[24];
                for (int i = 0; i < possibleHours.length; i++)
                    possibleHours[i] = i;
                Integer[] possibleMinutes = new Integer[60];
                for (int i = 0; i < possibleMinutes.length; i++)
                    possibleMinutes[i] = i;
                JComboBox hoursPicker = new JComboBox(possibleHours);
                hoursPicker.setEditable(true);
                hoursPicker.setPreferredSize(new Dimension(70, 25));
                hoursPicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                JComboBox minsPicker = new JComboBox(possibleMinutes);
                minsPicker.setEditable(true);
                minsPicker.setPreferredSize(new Dimension(70, 25));
                minsPicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                flowTimePanel.add(new JLabel("Hours"));
                flowTimePanel.add(hoursPicker);
                flowTimePanel.add(new JLabel("Minutes"));
                flowTimePanel.add(minsPicker);
                addPatientPanel.add(flowTimePanel);

                JPanel flowPriorityPanel = new JPanel(new FlowLayout());
                Integer[] possiblePriorities = new Integer[10];
                for (int i = 0; i < possiblePriorities.length; i++)
                    possiblePriorities[i] = i + 1;
                JComboBox priorityPicker = new JComboBox(possiblePriorities);
                priorityPicker.setEditable(true);
                priorityPicker.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                flowPriorityPanel.add(new JLabel("Priority : "));
                flowPriorityPanel.add(priorityPicker);
                addPatientPanel.add(flowPriorityPanel);

                JButton add = new JButton("Add");
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date lastVisited = new Date((int)yearPicker.getSelectedItem() - 1900, (int)monthPicker.getSelectedItem() - 1, (int)datePicker.getSelectedItem(), (int)hoursPicker.getSelectedItem(), (int)minsPicker.getSelectedItem());
                        AnimalPatient addedAnimal = new AnimalPatient(newSpecies.getText(), newName.getText(), lastVisited);
                        addedAnimal.setPriority((int)priorityPicker.getSelectedItem());
                        processor.addAnimal(addedAnimal);

                        if (processor.getWaitlist().size() > 0) {
                            System.out.println(processor.getAnimal(0).getName());

                            drawPanel.remove(noPatients);

                            for (int i = 0; i < processor.getWaitlist().size(); i++) {
                                drawPanel.add(processor.getAnimal(i).getDisplayPanel(), processor.getAnimal(i).getName());
                            }
                        }

                        cl.first(drawPanel);
                    }
                });
                addPatientPanel.add(add);



                newP.setVisible(true);
            }
        });
        controlPanel.add(newPatient);

        seeLater = new JButton("See Later");
        seeLater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (processor.getWaitlist().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Waiting list is already empty.");
                } else if (processor.getWaitlist().size() == 1) {
                    JOptionPane.showMessageDialog(null, "You only have one patient. No need to put off.");
                } else {
                    AnimalPatient putOff = processor.getAnimal(0);
                    processor.releaseAnimal();
                    processor.addAnimal(putOff);

                    drawPanel.removeAll();
                    for (int i = 0; i < processor.getWaitlist().size(); i++) {
                        drawPanel.add(processor.getAnimal(i).getDisplayPanel(), processor.getAnimal(i).getName());
                    }
                }
            }
        });
        controlPanel.add(seeLater);

        release = new JButton("Release");
        release.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (processor.getWaitlist().size() > 1) {
                    processor.releaseAnimal();

                    drawPanel.removeAll();
                    for (int i = 0; i < processor.getWaitlist().size(); i++) {
                        drawPanel.add(processor.getAnimal(i).getDisplayPanel(), processor.getAnimal(i).getName());
                    }

                    cl.first(drawPanel);

                } else if (processor.getWaitlist().size() == 1) {
                    processor.releaseAnimal();
                    drawPanel.removeAll();
                    drawPanel.add(noPatients);
                    cl.first(drawPanel);
                } else if (processor.getWaitlist().size() == 0) JOptionPane.showMessageDialog(null, "Waiting list is already empty.");
            }
        });
        controlPanel.add(release);

        loadXML = new JButton("Load XML");
        loadXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    processor.loadDocument(file.getAbsolutePath());

                    if (processor.getWaitlist().size() > 0) {
                        System.out.println(processor.getAnimal(0).getName());

                        drawPanel.remove(noPatients);

                        for (int i = 0; i < processor.getWaitlist().size(); i++) {
                            drawPanel.add(processor.getAnimal(i).getDisplayPanel(), processor.getAnimal(i).getName());
                        }
                    }

                    cl.first(drawPanel);
                }
            }
        });
        controlPanel.add(loadXML);

        saveXML = new JButton("Save XML");
        saveXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        controlPanel.add(saveXML);

        updatePic = new JButton("Update Picture");
        updatePic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    processor.getAnimal(0).loadImage(file.getAbsolutePath());
                }
            }
        });
        controlPanel.add(updatePic);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    public void generateXML() throws JAXBException, IOException {
        ObjectFactory factory = new ObjectFactory();
        String name = factory.
        File newFile = new File("patientList.xml");
    }

    public static void main(String[] args) {
        VetGUI main = new VetGUI();
    }
}
