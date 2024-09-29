import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    // Create a frame
    JFrame frame;

    // Create text field
    JTextField textField;

    // Store operator and operands
    String operator;
    double num1, num2, result;

    // Constructor to initialize the calculator interface
    public Calculator() {
        // Create frame
        frame = new JFrame("Calculator");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create a text field
        textField = new JTextField();
        textField.setBounds(30, 40, 340, 50);
        textField.setEditable(false);
        frame.add(textField);

        // Create buttons
        String[] buttonLabels = {
                "7", "8", "9", "/", 
                "4", "5", "6", "*", 
                "1", "2", "3", "-", 
                "0", ".", "=", "+"
        };
        JButton[] buttons = new JButton[16];
        int x = 30, y = 100;

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(x, y, 80, 80);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
            x += 90;

            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 90;
            }
        }

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            // If it's a number or decimal point, append it to the text field
            textField.setText(textField.getText() + command);
        } else if (command.equals("=")) {
            // Perform the calculation
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            operator = null;
        } else {
            // Save the operator and first number
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        // Run the calculator app
        new Calculator();
    }
}
