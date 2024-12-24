import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.SpinnerNumberModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private JButton deleteFromRoom = Create.button("Delete patient from room", blue, white, 34, 785, null);
    private JButton back = Create.button("Back", blue, white, 167, 982, null);

    private final JLabel patientID = Create.label("Patient ID", 892, 77);
    private final JLabel doctorID = Create.label("Doctor ID", 896, 154);
    private final JLabel firstName = Create.label("First name", 892, 154);
    private final JLabel roomID = Create.label("Room ID", 902, 154);
    private final JLabel diagnosis = Create.label("Diagnosis", 902, 231);
    private final JLabel lastName = Create.label("Last name", 894, 231);
    private final JLabel operationID = Create.label("Operation ID", 881, 231);
    private final JLabel clinicID = Create.label("Clinic ID", 905, 309);
    private final JLabel email = Create.label("Email", 920, 309);
    private final JLabel describition = Create.label("Describition", 887, 385);
    private final JLabel country = Create.label("Country", 906, 385);
    private final JLabel nurseID = Create.label("Nurse ID", 902, 463);
    private final JLabel city = Create.label("City", 925, 463);
    private final JLabel dateOfBirth = Create.label("Date of birth", 877, 540);
    private final JLabel streetNumber = Create.label("Street number", 871, 617);
    private final JLabel buildingNumber = Create.label("Buildingn number", 862, 694);
    private final JLabel gender = Create.label("Gender", 911, 771);
    private final JLabel phoneNumber = Create.label("Phone number", 875, 848);

    private JTextField patientID2 = Create.textField(1095, 70);
    private JTextField doctorID2 = Create.textField(1095, 147);
    private JTextField firstName2 = Create.textField(1095, 147);
    private JSpinner roomID2 = Create.spinner(new SpinnerNumberModel(0, 0, 9, 1), 1095, 147);
    private JTextField diagnosis2 = Create.textField(1095, 224);
    private JTextField lastName2 = Create.textField(1095, 224);
    private JSpinner operationID2 = Create.spinner(new SpinnerNumberModel(0, 0, 9, 1), 1095, 224);
    private JSpinner clinicID2 = Create.spinner(new SpinnerNumberModel(0, 0, 9, 1), 1095, 302);
    private JTextField email2 = Create.textField(1095, 302);
    private JTextField describition2 = Create.textField(1095, 378);
    private JTextField country2 = Create.textField(1095, 378);
    private JTextField nurseID2 = Create.textField(1095, 456);
    private JTextField city2 = Create.textField(1095, 456);
    private JTextField year = Create.textField(1095, 533);
    private final JLabel _1 = Create.label("-", 1270, 540);
    private JTextField month = Create.textField(1295, 533);
    private final JLabel _2 = Create.label("-", 1390, 540);
    private JTextField day = Create.textField(1415, 533);
    private JTextField streetNumber2 = Create.textField(1095, 610);
    private JTextField buildingNumber2 = Create.textField(1095, 687);
    String[] genders = { "Male", "Female" };
    private JComboBox<String> gender2 = Create.comboBox(genders, 1095, 764);
    private JTextField phoneNumber2 = Create.textField(1095, 840);
    private JTextField message = Create.textField(970, 1000);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 926, null);

    public Patient(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton manager, JButton exit, JPanel content, Connection connection) {
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
        content.add(phoneNumber);
        content.add(phoneNumber2);
        content.add(country);
        content.add(country2);
        content.add(city);
        content.add(city2);
        content.add(dateOfBirth);
        year.setSize(160, 40);
        content.add(year);
        content.add(_1);
        month.setSize(80, 40);
        content.add(month);
        content.add(_2);
        day.setSize(80, 40);
        content.add(day);
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
        message.setBorder(null);
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

            setVisibility(true, firstName, firstName2, lastName, lastName2, patientID, patientID2, email, email2, gender, gender2, phoneNumber, phoneNumber2, country, country2, city, city2, dateOfBirth, year, _1, month, _2, day, streetNumber, streetNumber2, buildingNumber, buildingNumber2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Patient (id, first_name, last_name, gender,date_of_birth,city,country,street_number,building_number,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(patientID2.getText()));
                    add.setString(2, firstName2.getText());
                    add.setString(3, lastName2.getText());
                    add.setString(4, (String) gender2.getSelectedItem());
                    add.setDate(5, Date.valueOf(LocalDate.parse(year.getText() + "-" + month.getText() + "-" + day.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                    add.setString(6, city2.getText());
                    add.setString(7, country2.getText());
                    add.setInt(8, Integer.parseInt(streetNumber2.getText()));
                    add.setInt(9, Integer.parseInt(buildingNumber2.getText()));
                    add.setString(10, email2.getText());
                    add.executeUpdate();

                    query = "INSERT INTO Patient_Phone (patient_id, phone) VALUES (?, ?)";
                    add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(patientID2.getText()));
                    add.setString(2, phoneNumber2.getText());
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
                for (Component component : content.getComponents()) {
                    component.setVisible(false);
                }

                String query;
                PreparedStatement select;
                ResultSet result;

                try {
                    query = "SELECT * FROM Patient WHERE id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(patientID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 54, 216));
                    query = "SELECT doctor_id, date, diagnosis FROM Examine WHERE patient_id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(patientID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 324, 216));
                    query = "SELECT phone FROM Patient_Phone WHERE patient_id = ?";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(patientID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 594, 216));
                    query = "SELECT id, description, date, clinic_id FROM (SELECT doctor_id, operation_id FROM Perform_operation WHERE patient_id = ?) tmp INNER JOIN Operation_details d ON d.id = tmp.operation_id";
                    select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(patientID2.getText()));
                    result = select.executeQuery();
                    content.add(Create.table(connection, content, result, query, 420, 864, 216));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        allPatients.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            String query;
            PreparedStatement select;
            ResultSet result;

            try {
                query = "SELECT * FROM Patient";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 54, 216));
                query = "SELECT doctor_id, date, diagnosis FROM Examine";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 324, 216));
                query = "SELECT phone FROM Patient_Phone";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 594, 216));
                query = "SELECT id, description, date, clinic_id FROM (SELECT doctor_id, operation_id FROM Perform_operation) tmp INNER JOIN Operation_details d ON d.id = tmp.operation_id";
                select = connection.prepareStatement(query);
                result = select.executeQuery();
                content.add(Create.table(connection, content, result, query, 420, 864, 216));
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
                    String query = "SET IDENTITY_INSERT Operation_details ON;" + "INSERT INTO Operation_details (id, description,date,clinic_id) VALUES (?, ?, ?, ?);" + "SET IDENTITY_INSERT Operation_details OFF;";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, (int) operationID2.getValue());
                    add.setString(2, describition2.getText());
                    add.setDate(3, Date.valueOf(LocalDate.now()));
                    add.setInt(4, (int) clinicID2.getValue());
                    add.executeUpdate();

                    String query2 = "INSERT INTO Perform_operation (doctor_id,operation_id,patient_id) VALUES (?,?, ?)";
                    PreparedStatement add3 = connection.prepareStatement(query2);
                    add3.setInt(1, Integer.parseInt(doctorID2.getText()));
                    add3.setInt(2, (int) operationID2.getValue());
                    add3.setInt(3, Integer.parseInt(patientID2.getText()));
                    add3.executeUpdate();

                    String query3 = "INSERT INTO Operation_help (nurse_id,operation_id) VALUES (?,?)";
                    PreparedStatement add2 = connection.prepareStatement(query3);
                    add2.setInt(1, Integer.parseInt(nurseID2.getText()));
                    add2.setInt(2, (int) operationID2.getValue());
                    add2.executeUpdate();

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
                    String query = "INSERT INTO patient_stay (patient_id,room_id,entry) VALUES (?,?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(patientID2.getText()));
                    add.setInt(2, (int) roomID2.getValue());
                    add.setDate(3, Date.valueOf(LocalDate.now()));
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

            setVisibility(true, doctor, nurse, patient, manager, exit);
        });
    }
}