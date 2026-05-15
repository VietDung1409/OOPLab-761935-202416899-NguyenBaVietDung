package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

/**
 * Screen for adding a new DigitalVideoDisc to the store.
 * Inherits shared menu bar and layout from AddItemToStoreScreen.
 */
public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfId       = new JTextField(15);
    private JTextField tfTitle    = new JTextField(15);
    private JTextField tfCategory = new JTextField(15);
    private JTextField tfDirector = new JTextField(15);
    private JTextField tfLength   = new JTextField(15);
    private JTextField tfCost     = new JTextField(15);

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen, "AIMS - Add DVD", 350);
    }

    @Override
    protected String getAddButtonLabel() {
        return "Add DVD";
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 8));
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        form.add(new JLabel("ID:"));           form.add(tfId);
        form.add(new JLabel("Title:"));        form.add(tfTitle);
        form.add(new JLabel("Category:"));     form.add(tfCategory);
        form.add(new JLabel("Director:"));     form.add(tfDirector);
        form.add(new JLabel("Length (min):")); form.add(tfLength);
        form.add(new JLabel("Cost:"));         form.add(tfCost);

        return form;
    }

    @Override
    protected void handleAdd() {
        try {
            int id          = Integer.parseInt(tfId.getText().trim());
            String title    = tfTitle.getText().trim();
            String cat      = tfCategory.getText().trim();
            String director = tfDirector.getText().trim();
            int length      = Integer.parseInt(tfLength.getText().trim());
            float cost      = Float.parseFloat(tfCost.getText().trim());

            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, cat, director, length, cost);
            store.addMedia(dvd);

            JOptionPane.showMessageDialog(this,
                    "DVD \"" + title + "\" added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            goBackToStore();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for ID, Length and Cost.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
