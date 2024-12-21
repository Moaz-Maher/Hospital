import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import java.time.LocalDate;

import java.awt.Color;
import java.awt.Component;

public class Manager {
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

    public Manager(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton manager, JButton exit, JPanel content, Connection connection) {
    }
}