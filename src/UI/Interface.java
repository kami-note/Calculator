package UI;

import Services.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    private static String expression = "";

    public static void UI(JFrame frame) {
        frame.setLayout(new BorderLayout());

        JTextField display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(400, 50));
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = getjButton(label, display);
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private static JButton getjButton(String label, JTextField display) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (label) {
                    case "C":
                        expression = "";
                        display.setText("");
                        break;
                    case "=":
                        String result = String.valueOf(Calculator.calculator(expression));
                        display.setText(result);
                        expression = result;
                        break;
                    default:
                        addOperands(label);
                        display.setText(expression);
                }
            }
        });
        return button;
    }

    private static void addOperands(String label) {
        expression = expression + label;
    }
}
