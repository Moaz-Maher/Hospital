import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Nurse {
    private JButton back;
    private Font buttonFont = new Font("Arial", Font.BOLD, 24), labelFont = new Font("Arial", Font.BOLD, 22), font = new Font("Arial", Font.BOLD, 18);
    private Color blue = new Color(1, 50, 67), gray = new Color(150, 150, 150), white = new Color(242, 242, 242);

    public Nurse(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content, Connection connection) {
        back = Create.button("Back", gray, buttonFont, 91, 864, 238, 45, 3, null);
        back.setBorder(null);
        back.addActionListener(e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctor.setVisible(true);
            nurse.setVisible(true);
            patient.setVisible(true);
            exit.setVisible(true);
        });
        sidebar.add(back);
    }
}