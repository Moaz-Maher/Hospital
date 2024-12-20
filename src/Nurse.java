import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;

public class Nurse {
    private void setVisibility(boolean isVisible, Component... components) {
        for (Component component : components) {
            component.setVisible(isVisible);
        }
    }

    private void removeAllActionListeners(JButton button) {
        for (var listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        }
    }

    private final Color blue = new Color(1, 50, 67), white = new Color(242, 242, 242);

    private JButton addNurse = Create.button("Add Appointment", blue, Color.WHITE, 85, 324, null);
    private JButton deleteNurse = Create.button("Make Manager", blue, Color.WHITE, 108, 432, null);
    private JButton takeCareOfRoom = Create.button("Select", blue, Color.WHITE, 156, 540, null);
    private JButton deleteTakeCare = Create.button("Supervise Nurse", blue, Color.WHITE, 97, 648, null);
    private JButton back = Create.button("Back", blue, Color.WHITE, 167, 864, null);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 864, null);
}