import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

import java.sql.Connection;

public class Hospital {
    private JButton doctor, nurse, patient, exit;
    private Color blue = new Color(1, 50, 67), white = new Color(242, 242, 242);

    public Hospital(Connection connection) {
        JFrame frame = Create.frame(1920, 1080);

        JPanel sidebar = Create.panel(blue, 0, 0, 420, 1080);
        frame.add(sidebar);

        JPanel content = Create.panel(new Color(242, 242, 242), 420, 0, 1500, 1080);
        frame.add(content);

        doctor = Create.button("Doctor", blue, white, 152, 180, e -> {
            new Doctor(sidebar, doctor, nurse, patient, exit, content, connection);
        });
        sidebar.add(doctor);

        nurse = Create.button("Nurse", blue, white, 159, 360, e -> {
            new Nurse(sidebar, doctor, nurse, patient, exit, content, connection);
        });
        sidebar.add(nurse);

        patient = Create.button("Patient", blue, white, 148, 540, e -> {
            new Patient(sidebar, doctor, nurse, patient, exit, content, connection);
        });
        sidebar.add(patient);

        exit = Create.button("Exit", blue, white, 169, 900, e -> System.exit(0));
        sidebar.add(exit);

        frame.setVisible(true);
    }
}