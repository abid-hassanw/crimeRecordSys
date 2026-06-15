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

        panel.add(new JLabel("Status:"));
        statusField = new JTextField();
        panel.add(statusField);

        JButton addBtn = new JButton("Add Record");
        JButton searchBtn = new JButton("Search Record");

        panel.add(addBtn);
        panel.add(searchBtn);

        add(panel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addBtn.addActionListener(e -> addRecord());

        searchBtn.addActionListener(e -> searchRecord());

        setVisible(true);
    }

    private void addRecord() {

        try {

            int id = Integer.parseInt(idField.getText());

            if (crimeMap.containsKey(id)) {
                JOptionPane.showMessageDialog(this, "Crime ID already exists!");
                return;
            }

            String type = typeField.getText();
            String suspect = suspectField.getText();
            String status = statusField.getText();

            CrimeRecord record =
                    new CrimeRecord(id, type, suspect, status);

            crimeMap.put(id, record);

            outputArea.append("Added: " + record + "\n");

            idField.setText("");
            typeField.setText("");
            suspectField.setText("");
            statusField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void searchRecord() {

        try {

            int id = Integer.parseInt(
                    JOptionPane.showInputDialog("Enter Crime ID"));

            CrimeRecord record = crimeMap.get(id);

            if (record != null)
                outputArea.append("Found: " + record + "\n");
            else
                outputArea.append("Record Not Found\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID!");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new investigationrecordsystemgui());
    }
}


