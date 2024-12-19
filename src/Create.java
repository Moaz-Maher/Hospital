import javax.swing.*;
import java.awt.*;

public class Create {
    public static JFrame frame(int width, int height) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setUndecorated(true);
        return frame;
    }

    public static JPanel panel(Color bg, int x, int y, int width, int height) {
        JPanel panel = new JPanel(null);
        panel.setBackground(bg);
        panel.setBounds(x, y, width, height);
        return panel;
    }

    public static JLabel label(String text, Color fg, Font font, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(fg);
        label.setFont(font);
        int width = (int) label.getPreferredSize().getWidth();
        width += (width % 2);
        label.setBounds(x, y, width, (int) label.getPreferredSize().getHeight());
        return label;
    }

    public static JTextField textField(Color fg, Color bg, Color borderColor, Font font, int thickness, int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(bg);
        textField.setForeground(fg);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createLineBorder(borderColor, thickness));
        textField.setBounds(x, y, width, height);
        return textField;
    }

    public static JButton button(String text, Color bg, Font font, int x, int y, int width, int height, int thickness, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, thickness));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (action != null) {
            button.addActionListener(action);
        }
        return button;
    }

    public static JComboBox<String> comboBox(String[] items, Color bg, Color fg, Font font, int x, int y, int width, int height, int thickness) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(font);
        comboBox.setBackground(bg);
        comboBox.setForeground(fg);
        comboBox.setBounds(x, y, width, height);
        comboBox.setBorder(BorderFactory.createLineBorder(fg, thickness));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                }
                return c;
            }
        });

        return comboBox;
    }

    public static JSpinner spinner(Color bg, Color fg, Font font, int x, int y, int width, int height, int thickness, int min, int max, int step, int value) {
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(value, min, max, step);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setFont(font);
        spinner.setBounds(x, y, width, height);
        spinner.setBorder(BorderFactory.createLineBorder(fg, thickness));
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setEditable(false);
            textField.setBackground(bg);
            textField.setForeground(fg);
        }

        return spinner;
    }

    public static JSpinner listSpinner(String[] specialties, int x, int y, int width, int height, Font font, Color fg, Color bg, int thickness) {
        JSpinner listSpinner = new JSpinner(new SpinnerListModel(specialties));
        listSpinner.setBounds(x, y, width, height);
        listSpinner.setFont(font);
        listSpinner.setForeground(fg);
        listSpinner.setBackground(bg);
        listSpinner.setBorder(BorderFactory.createLineBorder(fg, thickness));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) listSpinner.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        editor.getTextField().setEditable(false);
        return listSpinner;
    }

}