import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;

import java.awt.Color;
import java.awt.Component;

public class Patient {
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

    private JButton add = Create.button("Add", blue, white, 170, 98, null);
    private JButton delete = Create.button("Delete", blue, white, 154, 196, null);
    private JButton select = Create.button("Select", blue, white, 156, 295, null);
    private JButton allPatients = Create.button("All patients", blue, white, 122, 393, null);
    private JButton examine = Create.button("Examine", blue, white, 145, 491, null);
    private JButton operation = Create.button("Operation", blue, white, 134, 589, null);
    private JButton patientToRoom = Create.button("Add patient to room", blue, white, 64, 687, null);
    private JButton deleteFromRoom = Create.button("Delete patient fromo room", blue, white, 27, 785, null);
    private JButton back = Create.button("Back", blue, white, 167, 982, null);

    private final JLabel patientID = Create.label("Patient ID", blue, 892, 77);
    private final JLabel doctorID = Create.label("Doctor ID", blue, 896, 154);
    private final JLabel firstName = Create.label("First name", blue, 892, 154);
    private final JLabel roomID = Create.label("Room ID", blue, 902, 154);
    private final JLabel diagnosis = Create.label("Diagnosis", blue, 902, 231);
    private final JLabel lastName = Create.label("Last name", blue, 894, 231);
    private final JLabel operationID = Create.label("Operation ID", blue, 881, 231);
    private final JLabel clinicID = Create.label("Clinic ID", blue, 905, 309);
    private final JLabel email = Create.label("Email", blue, 920, 309);
    private final JLabel describition = Create.label("Describition", blue, 887, 385);
    private final JLabel gender = Create.label("Gender", blue, 911, 385);
    private final JLabel country = Create.label("Country", blue, 906, 463);
    private final JLabel nurseID = Create.label("Nurse ID", blue, 902, 463);
    private final JLabel city = Create.label("City", blue, 925, 540);
    private final JLabel dateOfBirth = Create.label("Date of birth", blue, 877, 617);
    private final JLabel streetNumber = Create.label("Street number", blue, 871, 694);
    private final JLabel buildingNumber = Create.label("Buildingn umber", blue, 867, 771);

    private JTextField patientID2 = Create.textField(blue, white, blue, 1095, 70, 400, 40);
    private JTextField doctorID2 = Create.textField(blue, white, blue, 1095, 147, 400, 40);
    private JTextField firstName2 = Create.textField(blue, white, blue, 1095, 147, 400, 40);
    private JTextField roomID2 = Create.textField(blue, white, blue, 1095, 147, 400, 40);
    private JTextField diagnosis2 = Create.textField(blue, white, blue, 1095, 224, 400, 40);
    private JTextField lastName2 = Create.textField(blue, white, blue, 1095, 224, 400, 40);
    private JTextField operationID2 = Create.textField(blue, white, blue, 1095, 224, 400, 40);
    private JTextField clinicID2 = Create.textField(blue, white, blue, 1095, 302, 400, 40);
    private JTextField email2 = Create.textField(blue, white, blue, 1095, 302, 400, 40);
    private JTextField describition2 = Create.textField(blue, white, blue, 1095, 378, 400, 40);
    private JTextField gender2 = Create.textField(blue, white, blue, 1095, 378, 400, 40);
    private JTextField country2 = Create.textField(blue, white, blue, 1095, 456, 400, 40);
    private JTextField nurseID2 = Create.textField(blue, white, blue, 1095, 456, 400, 40);
    private JTextField city2 = Create.textField(blue, white, blue, 1095, 533, 400, 40);
    private JTextField dateOfBirth2 = Create.textField(blue, white, blue, 1095, 610, 400, 40);
    private JTextField streetNumber2 = Create.textField(blue, white, blue, 1095, 687, 400, 40);
    private JTextField buildingNumber2 = Create.textField(blue, white, blue, 1095, 764, 400, 40);

    private JTextField message = Create.textField(blue, white, blue, 970, 849, 400, 40);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 926, null);

    public Patient(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content, Connection connection) {
        sidebar.add(add);
        sidebar.add(delete);
        sidebar.add(select);
        sidebar.add(allPatients);
        sidebar.add(examine);
        sidebar.add(operation);
        sidebar.add(patientToRoom);
        sidebar.add(deleteFromRoom);
        sidebar.add(back);

        content.add(firstName);
        content.add(firstName2);
        content.add(doctorID);
        content.add(doctorID2);
        content.add(operationID);
        content.add(operationID2);
        content.add(lastName);
        content.add(lastName2);
        content.add(describition);
        content.add(describition2);
        content.add(patientID);
        content.add(patientID2);
        content.add(diagnosis);
        content.add(diagnosis2);
        content.add(email);
        content.add(email2);
        content.add(gender);
        content.add(gender2);
        content.add(country);
        content.add(country2);
        content.add(city);
        content.add(city2);
        content.add(dateOfBirth);
        content.add(dateOfBirth2);
        content.add(streetNumber);
        content.add(streetNumber2);
        content.add(buildingNumber);
        content.add(buildingNumber2);
        content.add(clinicID);
        content.add(clinicID2);
        content.add(nurseID);
        content.add(nurseID2);
        content.add(roomID);
        content.add(roomID2);
        content.add(message);
        content.add(confirm);

        for (Component component : sidebar.getComponents()) {
            component.setVisible(false);
        }

        for (Component component : content.getComponents()) {
            component.setVisible(false);
        }

        setVisibility(true, add, delete, select, allPatients, examine, operation, patientToRoom, deleteFromRoom, back);

        add.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, firstName, firstName2, lastName, lastName2, patientID, patientID2, email, email2, gender, gender2, country, country2, city, city2, dateOfBirth, dateOfBirth2, streetNumber, streetNumber2, buildingNumber, buildingNumber2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Patient (id, first_name, last_name, gender,date_of_birth,city,country,street_number,building_number,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(patientID.getText()));

                    add.setString(2, firstName2.getText());
                    add.setString(3, lastName2.getText());
                    add.setString(4, gender2.getText());
                    // add.setDate(5, (Date) dateOfBirth2.getText());
                    add.setString(6, city2.getText());
                    add.setString(7, country2.getText());
                    add.setInt(8, Integer.parseInt(streetNumber2.getText()));
                    add.setInt(9, Integer.parseInt(buildingNumber2.getText()));
                    add.setString(10, email2.getText());
                    add.executeUpdate();

                    message.setText("The patient has been added successfully.");
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

            setVisibility(true, patientID, patientID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "DELETE FROM Patient WHERE id = ?";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(patientID2.getText()));
                    add.executeUpdate();

                    message.setText("The patient has been deleted successfully.");
                    message.setForeground(Color.RED);
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

            setVisibility(true, patientID, patientID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "SELECT * FROM Patient WHERE id = ?";
                    PreparedStatement select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(patientID2.getText()));
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
        });

        allPatients.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            removeAllActionListeners(confirm);

            try {
                String query = "SELECT * FROM Patient";
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

        examine.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, patientID, patientID2, diagnosis, diagnosis2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Examine (doctor_id,patient_id,diagnosis,date) VALUES (?,?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(doctorID2.getText()));
                    add.setInt(2, Integer.parseInt(patientID2.getText()));
                    add.setString(3, diagnosis2.getText());
                    add.setDate(4, Date.valueOf(LocalDate.now()));
                    add.executeUpdate();

                    message.setText("The eamine has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        operation.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, operationID, operationID2, describition, describition2, patientID, patientID2, doctorID, doctorID2, clinicID, clinicID2, nurseID, nurseID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Operation_details (description,date,clinic_id) VALUES (?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setString(1, describition.getText());
                    add.setDate(2, Date.valueOf(LocalDate.now()));
                    add.setInt(3, Integer.parseInt(clinicID2.getText()));
                    add.executeUpdate();

                    String query2 = "INSERT INTO Operation_help (nurse_id,operation_id) VALUES (?,?)";
                    PreparedStatement add2 = connection.prepareStatement(query2);
                    add2.setInt(1, Integer.parseInt(nurseID2.getText()));
                    add2.setInt(2, Integer.parseInt(operationID2.getText()));
                    add2.executeUpdate();

                    String query3 = "INSERT INTO Perform_operation (doctor_id,operation_id,patient_id) VALUES (?,?, ?)";
                    PreparedStatement add3 = connection.prepareStatement(query3);
                    add3.setInt(1, Integer.parseInt(doctorID2.getText()));
                    add3.setInt(2, Integer.parseInt(operationID2.getText()));
                    add3.setInt(3, Integer.parseInt(patientID2.getText()));
                    add3.executeUpdate();

                    message.setText("The operation has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        patientToRoom.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, patientID, patientID2, roomID, roomID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.executeUpdate();

                    message.setText("The patient has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        deleteFromRoom.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, patientID, patientID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "UPDATE Patient_stay SET leave = ? WHERE patient_id = ?";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setDate(1, Date.valueOf(LocalDate.now()));
                    add.setInt(2, Integer.parseInt(patientID2.getText()));
                    add.executeUpdate();

                    message.setText("The patient has been added successfully.");
                    message.setForeground(Color.RED);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
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