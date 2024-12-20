import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

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

    public static JButton button(String text, Color bg, Font font, int x, int y, int width, int height, Color border, int thickness, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.setBorder(BorderFactory.createLineBorder(border, thickness));
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

    public static JLabel label(String text, Color fg, Font font, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(fg);
        label.setFont(font);
        int width = (int) label.getPreferredSize().getWidth();
        width += (width % 2);
        label.setBounds(x, y, width, (int) label.getPreferredSize().getHeight());
        return label;
    }

    public static JSpinner spinner(Object model, int x, int y, int width, int height, Font font, Color fg, Color bg, int thickness) {
        JSpinner spinner = new JSpinner(model instanceof SpinnerNumberModel ? (SpinnerNumberModel) model : (SpinnerListModel) model);

        spinner.setFont(font);
        spinner.setBounds(x, y, width, height);
        spinner.setBorder(BorderFactory.createLineBorder(fg, thickness));

        JTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setBackground(bg);
        textField.setForeground(fg);

        return spinner;
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
}