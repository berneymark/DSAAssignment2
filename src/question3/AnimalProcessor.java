package question3;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AnimalProcessor {
    final private String WIN_XML_PATH = "C:\\Users\\berne\\IdeaProjects\\DSAAssignment2\\src\\question3\\animals.xml";
    final private String MAC_XML_PATH = "";
    private LinkedList<AnimalPatient> waitlist = new LinkedList<>();

    public void addAnimal(AnimalPatient animal) {
        waitlist.addFirst(animal);
    }

    public AnimalPatient getNextAnimal() {
        return waitlist.getFirst();
    }

    public AnimalPatient releaseAnimal() {
        return waitlist.removeFirst();
    }

    public LinkedList<AnimalPatient> getWaitlist() {
        return waitlist;
    }

    public int animalsLeftToProcess() {
        return waitlist.size();
    }

    public void loadAnimalsFromXML(Document document) {
        String name;
        String species;

        int day, month, year;
        int time, hours, minutes;
        Date lastVisited;

        try {
            Node rootElement = document.getFirstChild();

            if (rootElement != null) {
                NodeList children = document.getElementsByTagName("animal");

                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);

                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        Element animalElement = (Element)child;

                        NodeList nameList = animalElement.getElementsByTagName("name");
                        NodeList textNameList = ((Element)nameList.item(0)).getChildNodes();
                        name = (textNameList.item(0).getNodeValue().trim());

                        NodeList speciesList = animalElement.getElementsByTagName("species");
                        NodeList textSpeciesList = ((Element)speciesList.item(0)).getChildNodes();
                        species = textSpeciesList.item(0).getNodeValue().trim();

                        NodeList dayList = animalElement.getElementsByTagName("day");
                        NodeList textDayList = ((Element)dayList.item(0)).getChildNodes();
                        day = Integer.parseInt(textDayList.item(0).getNodeValue().trim());

                        NodeList monthList = animalElement.getElementsByTagName("month");
                        NodeList textMonthList = ((Element)monthList.item(0)).getChildNodes();
                        month = Integer.parseInt(textMonthList.item(0).getNodeValue().trim()) - 1;

                        NodeList yearList = animalElement.getElementsByTagName("year");
                        NodeList textYearList = ((Element)yearList.item(0)).getChildNodes();
                        year = Integer.parseInt(textYearList.item(0).getNodeValue().trim()) - 1900;

                        NodeList timeList = animalElement.getElementsByTagName("time");
                        NodeList textTimeList = ((Element)timeList.item(0)).getChildNodes();
                        time = Integer.parseInt(textTimeList.item(0).getNodeValue().trim());
                        hours = time / 100;
                        minutes = time % 100;
                        lastVisited = new Date(year, month, day, hours, minutes);

                        waitlist.addLast(new AnimalPatient(species, name, lastVisited));
                        waitlist.getLast().setPriority(5);
                    }
                }
            } else System.out.println("ROOT = NULL");
        } catch (NegativeArraySizeException e) {
            e.getMessage();
        }
    }

    public void loadDocument() {
        File vetPatients = new File(WIN_XML_PATH);

        try {
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docBuildFactory.newDocumentBuilder();
            Document patientFiles = docBuild.parse(vetPatients);
            patientFiles.getDocumentElement().normalize();
            loadAnimalsFromXML(patientFiles);
        } catch(IOException | SAXException | ParserConfigurationException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        AnimalProcessor ap = new AnimalProcessor();
        ap.loadDocument();

        System.out.println(ap.waitlist);
    }
}
