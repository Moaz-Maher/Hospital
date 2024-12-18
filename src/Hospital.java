import javax.swing.*;
import java.awt.*;

public class Hospital {
    private JButton doctor, nurse, patient, exit;

    private Font buttonFont = new Font("Arial", Font.BOLD, 24);

    private Color blue = new Color(1, 50, 67);

    public Hospital() {
        JFrame frame = Create.frame(1920, 1080);

        JPanel sidebar = Create.panel(blue, 0, 0, 420, 1080);
        frame.add(sidebar);

        JPanel content = Create.panel(new Color(242, 242, 242), 420, 0, 1500, 1080);
        frame.add(content);

        doctor = Create.button("Doctor", blue, buttonFont, 91, 216, 238, 45, 3, null);
        doctor.addActionListener(e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }
            new Doctor(sidebar, doctor, nurse, patient, exit, content);
        });
        sidebar.add(doctor);

        nurse = Create.button("Nurse", blue, buttonFont, 91, 432, 238, 45, 3, null);
        nurse.addActionListener(e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }
            new Nurse(sidebar, doctor, nurse, patient, exit, content);
        });
        sidebar.add(nurse);

        patient = Create.button("Patient", blue, buttonFont, 91, 648, 238, 45, 3, null);
        patient.addActionListener(e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }
            new Patient(sidebar, doctor, nurse, patient, exit, content);
        });
        sidebar.add(patient);

        exit = Create.button("Exit", new Color(150, 150, 150), buttonFont, 91, 864, 238, 45, 3, null);
        exit.setBorder(null);
        exit.addActionListener(e -> System.exit(0));
        sidebar.add(exit);

        frame.setVisible(true);
    }
}