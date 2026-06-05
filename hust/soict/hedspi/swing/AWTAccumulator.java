package hust.soict.hedspi.swing;

import java.awt.*;
import java.awt.event.*;

public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    public AWTAccumulator() {
        // Set layout: 2 rows, 2 columns
        setLayout(new GridLayout(2, 2));

        // Row 1: label + input field
        add(new Label("Enter an integer: "));
        tfInput = new TextField(10);
        tfInput.addActionListener(new EnterListener(tfInput));
        add(tfInput);

        // Row 2: label + output field (read-only)
        add(new Label("The accumulated sum is: "));
        tfOutput = new TextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);

        // Frame settings
        setTitle("AWT Accumulator");
        setSize(400, 100);
        setVisible(true);

        // Handle window close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Named inner class for event handling
    private class EnterListener implements ActionListener {
        private TextField source;

        public EnterListener(TextField source) {
            this.source = source;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int value = Integer.parseInt(source.getText());
            sum += value;
            tfOutput.setText(String.valueOf(sum));
            source.setText("");
        }
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }
}
