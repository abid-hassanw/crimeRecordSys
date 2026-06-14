import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class crimerecord {
    int crimeId;
    String crimeType;
    String suspectName;
    String status;

    public crimerecord(int crimeId, String crimeType,
                       String suspectName, String status) {
        this.crimeId = crimeId;
        this.crimeType = crimeType;
        this.suspectName = suspectName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Crime ID: " + crimeId +
                ", Type: " + crimeType +
                ", Suspect: " + suspectName +
                ", Status: " + status;
    }
}
public class investigationrecordsystemgui extends JFrame {

    JTextField idField, typeField, suspectField, statusField;
    JTextArea outputArea;

    HashMap<Integer, CrimeRecord> crimeMap = new HashMap<>();

    public investigationrecordsystemgui() {

        setTitle("Crime Investigation Record System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Crime ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Crime Type:"));
        typeField = new JTextField();
        panel.add(typeField);

        panel.add(new JLabel("Suspect Name:"));
        suspectField = new JTextField();
        panel.add(suspectField);

    }}
