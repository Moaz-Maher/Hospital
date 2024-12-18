import javax.swing.*;
import java.awt.*;

class Doctor {
    private JButton add, remove, addAppointment, makeManager, select, superviseNurse, back;
    private Font buttonFont = new Font("Arial", Font.BOLD, 24);
    private Font labelFont = new Font("Arial", Font.BOLD, 22);
    private Font font = new Font("Arial", Font.BOLD, 14);
    private Color blue = new Color(1, 50, 67);
    private Color gray = new Color(150, 150, 150);
    private Color white = new Color(242, 242, 242);

    private JLabel doctorID = Create.label("Doctor ID", blue, labelFont, 753, 108);
    private JLabel clinicID = Create.label("Clinic ID", blue, labelFont, 759, 216);
    private JLabel specialization = Create.label("Specialization", blue, labelFont, 731, 216);
    private JLabel nurseID = Create.label("Nurse ID", blue, labelFont, 757, 216);
    private JLabel shift = Create.label("Shift", blue, labelFont, 777, 324);
    private JLabel firstName = Create.label("First name", blue, labelFont, 747, 324);
    private JLabel lastName = Create.label("Last name", blue, labelFont, 748, 432);
    private JLabel yearsOfExperience = Create.label("Years of experience", blue, labelFont, 700, 540);
    private JLabel degree = Create.label("Degree", blue, labelFont, 764, 648);
    private JLabel day = Create.label("Day", blue, labelFont, 781, 432);

    private JTextField doctorIDField = Create.textField(blue, white, blue, font, 3, 950, 101, 400, 40);
    private JTextField clinicIDField = Create.textField(blue, white, blue, font, 3, 950, 209, 400, 40);
    private JTextField specializationField = Create.textField(blue, white, blue, font, 3, 950, 209, 400, 40);
    private JTextField nurseIDField = Create.textField(blue, white, blue, font, 3, 950, 209, 400, 40);
    private JTextField firstNameField = Create.textField(blue, white, blue, font, 3, 950, 317, 400, 40);
    private JTextField lastNameField = Create.textField(blue, white, blue, font, 3, 950, 425, 400, 40);

    private JSpinner shiftSpinner = Create.spinner(white, blue, font, 950, 317, 400, 40, 3, 1, 6, 1, 1);
    private JSpinner yearsOfExperienceSpinner = Create.spinner(white, blue, font, 950, 533, 400, 40, 3, 0, Integer.MAX_VALUE, 1, 0);

    private String[] days = { "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
    private JComboBox<String> comboBox = Create.comboBox(days, white, blue, font, 950, 425, 400, 40, 3);
    private String[] degrees = { "Bachelor", "Master", "Doctoral" };
    private JComboBox<String> degreeField = Create.comboBox(degrees, white, blue, font, 950, 641, 400, 40, 3);

    public Doctor(JPanel sidebar, JButton doctor, JButton nurse, JButton patient, JButton exit, JPanel content) {
        add = Create.button("Add", blue, buttonFont, 91, 108, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            content.add(specialization);
            content.add(specializationField);
            content.add(firstName);
            content.add(firstNameField);
            content.add(lastName);
            content.add(lastNameField);
            content.add(degree);
            content.add(degreeField);
            content.add(yearsOfExperience);
            content.add(yearsOfExperienceSpinner);
            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            specialization.setVisible(true);
            specializationField.setVisible(true);
            firstName.setVisible(true);
            firstNameField.setVisible(true);
            lastName.setVisible(true);
            lastNameField.setVisible(true);
            degree.setVisible(true);
            degreeField.setVisible(true);
            yearsOfExperience.setVisible(true);
            yearsOfExperienceSpinner.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(add);

        remove = Create.button("Remove", blue, buttonFont, 91, 216, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(remove);

        addAppointment = Create.button("Add Appointment", blue, buttonFont, 91, 324, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            content.add(clinicID);
            content.add(clinicIDField);
            content.add(shift);
            content.add(shiftSpinner);
            content.add(day);
            content.add(comboBox);

            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);

            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }

            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            clinicID.setVisible(true);
            clinicIDField.setVisible(true);
            shift.setVisible(true);
            comboBox.setVisible(true);
            day.setVisible(true);
            shiftSpinner.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(addAppointment);

        makeManager = Create.button("Make Manager", blue, buttonFont, 91, 432, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            content.add(specialization);
            content.add(specializationField);
            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            specialization.setVisible(true);
            specializationField.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(makeManager);

        select = Create.button("Select", blue, buttonFont, 91, 540, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(select);

        superviseNurse = Create.button("Supervise Nurse", blue, buttonFont, 91, 648, 238, 45, 3, e -> {
            content.add(doctorID);
            content.add(doctorIDField);
            content.add(nurseID);
            content.add(nurseIDField);
            JButton add = Create.button("Add", blue, labelFont, 1051, 864, 238, 45, 3, e1 -> {
            });
            add.setBorder(BorderFactory.createLineBorder(gray, 3));
            content.add(add);
            for (Component component : content.getComponents()) {
                component.setVisible(false);
            }
            doctorID.setVisible(true);
            doctorIDField.setVisible(true);
            nurseID.setVisible(true);
            nurseIDField.setVisible(true);
            add.setVisible(true);
        });
        sidebar.add(superviseNurse);

        back = Create.button("Back", gray, buttonFont, 91, 864, 238, 45, 3, e -> {
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
        back.setBorder(null);
        sidebar.add(back);
    }
}