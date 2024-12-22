import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.SpinnerNumberModel;

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

    private JButton add = Create.button("Add nurse", blue, white, 133, 120, null);
    private JButton delete = Create.button("Delete nurse", blue, white, 118, 240, null);
    private JButton takeCareOfRoom = Create.button("Take care of room", blue, white, 82, 360, null);
    private JButton select = Create.button("Select", blue, white, 156, 480, null);
    private JButton deleteTakeCare = Create.button("Delete take care", blue, white, 93, 600, null);
    private JButton allNurses = Create.button("All nurses", blue, white, 135, 720, null);
    private JButton back = Create.button("Back", blue, white, 167, 960, null);

    private final JLabel nurseID = Create.label("Nurse ID", 902, 108);
    private final JLabel firstName = Create.label("First name", 892, 216);
    private final JLabel roomID = Create.label("Room ID", 902, 216);
    private final JLabel lastName = Create.label("Last name", 894, 324);
    private final JLabel supervisorID = Create.label("Supervisor ID", 879, 432);

    private JTextField nurseID2 = Create.textField(1095, 101);
    private JTextField firstName2 = Create.textField(1095, 209);
    private JSpinner roomID2 = Create.spinner(new SpinnerNumberModel(0, 0, 9, 1), 1095, 209);
    private JTextField lastName2 = Create.textField(1095, 317);
    private JTextField supervisorID2 = Create.textField(1095, 425);
    private JTextField message = Create.textField(970, 756);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 864, null);

    public Nurse(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton manager, JButton exit, JPanel content, Connection connection) {
        sidebar.add(add);
        sidebar.add(delete);
        sidebar.add(takeCareOfRoom);
        sidebar.add(select);
        sidebar.add(deleteTakeCare);
        sidebar.add(allNurses);
        sidebar.add(back);

        content.add(firstName);
        content.add(firstName2);
        content.add(lastName);
        content.add(lastName2);
        content.add(roomID);
        content.add(roomID2);
        content.add(nurseID);
        content.add(nurseID2);
        content.add(supervisorID);
        content.add(supervisorID2);
        message.setBorder(null);
        content.add(message);
        content.add(confirm);

        for (Component component : sidebar.getComponents()) {
            component.setVisible(false);
        }

        for (Component component : content.getComponents()) {
            component.setVisible(false);
        }

        setVisibility(true, add, delete, takeCareOfRoom, select, deleteTakeCare, allNurses, back);

        add.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, firstName, firstName2, lastName, lastName2, nurseID, nurseID2, supervisorID, supervisorID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Nurse (id, first_name, last_name, supervizor_id) VALUES (?, ?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(nurseID2.getText()));
                    add.setString(2, firstName2.getText());
                    add.setString(3, lastName2.getText());
                    add.setInt(4, Integer.parseInt(supervisorID2.getText()));
                    add.executeUpdate();

                    message.setText("The Nurse has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        delete.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, nurseID, nurseID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "DELETE FROM Nurse WHERE id = ?";
                    PreparedStatement remove = connection.prepareStatement(query);
                    remove.setInt(1, Integer.parseInt(nurseID2.getText()));
                    remove.executeUpdate();

                    message.setText("The nurse has been deleted successfully.");
                    message.setForeground(Color.RED);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        takeCareOfRoom.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, nurseID, nurseID2, roomID, roomID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Take_care (nurse_id,room_id) VALUES (?, ?)";
                    PreparedStatement takeCareOfRoom = connection.prepareStatement(query);
                    takeCareOfRoom.setInt(1, Integer.parseInt(nurseID2.getText()));
                    takeCareOfRoom.setInt(2, (int) roomID2.getValue());
                    takeCareOfRoom.executeUpdate();
                    message.setText("A new patient stay was inserted successfully!");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        select.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, nurseID, nurseID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                for (Component component : content.getComponents()) {
                    component.setVisible(false);
                }

                String query;
                PreparedStatement select;
                ResultSet result;

                try {
                    query = "SELECT * FROM Nurse WHERE id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(nurseID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 90, 270));
                    query = "SELECT * FROM Take_care WHERE nurse_id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(nurseID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 450, 270));
                    query = "SELECT * FROM Operation_help WHERE nurse_id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(nurseID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 810, 270));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        deleteTakeCare.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, nurseID, nurseID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                String query = "DELETE FROM Take_care WHERE nurse_id = ?";
                try {
                    PreparedStatement deleteTakeCare = connection.prepareStatement(query);
                    deleteTakeCare.setInt(1, Integer.parseInt(nurseID2.getText()));
                    deleteTakeCare.executeUpdate();

                    message.setText("The record has been deleted successfully.");
                    message.setForeground(Color.RED);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        allNurses.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            String query;
            PreparedStatement select;
            ResultSet result;

            try {
                query = "SELECT * FROM Nurse";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 90, 270));
                query = "SELECT * FROM Take_care";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 450, 270));
                query = "SELECT * FROM Operation_help";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 810, 270));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }

            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctor, nurse, patient, manager, exit);
        });
    }
}