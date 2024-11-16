package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.DrinkRecord;
import model.DrinkRecords;
import persistence.JsonReader;
import persistence.JsonWriter;

//the graphical user interface for drinking records
public class GUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JButton addButton;
    private JButton saveButton;
    private JPanel panel;
    private DefaultListModel<DrinkRecord> myList;
    private JList<DrinkRecord> list;
    private JScrollPane listScrollPane;
    private DrinkRecords drinkRecords;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/myDrinkRecord.json";
    
    public GUI() {
        initGUI();

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
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initalize GUI and drinkRecords
    public void initGUI() {
        drinkRecords = new DrinkRecords();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();

        label = new JLabel("Add new drinking record to begin");

        myList = new DefaultListModel<>();
        list = new JList<>(myList);
        //myList.addElement(new DrinkRecord("Fish", 100));
        listScrollPane = new JScrollPane(list);

        // Create the buttons
        addButton = new JButton("Add new drinking Records");
        saveButton = new JButton("Save your drinking Records");

        addButton.addActionListener(this);
        saveButton.addActionListener(this);

        panel = new JPanel(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            passAddButton();
        } else if (e.getSource() == saveButton) {
            passSaveButton();
        }

       
    }

    // EFFECTS: generate next step when user clicking add button
    public void passSaveButton() {
        try {
            jsonWriter.open();
            jsonWriter.write(drinkRecords);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Successfully saved my drinking records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        
    }

    // EFFECTS: generate next step when user clicking add button
    public void passAddButton() {
        JTextField typeField = new JTextField();
        JTextField amountField = new JTextField();
        Object[] message = {
            "Type of drink:", typeField,
            "Amount (ml):", amountField
        };

        ImageIcon originalIcon = new ImageIcon("images/drink.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon customIcon = new ImageIcon(scaledImage);

        int option = JOptionPane.showConfirmDialog(
                frame, 
                message, 
                "Enter Drinking Record", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                customIcon
        );

        if (option == JOptionPane.OK_OPTION) {
            addNewDrinkRecord(typeField, amountField);
        }
    }

    // EFFECT: add new drink records to JList and drinkRecords
    public void addNewDrinkRecord(JTextField typeField, JTextField amountField) {
        String type = typeField.getText();
        String amountText = amountField.getText();
        int amount = Integer.parseInt(amountText);

        DrinkRecord newDrinkRecord = new DrinkRecord(type, amount);
        drinkRecords.addDrinkRecord(newDrinkRecord);

        myList.addElement(newDrinkRecord);
    }
}
