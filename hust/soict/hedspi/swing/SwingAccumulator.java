package hust.soict.hedspi.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingAccumulator extends JFrame {
    private JTextField tfInput;
    private JTextField tfOutput;
    private int sum = 0;

    public SwingAccumulator() {
        // Get the content pane (Swing components must be added to content pane, not JFrame directly)
        Container cp = getContentPane();

        // Set layout: 2 rows, 2 columns
        cp.setLayout(new GridLayout(2, 2));

        // Row 1: label + input field
        cp.add(new JLabel("Enter an integer: "));
        tfInput = new JTextField(10);
        tfInput.addActionListener(new EnterListener(tfInput));
        cp.add(tfInput);

        // Row 2: label + output field (read-only)
        cp.add(new JLabel("The accumulated sum is: "));
        tfOutput = new JTextField(10);
        tfOutput.setEditable(false);
        cp.add(tfOutput);

        // JFrame settings
        setTitle("Swing Accumulator");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Named inner class for event handling (same logic as AWT version)
    private class EnterListener implements ActionListener {
        private JTextField source;

        public EnterListener(JTextField source) {
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
        // Swing apps should be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SwingAccumulator());
    }
}
