import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

class Doctor {
    private void setVisibility(boolean isVisible, Component... components) {
        for (Component component : components) {
            component.setVisible(isVisible);
        }
    }

    private JButton add, remove, addAppointment, makeManager, select, superviseNurse, back;

    private final Font buttonFont = new Font("Arial", Font.BOLD, 24), labelFont = new Font("Arial", Font.BOLD, 22), font = new Font("Arial", Font.BOLD, 14);
    private final Color blue = new Color(1, 50, 67), gray = new Color(150, 150, 150), white = new Color(242, 242, 242);

    private final JLabel doctorID = Create.label("Doctor ID", blue, labelFont, 898, 108);
    private final JLabel clinicID = Create.label("Clinic ID", blue, labelFont, 904, 216);
    private final JLabel nurseID = Create.label("Nurse ID", blue, labelFont, 902, 216);
    private final JLabel specialization = Create.label("Specialization", blue, labelFont, 876, 216);
    private final JLabel firstName = Create.label("First name", blue, labelFont, 892, 324);
    private final JLabel shift = Create.label("Shift", blue, labelFont, 922, 324);
    private final JLabel day = Create.label("Day", blue, labelFont, 926, 432);
    private final JLabel lastName = Create.label("Last name", blue, labelFont, 893, 432);
    private final JLabel yearsOfExperience = Create.label("Years of experience", blue, labelFont, 845, 540);
    private final JLabel degree = Create.label("Degree", blue, labelFont, 909, 648);

    private String[] specialties = { "Dermatology", "Gastroenterology", "General Medicine", "Ophthalmology", "Orthopedics", "Otolaryngology (ENT)", "Pediatrics", "Radiology" };
    private String[] days = { "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
    private String[] degrees = { "Bachelor", "Master", "Doctoral" };

    private JTextField doctorID2 = Create.textField(blue, white, blue, font, 3, 1095, 101, 400, 40);
    private JTextField clinicID2 = Create.textField(blue, white, blue, font, 3, 1095, 209, 400, 40);
    private JTextField nurseID2 = Create.textField(blue, white, blue, font, 3, 1095, 209, 400, 40);
    private JSpinner specialization2 = Create.listSpinner(specialties, 1095, 209, 400, 40, font, blue, white, 3);
    private JTextField firstName2 = Create.textField(blue, white, blue, font, 3, 1095, 317, 400, 40);
    private JSpinner shift2 = Create.spinner(white, blue, font, 1095, 317, 400, 40, 3, 1, 6, 1, 1);
    private JComboBox<String> day2 = Create.comboBox(days, white, blue, font, 1095, 425, 400, 40, 3);
    private JTextField lastName2 = Create.textField(blue, white, blue, font, 3, 1095, 425, 400, 40);
    private JSpinner yearsOfExperience2 = Create.spinner(white, blue, font, 1095, 533, 400, 40, 3, 0, Integer.MAX_VALUE, 1, 0);
    private JComboBox<String> degree2 = Create.comboBox(degrees, white, blue, font, 1095, 641, 400, 40, 3);
    private JTextField message = Create.textField(blue, white, white, font, 0, 970, 756, 400, 40);

    private JButton confirm = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, null);

    public Doctor(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content, Connection connection) {
        content.add(doctorID);
        content.add(doctorID2);
        content.add(clinicID);
        content.add(clinicID2);
        content.add(nurseID);
        content.add(nurseID2);
        content.add(specialization);
        content.add(specialization2);
        content.add(firstName);
        content.add(firstName2);
        content.add(shift);
        content.add(shift2);
        content.add(day);
        content.add(day2);
        content.add(lastName);
        content.add(lastName2);
        content.add(yearsOfExperience);
        content.add(yearsOfExperience2);
        content.add(degree);
        content.add(degree2);
        content.add(message);
        content.add(confirm);

        for (Component component : sidebar.getComponents()) {
            component.setVisible(false);
        }
        for (Component component : content.getComponents()) {
            component.setVisible(false);
        }

        add = Create.button("Add", blue, buttonFont, 91, 108, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, specialization, specialization2, firstName, firstName2, lastName, lastName2, degree, degree2, yearsOfExperience, yearsOfExperience2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Doctor (id, first_name, last_name, degree, years_of_exp, specialization)" + "VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement add = connection.prepareStatement(query);
                    add.setInt(1, Integer.parseInt(doctorID2.getText()));
                    add.setString(2, firstName2.getText());
                    add.setString(3, lastName2.getText());
                    add.setString(4, (String) degree2.getSelectedItem());
                    add.setInt(5, (int) yearsOfExperience2.getValue());
                    add.setString(6, (String) specialization2.getValue());
                    add.executeUpdate();
                    message.setText("The doctor has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(add);

        remove = Create.button("Remove", blue, buttonFont, 91, 216, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "DELETE FROM Doctor WHERE (id) = " + "?";
                    PreparedStatement remove = connection.prepareStatement(query);
                    remove.setInt(1, Integer.parseInt(doctorID2.getText()));
                    remove.executeUpdate();

                    message.setText("The doctor has been removed successfully.");
                    message.setForeground(Color.RED);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(remove);

        addAppointment = Create.button("Add Appointment", blue, buttonFont, 91, 324, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, clinicID, clinicID2, shift, shift2, day, day2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Appointment(doctor_id,[day],shift_number,clinic_id)" + "VALUES (?, ?, ?, ?)";
                    PreparedStatement addAppointment = connection.prepareStatement(query);
                    addAppointment.setInt(1, Integer.parseInt(doctorID2.getText()));
                    addAppointment.setDate(2, (Date) day2.getSelectedItem());
                    addAppointment.setInt(3, (int) shift2.getValue());
                    addAppointment.setInt(4, Integer.parseInt(clinicID2.getText()));
                    addAppointment.executeUpdate();
                    message.setText("The appointment has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(addAppointment);

        makeManager = Create.button("Make Manager", blue, buttonFont, 91, 432, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, specialization, specialization2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Specialization (name, start_date, manager_id)" + "VALUES (?, ?, ?)";
                    PreparedStatement makeManager = connection.prepareStatement(query);
                    makeManager.setString(1, (String) specialization2.getValue());
                    makeManager.setDate(2, Date.valueOf(LocalDate.now()));
                    makeManager.setInt(3, Integer.parseInt(doctorID2.getText()));
                    makeManager.executeUpdate();
                    message.setText("The manager has been added successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(makeManager);

        select = Create.button("Select", blue, buttonFont, 91, 540, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "SELECT * FROM Doctor WHERE id = ?";
                    PreparedStatement select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(doctorID2.getText()));
                    for (Component component : content.getComponents()) {
                        component.setVisible(false);
                    }
                    ResultSet resultSet = select.executeQuery();
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1) + "\n" + resultSet.getString(2) + "\n" + resultSet.getString(3) + "\n" + resultSet.getString(4) + "\n" + resultSet.getInt(5) + "\n" + resultSet.getString(6));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(select);

        superviseNurse = Create.button("Supervise Nurse", blue, buttonFont, 91, 648, 238, 45, 3, e -> {
            confirm.setBorder(BorderFactory.createLineBorder(gray, 3));
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctorID, doctorID2, nurseID, nurseID2, firstName, firstName2, lastName, lastName2, confirm);
            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Nurse (id, first_name, last_name, supervizor_id)" + "VALUES (?, ?, ?, ?)";
                    PreparedStatement superviseNurse = connection.prepareStatement(query);
                    superviseNurse.setInt(1, Integer.parseInt(nurseID2.getText()));
                    superviseNurse.setString(2, firstName2.getText());
                    superviseNurse.setString(3, lastName2.getText());
                    superviseNurse.setInt(4, Integer.parseInt(doctorID2.getText()));
                    superviseNurse.executeUpdate();
                    message.setText("The nurse has been supervised successfully.");
                    message.setForeground(Color.GREEN);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });
        sidebar.add(superviseNurse);

        back = Create.button("Back", gray, buttonFont, 91, 864, 238, 45, 3, e -> {
            for (Component component : sidebar.getComponents()) {
                component.setVisible(false);
            }
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            setVisibility(true, doctor, nurse, patient, exit);
        });
        back.setBorder(null);
        sidebar.add(back);
    }
}