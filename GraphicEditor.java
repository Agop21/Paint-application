import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public class GraphicEditor {

    private static final Map<String, Color> COLORS = new TreeMap<String, Color>() {{
        put("Black", Color.BLACK);
        put("Blue", Color.BLUE);
        put("Green", Color.GREEN);
        put("Yellow", Color.YELLOW);
        put("Red", Color.RED);
        put("Pink", Color.PINK);
        put("Gray", Color.GRAY);
        put("Magenta", Color.MAGENTA);
        put("Orange", Color.ORANGE);
        put("Dark Gray", Color.DARK_GRAY);
        put("Cyan", Color.CYAN);
    }};

    private GraphicEditor() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 1024, 768);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingField drawField = new DrawingField();
        drawField.setBackground(Color.WHITE);
        drawField.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        DefaultListModel<String> shapeModel = new DefaultListModel<>();
        JList<String> shapes = new JList<>(shapeModel);
        shapeModel.addElement("Rectangle");
        shapeModel.addElement("Filled Oval");
        shapeModel.addElement("Oval");
        shapes.setSelectedIndex(drawField.getShape());
        shapes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                drawField.setShape(shapes.getSelectedIndex());
            }
        });

        JComboBox<String> borderColorList = new JComboBox<String>();
        COLORS.keySet().forEach(borderColorList::addItem);
        borderColorList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getItemSelectable();
                String selectedColor = (String) cb.getSelectedItem();
                drawField.setBorderColor(COLORS.get(selectedColor));
            }
        });

        JComboBox<String> backgroundColorList = new JComboBox<String>();
        COLORS.keySet().forEach(backgroundColorList::addItem);
        backgroundColorList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getItemSelectable();
                String selectedColor = (String) cb.getSelectedItem();
                drawField.setBackgroundColor(COLORS.get(selectedColor));
            }
        });

        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println();
                drawField.print();
            }
        });


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(shapes);
        controlPanel.add(borderColorList);
        controlPanel.add(backgroundColorList);
        controlPanel.add(printButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(drawField, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicEditor();
    }
}