package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JButton button;
    private JPanel panel;

    public GUI() {
        frame = new JFrame();

        button = new JButton("add");
        label = new JLabel("Number of drinking records: 0");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);


        button.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Drining Records");
        frame.pack();
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
        }
    }
}
