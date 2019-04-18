package question3;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import org.w3c.dom.Document;

public class AnimalProcessor {
    private LinkedList<AnimalPatient> waitlist;

    public AnimalProcessor() {

    }

    public void addAnimal(AnimalPatient animal) {
        waitlist.add(animal);
    }

    public AnimalPatient getNextAnimal() {
        return null;
    }

    public AnimalPatient releaseAnimal() {
        return waitlist.remove();
    }

    public int animalsLeftToProcess() {
        return 0;
    }

    public void loadAnimalsFromXML(Document document) {

    }

    public static void main(String[] args) {
        AnimalProcessor ap = new AnimalProcessor();
        Document patientFiles = null;


        try {
            File vetPatients = new File("question3/animals.xml");
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docBuildFactory.newDocumentBuilder();
            patientFiles = docBuild.parse(vetPatients);
            patientFiles.getDocumentElement ().normalize ();
        } catch(Exception e) {
            e.getMessage();
        }

        ap.loadAnimalsFromXML(patientFiles);
    }
}
