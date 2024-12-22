import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

import java.sql.Connection;
import java.sql.SQLException;

public class Hospital {
    private JButton doctor, nurse, patient, manager, exit;
    private Color blue = new Color(1, 50, 67), white = new Color(242, 242, 242);

    public Hospital(Connection connection) {
        JFrame frame = Create.frame();

        JPanel sidebar = Create.panel(blue, 0, 420);
        frame.add(sidebar);

        JPanel content = Create.panel(white, 420, 1500);
        frame.add(content);

        doctor = Create.button("Doctor", blue, white, 152, 154, e -> {
            new Doctor(sidebar, doctor, nurse, patient, manager, exit, content, connection);
        });
        sidebar.add(doctor);

        nurse = Create.button("Nurse", blue, white, 159, 309, e -> {
            new Nurse(sidebar, doctor, nurse, patient, manager, exit, content, connection);
        });
        sidebar.add(nurse);

        patient = Create.button("Patient", blue, white, 148, 463, e -> {
            new Patient(sidebar, doctor, nurse, patient, manager, exit, content, connection);
        });
        sidebar.add(patient);

        manager = Create.button("Manager", blue, white, 148, 617, e -> {
            new Manager(sidebar, doctor, nurse, patient, manager, exit, content, connection);
        });
        sidebar.add(manager);

        exit = Create.button("Exit", blue, white, 169, 926, e -> {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        });
        sidebar.add(exit);

        frame.setVisible(true);
    }
}