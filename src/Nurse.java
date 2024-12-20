import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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

    private JButton add = Create.button("Add nurse", blue, Color.WHITE, 133, 135, null);
    private JButton delete = Create.button("Delete nurse", blue, Color.WHITE, 118, 270, null);
    private JButton takeCareOfRoom = Create.button("Take care of room", blue, Color.WHITE, 82, 405, null);
    private JButton deleteTakeCare = Create.button("Delete take care", blue, Color.WHITE, 93, 540, null);
    private JButton allNurses = Create.button("All nurses", blue, Color.WHITE, 135, 675, null);
    private JButton back = Create.button("Back", blue, Color.WHITE, 167, 945, null);

    private final JLabel nurseID = Create.label("Nurse ID", blue, 902, 108);
    private final JLabel firstName = Create.label("First name", blue, 892, 324);
    private final JLabel lastName = Create.label("Last name", blue, 894, 216);
    private final JLabel roomID = Create.label("Room ID", blue, 902, 216);
    private final JLabel supervisorID = Create.label("Supervisor ID", blue, 879, 432);

    private JTextField nurseID2 = Create.textField(blue, white, blue, 1095, 101, 400, 40);
    private JTextField firstName2 = Create.textField(blue, white, blue, 1095, 317, 400, 40);
    private JTextField lastName2 = Create.textField(blue, white, blue, 1095, 209, 400, 40);
    private JTextField roomID2 = Create.textField(blue, white, blue, 1095, 209, 400, 40);
    private JTextField supervisorID2 = Create.textField(blue, white, blue, 1095, 425, 400, 40);
    private JTextField message = Create.textField(blue, white, white, 970, 756, 400, 40);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 864, null);

    public Nurse(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content, Connection connection) {
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
        content.add(message);
        content.add(confirm);

        sidebar.add(add);
        sidebar.add(delete);
        sidebar.add(takeCareOfRoom);
        sidebar.add(deleteTakeCare);
        sidebar.add(allNurses);
        sidebar.add(back);

        for (Component component : sidebar.getComponents()) {
            component.setVisible(false);
        }

        for (Component component : content.getComponents()) {
            component.setVisible(false);
        }

        setVisibility(true, add, delete, takeCareOfRoom, deleteTakeCare, allNurses, back);

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
                    add.setInt(1, Integer.parseInt(firstName2.getText()));
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
                    takeCareOfRoom.setInt(2, Integer.parseInt(roomID2.getText()));
                    int rowsInserted1 = takeCareOfRoom.executeUpdate();
                    if (rowsInserted1 > 0) {
                        message.setText("A new patient stay was inserted successfully!");
                        message.setForeground(Color.GREEN);
                        message.setVisible(true);

                    }
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
                try {
                    String query = "DELETE FROM Take_care WHERE nurse_id = ?";
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

            removeAllActionListeners(confirm);

            try {
                String query = "SELECT * FROM Nurse";
                PreparedStatement select = connection.prepareStatement(query);
                ResultSet result = select.executeQuery();
                DefaultTableModel model = new DefaultTableModel();

                JTable table = new JTable(model);
                ResultSetMetaData metaData = result.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }
                while (result.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = result.getObject(i);
                    }
                    model.addRow(rowData);
                }
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setBounds(420, 0, 1500, 1080);
                content.add(tableScrollPane);

                for (Component component : content.getComponents()) {
                    component.setVisible(false);
                }

                tableScrollPane.setVisible(true);
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

            setVisibility(true, doctor, nurse, patient, exit);
        });
    }
}