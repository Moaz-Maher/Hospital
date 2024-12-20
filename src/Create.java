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

    public static JButton button(String text, Color bg, Color fg, int x, int y, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("MV Boli", Font.BOLD, 24));
        button.setFocusPainted(false);
        int width = (int) button.getPreferredSize().getWidth();
        width += (width % 2);
        int height = (int) button.getPreferredSize().getHeight();
        height += (height % 2);
        button.setBounds(x, y, width, height);
        button.setBorder(null);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (action != null) {
            button.addActionListener(action);
        }
        return button;
    }

    public static JComboBox<String> comboBox(String[] items, Color bg, Color fg, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("MV Boli", Font.BOLD, 16));
        comboBox.setBackground(bg);
        comboBox.setForeground(fg);
        comboBox.setBounds(x, y, width, height);
        comboBox.setBorder(BorderFactory.createLineBorder(fg, 1));
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

    public static JLabel label(String text, Color fg, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(fg);
        label.setFont(new Font("MV Boli", Font.BOLD, 20));
        int width = (int) label.getPreferredSize().getWidth();
        width += (width % 2);
        label.setBounds(x, y, width, (int) label.getPreferredSize().getHeight());
        return label;
    }

    public static JSpinner spinner(Object model, int x, int y, int width, int height, Color fg, Color bg) {
        JSpinner spinner = new JSpinner(model instanceof SpinnerNumberModel ? (SpinnerNumberModel) model : (SpinnerListModel) model);

        spinner.setFont(new Font("MV Boli", Font.BOLD, 16));
        spinner.setBounds(x, y, width, height);
        spinner.setBorder(BorderFactory.createLineBorder(fg, 2));

        JTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setBackground(bg);
        textField.setForeground(fg);

        return spinner;
    }

    public static JTextField textField(Color fg, Color bg, Color borderColor, int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(bg);
        textField.setForeground(fg);
        textField.setFont(new Font("MV Boli", Font.BOLD, 16));
        textField.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        textField.setBounds(x, y, width, height);
        return textField;
    }
}