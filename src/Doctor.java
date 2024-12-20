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

class Doctor {
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

    private JButton add = Create.button("Add", blue, white, 170, 108, null);
    private JButton remove = Create.button("Remove", blue, white, 147, 216, null);
    private JButton addAppointment = Create.button("Add Appointment", blue, white, 85, 324, null);
    private JButton makeManager = Create.button("Make Manager", blue, white, 108, 432, null);
    private JButton select = Create.button("Select", blue, white, 156, 540, null);
    private JButton superviseNurse = Create.button("Supervise Nurse", blue, white, 97, 648, null);
    private JButton allDoctors = Create.button("All doctors", blue, white, 127, 756, null);
    private JButton back = Create.button("Back", blue, white, 167, 972, null);

    private final JLabel doctorID = Create.label("Doctor ID", blue, 898, 108);
    private final JLabel clinicID = Create.label("Clinic ID", blue, 904, 216);
    private final JLabel nurseID = Create.label("Nurse ID", blue, 902, 216);
    private final JLabel specialization = Create.label("Specialization", blue, 876, 216);
    private final JLabel firstName = Create.label("First name", blue, 892, 324);
    private final JLabel shift = Create.label("Shift", blue, 922, 324);
    private final JLabel day = Create.label("Day", blue, 926, 432);
    private final JLabel lastName = Create.label("Last name", blue, 893, 432);
    private final JLabel yearsOfExperience = Create.label("Years of experience", blue, 845, 540);
    private final JLabel degree = Create.label("Degree", blue, 909, 648);

    private String[] specialties = { "Dermatology", "Gastroenterology", "General Medicine", "Ophthalmology", "Orthopedics", "Otolaryngology (ENT)", "Pediatrics", "Radiology" };
    private String[] days = { "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
    private String[] degrees = { "Bachelor", "Master", "Doctoral" };

    private JTextField doctorID2 = Create.textField(blue, white, blue, 1095, 101, 400, 40);
    private JTextField clinicID2 = Create.textField(blue, white, blue, 1095, 209, 400, 40);
    private JTextField nurseID2 = Create.textField(blue, white, blue, 1095, 209, 400, 40);
    private JSpinner specialization2 = Create.spinner(new SpinnerListModel(specialties), 1095, 209, 400, 40, blue, white);
    private JTextField firstName2 = Create.textField(blue, white, blue, 1095, 317, 400, 40);
    private JSpinner shift2 = Create.spinner(new SpinnerNumberModel(1, 1, 6, 1), 1095, 317, 400, 40, blue, white);
    private JComboBox<String> day2 = Create.comboBox(days, white, blue, 1095, 425, 400, 40);
    private JTextField lastName2 = Create.textField(blue, white, blue, 1095, 425, 400, 40);
    private JSpinner yearsOfExperience2 = Create.spinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1), 1095, 533, 400, 40, blue, white);
    private JComboBox<String> degree2 = Create.comboBox(degrees, white, blue, 1095, 641, 400, 40);
    private JTextField message = Create.textField(blue, white, white, 970, 756, 400, 40);

    private JButton confirm = Create.button("Confirm", white, blue, 1107, 864, null);

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

        sidebar.add(add);
        sidebar.add(remove);
        sidebar.add(addAppointment);
        sidebar.add(makeManager);
        sidebar.add(select);
        sidebar.add(superviseNurse);
        sidebar.add(allDoctors);
        sidebar.add(back);

        for (Component component : sidebar.getComponents()) {
            component.setVisible(false);
        }

        for (Component component : content.getComponents()) {
            component.setVisible(false);
        }

        setVisibility(true, add, remove, addAppointment, makeManager, select, superviseNurse, allDoctors, back);

        add.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, specialization, specialization2, firstName, firstName2, lastName, lastName2, degree, degree2, yearsOfExperience, yearsOfExperience2, confirm);

            removeAllActionListeners(confirm);

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

        remove.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, confirm);

            removeAllActionListeners(confirm);

            confirm.addActionListener(e1 -> {
                try {
                    String query = "DELETE FROM Doctor WHERE id = ?";
                    PreparedStatement remove = connection.prepareStatement(query);
                    remove.setInt(1, Integer.parseInt(doctorID2.getText()));
                    remove.executeUpdate();

                    message.setText("The doctor has been deleted successfully.");
                    message.setForeground(Color.RED);
                    message.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        });

        addAppointment.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, clinicID, clinicID2, shift, shift2, day, day2, confirm);

            removeAllActionListeners(confirm);

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

        makeManager.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, specialization, specialization2, confirm);

            removeAllActionListeners(confirm);

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

        select.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, confirm);

            removeAllActionListeners(confirm);

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

        superviseNurse.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            setVisibility(true, doctorID, doctorID2, nurseID, nurseID2, firstName, firstName2, lastName, lastName2, confirm);

            removeAllActionListeners(confirm);

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

        allDoctors.addActionListener(e -> {
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            removeAllActionListeners(confirm);

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