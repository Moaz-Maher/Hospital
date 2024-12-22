import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Create {
    private final static Color blue = new Color(1, 50, 67), white = new Color(242, 242, 242);

    public static JFrame frame() {
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setUndecorated(true);
        return frame;
    }

    public static JPanel panel(Color bg, int x, int width) {
        JPanel panel = new JPanel(null);
        panel.setBackground(bg);
        panel.setBounds(x, 0, width, 1080);
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

    public static JComboBox<String> comboBox(String[] items, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("MV Boli", Font.BOLD, 16));
        comboBox.setBackground(white);
        comboBox.setForeground(blue);
        comboBox.setBounds(x, y, 400, 40);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(1, 50, 67), 1));
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

    public static JLabel label(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(1, 50, 67));
        label.setFont(new Font("MV Boli", Font.BOLD, 20));
        int width = (int) label.getPreferredSize().getWidth();
        width += (width % 2);
        label.setBounds(x, y, width, (int) label.getPreferredSize().getHeight());
        return label;
    }

    public static JSpinner spinner(Object model, int x, int y) {
        JSpinner spinner = new JSpinner(model instanceof SpinnerNumberModel ? (SpinnerNumberModel) model : (SpinnerListModel) model);

        spinner.setFont(new Font("MV Boli", Font.BOLD, 16));
        spinner.setBounds(x, y, 400, 40);
        spinner.setBorder(BorderFactory.createLineBorder(new Color(1, 50, 67), 2));

        JTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setBackground(white);
        textField.setForeground(new Color(1, 50, 67));

        return spinner;
    }

    public static JTextField textField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(white);
        textField.setForeground(new Color(1, 50, 67));
        textField.setFont(new Font("MV Boli", Font.BOLD, 16));
        textField.setBorder(BorderFactory.createLineBorder(new Color(1, 50, 67), 2));
        textField.setBounds(x, y, 400, 40);
        return textField;
    }

    public static JScrollPane table(Connection connection, JPanel content, ResultSet result, String query, int x, int y, int height) throws SQLException {
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
        tableScrollPane.setBounds(x, y, 1500, height);
        return tableScrollPane;
    }
}