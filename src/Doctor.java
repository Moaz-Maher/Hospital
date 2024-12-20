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
import java.awt.Font;

class Doctor {
    private void setVisibility(boolean isVisible, Component... components) {
        for (Component component : components) {
            component.setVisible(isVisible);
        }
    }

    private final Font buttonFont = new Font("Arial", Font.BOLD, 24);
    private final Font labelFont = new Font("Arial", Font.BOLD, 22);
    private final Font font = new Font("Arial", Font.BOLD, 14);
    private final Color blue = new Color(1, 50, 67), gray = new Color(150, 150, 150), white = new Color(242, 242, 242);

    private JButton add = Create.button("Add", blue, buttonFont, 91, 108, 238, 45, Color.WHITE, 3, null);
    private JButton remove = Create.button("Remove", blue, buttonFont, 91, 216, 238, 45, Color.WHITE, 3, null);
    private JButton addAppointment = Create.button("Add Appointment", blue, buttonFont, 91, 324, 238, 45, Color.WHITE, 3, null);
    private JButton makeManager = Create.button("Make Manager", blue, buttonFont, 91, 432, 238, 45, Color.WHITE, 3, null);
    private JButton select = Create.button("Select", blue, buttonFont, 91, 540, 238, 45, Color.WHITE, 3, null);
    private JButton superviseNurse = Create.button("Supervise Nurse", blue, buttonFont, 91, 648, 238, 45, Color.WHITE, 3, null);
    private JButton back = Create.button("Back", gray, buttonFont, 91, 864, 238, 45, Color.WHITE, 3, null);

    JLabel doctors = Create.label("Doctors", blue, labelFont, 1129, 36);
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
    private JSpinner specialization2 = Create.spinner(new SpinnerListModel(specialties), 1095, 209, 400, 40, font, blue, white, 3);
    private JTextField firstName2 = Create.textField(blue, white, blue, font, 3, 1095, 317, 400, 40);
    private JSpinner shift2 = Create.spinner(new SpinnerNumberModel(1, 1, 6, 1), 1095, 317, 400, 40, font, blue, white, 3);
    private JComboBox<String> day2 = Create.comboBox(days, white, blue, font, 1095, 425, 400, 40, 3);
    private JTextField lastName2 = Create.textField(blue, white, blue, font, 3, 1095, 425, 400, 40);
    private JSpinner yearsOfExperience2 = Create.spinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1), 1095, 533, 400, 40, font, blue, white, 3);
    private JComboBox<String> degree2 = Create.comboBox(degrees, white, blue, font, 1095, 641, 400, 40, 3);
    private JTextField message = Create.textField(blue, white, white, font, 0, 970, 756, 400, 40);

    private JButton confirm = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, gray, 3, null);

    public Doctor(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content, Connection connection) {
        content.add(doctors);
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

        add.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, specialization, specialization2, firstName, firstName2, lastName, lastName2, degree, degree2, yearsOfExperience, yearsOfExperience2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Doctor (id, first_name, last_name, degree, years_of_exp, specialization) VALUES (?, ?, ?, ?, ?, ?)";
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

        remove.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "DELETE FROM Doctor WHERE id = ?";
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

        addAppointment.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, clinicID, clinicID2, shift, shift2, day, day2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Appointment(doctor_id, [day], shift_number, clinic_id) VALUES (?, ?, ?, ?)";
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

        makeManager.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, specialization, specialization2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Specialization (name, start_date, manager_id) VALUES (?, ?, ?)";
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

        select.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "SELECT * FROM (SELECT * FROM Doctor WHERE id = ?) tmp LEFT JOIN Appointment a ON a.doctor_id = tmp.id LEFT JOIN Specialization s ON s.manager_id = tmp.id LEFT JOIN Nurse n ON n.supervizor_id = tmp.id";
                    PreparedStatement select = connection.prepareStatement(query);
                    select.setInt(1, Integer.parseInt(doctorID2.getText()));
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
        sidebar.add(select);

        superviseNurse.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, nurseID, nurseID2, firstName, firstName2, lastName, lastName2, confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "INSERT INTO Nurse (id, first_name, last_name, supervizor_id) VALUES (?, ?, ?, ?)";
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

        back.addActionListener(e -> {
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

        try {
            String query = "SELECT * FROM Doctor";
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
            tableScrollPane.setBounds(420, 108, 1500, 972);
            content.add(tableScrollPane);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctors, tableScrollPane);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}