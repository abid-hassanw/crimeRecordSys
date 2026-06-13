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
