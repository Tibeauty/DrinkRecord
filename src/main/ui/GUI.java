package ui;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DrinkRecord;
import model.DrinkRecords;

//the graphical user interface for drinking records
public class GUI implements ActionListener {
    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JButton addButton;
    private JButton saveButton;
    private JPanel panel;
    private DrinkRecords drinkRecords;
    private DefaultListModel<DrinkRecord> myList;
    private JList<DrinkRecord> list;
    
    public GUI() {
        frame = new JFrame();

        label = new JLabel("Number of drinking records: 0");

        myList = new DefaultListModel<>();
        list = new JList<>(myList);
        myList.addElement(new DrinkRecord("Fish", 100));
        JScrollPane listScrollPane = new JScrollPane(list);

        // Create the buttons
        addButton = new JButton("Add");
        saveButton = new JButton("Save");

        addButton.addActionListener(this);
        saveButton.addActionListener(this);

        panel = new JPanel(new BorderLayout());

        // Add the list and label to the top of the panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(label, BorderLayout.NORTH);
        topPanel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.CENTER);

        // Add the buttons to the bottom of the panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.add(addButton);
        bottomPanel.add(saveButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Drinking Records");
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //count++;
        //label.setText("Number of drinking records:" + count);
        JTextField typeField = new JTextField();
        JTextField amountField = new JTextField();
        Object[] message = {
            "Type of drink:", typeField,
            "Amount (ml):", amountField
        };

        int option = JOptionPane.showConfirmDialog(
                frame, 
                message, 
                "Enter Drinking Record", 
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_CANCEL_OPTION) {
            String type = typeField.getText();
            String amountText = amountField.getText();
            int amount = Integer.parseInt(amountText);

            DrinkRecord drinkRecord = new DrinkRecord(type, amount);
            drinkRecords.addDrinkRecord(drinkRecord);
        }
    }
}
